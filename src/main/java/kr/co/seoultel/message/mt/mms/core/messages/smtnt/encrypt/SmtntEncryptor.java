package kr.co.seoultel.message.mt.mms.core.messages.smtnt.encrypt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
public class SmtntEncryptor {

    private static Cipher CIPHER;


    private static byte[] ENC_KEY_BYTES;
    private static byte[] IV_KEY_BYTES;


    private static SecretKeySpec SECRECT_KEY_SPEC;
    private static IvParameterSpec IV_PARAMETER_SPEC;

    private static final String CIPHER_INSTANCE_TYPE = "AES/CBC/PKCS5Padding";


    public static void initialize(String encKeyStr, String ivKeyStr) {
        setEncKeyBytes(encKeyStr);
        setIvKeyBytes(ivKeyStr);
        setDefaultCipher();
    }

    public static void setEncKeyBytes(String encKeyStr) {
        encKeyStr = encKeyStr.length() > 16 ? encKeyStr.substring(0, 16) : encKeyStr;
        ENC_KEY_BYTES = encKeyStr.getBytes(StandardCharsets.UTF_8);
        SECRECT_KEY_SPEC = new SecretKeySpec(ENC_KEY_BYTES, "AES");
    }

    public static void setIvKeyBytes(String ivKeyStr) {
        IV_KEY_BYTES = ivKeyStr.getBytes(StandardCharsets.UTF_8);
        IV_PARAMETER_SPEC = new IvParameterSpec(IV_KEY_BYTES);
    }

    public static void setDefaultCipher() {
        try {
            CIPHER = Cipher.getInstance(CIPHER_INSTANCE_TYPE);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            log.error("[Encryption-Util] ", e);
        }
    }

    // AES 암호화
    public static Optional<String> encryptTextByAes128AndBase64(String str) {
        try {
            CIPHER.init(Cipher.ENCRYPT_MODE, SECRECT_KEY_SPEC, IV_PARAMETER_SPEC);
            byte[] encrypted = CIPHER.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Optional.of(new String(Base64.getEncoder().encode(encrypted), StandardCharsets.UTF_8));
        } catch (GeneralSecurityException e) {
            log.error("[SYSTEM] FAILED TO ENCRYPT TEXT[{}] BY BASE64 & AES128", str, e);
            return Optional.empty();
        }
    }

    // AES 복호화
    public static Optional<String> decryptTextByBase64AndAes128(String str) {
        try {
            CIPHER.init(Cipher.DECRYPT_MODE, SECRECT_KEY_SPEC, IV_PARAMETER_SPEC);
            byte[] decryptedByBase64 = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
            return Optional.of(new String(CIPHER.doFinal(decryptedByBase64), StandardCharsets.UTF_8));
        } catch (GeneralSecurityException e) {
            log.error("[SYSTEM] FAILED TO DECRYPT TEXT[{}] BY BASE64 & AES128", str, e);
            return Optional.empty();
        }
    }


    // IV 생성 메서드
    private static byte[] generateRandomIV() {
        byte[] iv = new byte[16]; // IV 길이는 블록 크기와 동일하게 설정 (AES의 경우 16바이트)
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv); // 보안적으로 안전한 난수 생성
        return iv;
    }
}

