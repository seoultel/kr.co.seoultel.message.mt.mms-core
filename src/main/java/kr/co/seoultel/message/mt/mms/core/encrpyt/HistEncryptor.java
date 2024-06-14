package kr.co.seoultel.message.mt.mms.core.encrpyt;

import io.netty.buffer.ByteBufUtil;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;


@Slf4j
public class HistEncryptor {

    private final int keypos;
    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivParameterSpec;

    public HistEncryptor(int keypos, String password) throws CryptoException {
        try {
            this.keypos = keypos;
            byte[] encKey = getEncKey(password);

            byte[] ivKey = new byte[16];

            secretKeySpec = new SecretKeySpec(encKey, "AES");
            ivParameterSpec = new IvParameterSpec(ivKey);

            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }

    private final Cipher cipher;

    public byte[] encryptBySha256(String password) throws CryptoException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(password.getBytes());
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }
    // SHA-256
    private byte[] getEncKey(String password) throws CryptoException {
        try {
            byte[] encryptedBySha256 = encryptBySha256(password);
            String hex = ByteBufUtil.hexDump(encryptedBySha256);

            if (keypos <=  32) {
                String encKey = hex.substring(keypos, keypos + 32);
                System.out.println("keypos <= 32, encKey : " + encKey);
                return encKey.getBytes();
            } else {
                String encKey = hex.substring(keypos);
                System.out.println("keypos > 32, encKey : " + encKey);
                return encKey.getBytes();
            }
        } catch (Exception var5) {
            throw new CryptoException("Failed to get encrypt key, using AES");
        }
    }
    // AES 복호화
    public Optional<String> encryptTextByAes256AndBase64(String password) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Optional.of(Base64.getEncoder().encodeToString(encrypted));
        } catch (GeneralSecurityException var3) {
            log.error("[SYSTEM] FAILED TO ENCRYPT TEXT[{}] BY BASE64 & AES256", password, var3);
            return Optional.empty();
        }
    }

    // AES 복호화
    public Optional<String> decryptTextByBase64AndAes128(String str) {
        try {
            byte[] decryptedByBase64 = Base64.getDecoder().decode(str);
            return Optional.of(new String(cipher.doFinal(decryptedByBase64)));
        } catch (GeneralSecurityException e) {
            log.error("[SYSTEM] FAILED TO DECRYPT TEXT[{}] BY BASE64 & AES128", str, e);
            return Optional.empty();
        }
    }
}

