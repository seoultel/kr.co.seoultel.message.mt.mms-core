package kr.co.seoultel.message.mt.mms.core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String readJsonFileAsString(String filePath) throws IOException {
        // ���� ��θ� ����Ͽ� ������ ��� ����Ʈ�� �а� ���ڿ��� ��ȯ�մϴ�.
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

}
