package kr.co.seoultel.message.mt.mms.core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String readJsonFileAsString(String filePath) throws IOException {
        // 파일 경로를 사용하여 파일의 모든 바이트를 읽고 문자열로 변환합니다.
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

}
