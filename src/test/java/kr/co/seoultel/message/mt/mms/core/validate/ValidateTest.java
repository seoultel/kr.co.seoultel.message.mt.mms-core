package kr.co.seoultel.message.mt.mms.core.validate;

import io.netty.buffer.ByteBufUtil;
import kr.co.seoultel.message.mt.mms.core.encrpyt.HistEncryptor;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;

public class ValidateTest {

    public static void main(String[] args) {
//        int keypos = 12;
//        String pwd = "testpwd";
//        try {
//            HistEncryptor histEncryptor = new HistEncryptor(keypos, pwd);
//            byte[] bytes = histEncryptor.encryptBySha256(pwd);
//            String hex = ByteBufUtil.hexDump(bytes);
//            System.out.println("hex : " + hex);
//
//            // hex.substring(keypos, keypos + 32);
//
//            String encryptedPwdBySha256 = ByteBufUtil.hexDump(histEncryptor.encryptBySha256(pwd));
//            System.out.println("encryptedPwdBySha256 : " + encryptedPwdBySha256);
//
//            String encryptedPwdByAes256AndBase64 = histEncryptor.encryptTextByAes256AndBase64(encryptedPwdBySha256).get();
//            System.out.println("encryptedPwd : " + encryptedPwd);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }

//        int keypos = 60;
//        String hexDump = "a85b6a20813c31a8b1b3f3618da796271c9aa293b3f809873053b21aec501087";
//
//        byte[] hash = new byte[32];
//        if (keypos > 33) {
//            String substr = hexDump.substring(keypos);
//            System.out.println(substr);
//        } else {
//            String substr = hexDump.substring(0, keypos);
//            System.out.println(substr);
//        }

//        System.out.println("Arrays.toString(new byte[16]) : " +  Arrays.toString(new byte[16]));

//        if (keypos >= 33) {
//            byte[] dumpBytes = hexDump.substring(keypos).getBytes();
//            System.arraycopy(dumpBytes, 0, );
//            System.arraycopy(dumpBytes, 0, keyBytes, 0, Math.min(dumpBytes.length, keyBytes.length));
//
////            System.out.println(Arrays.toString(keyBytes));
////            System.out.println(new String(hexDump.substring(keypos, hexDump.length()).getBytes()));
//        } else {
////            System.out.println(Arrays.toString(hexDump.substring(keypos, keypos + 32).getBytes()));
//        }


//        String localDateTimeString = "2024-06-07 13:35:26.000";
//        String mcmpLocalDateFormat = DateUtil.parseFullLocalDateToMcmpLocalDateFormat(localDateTimeString);
//
//        System.out.printf("mcmpLocalDateFormat : %s", mcmpLocalDateFormat);
//    }

}
