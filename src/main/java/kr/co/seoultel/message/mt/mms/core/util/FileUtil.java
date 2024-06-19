package kr.co.seoultel.message.mt.mms.core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static String readJsonFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void createFile(String filePathStr) {
        try {
            Path filePath = Path.of(filePathStr);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
