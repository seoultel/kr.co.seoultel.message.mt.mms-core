//package dataVault;
//
//import kr.co.seoultel.message.mt.mms.core.dataVault.DataVault;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DataVaultTest {
//
//    private static final String TEST_FILE_PATH = "/Users/sim/Documents/GitHub/seoultel/libraries/kr.co.seoultel.message.mt.mms-core/src/test/resources/dataVault/test.dvt";
//    private DataVault<String> dataVault;
//
//    @BeforeEach
//    void setUp() {
//        dataVault = new DataVault<>("TestVault", TEST_FILE_PATH);
//        dataVault.clearFile(); // Ensure the file is empty before each test
//    }
//
//    @Test
//    void testInit() {
//        dataVault.init();
//        File file = new File(TEST_FILE_PATH);
//        assertTrue(file.exists());
//    }
//
//    @Test
//    void testWriteOne() throws IOException {
//        String testData = "Test data";
//        dataVault.writeOne(testData);
//        List<String> lines = Files.readAllLines(new File(TEST_FILE_PATH).toPath());
//        assertEquals(1, lines.size());
//        assertEquals(testData, lines.get(0));
//    }
//
//    @Test
//    void testWriteAll() throws IOException {
//        List<String> testData = Arrays.asList("Data 1", "Data 2", "Data 3");
//        dataVault.writeAll(testData);
//        List<String> lines = Files.readAllLines(new File(TEST_FILE_PATH).toPath());
//        assertEquals(testData.size(), lines.size());
//        assertEquals(testData, lines);
//    }
//
//    @Test
//    void testReadAll() throws IOException {
//        List<String> testData = Arrays.asList("Data 1", "Data 2", "Data 3");
//        dataVault.writeAll(testData);
//        Optional<List<String>> readData = dataVault.readAll(String.class);
//        assertTrue(readData.isPresent());
//        assertEquals(testData, readData.get());
//    }
//
//    @Test
//    void testDestroy() throws IOException {
//        List<String> testData = Arrays.asList("Data 1", "Data 2", "Data 3");
//        dataVault.destroy(testData);
//        List<String> lines = Files.readAllLines(new File(TEST_FILE_PATH).toPath());
//        assertEquals(testData.size(), lines.size());
//        assertEquals(testData, lines);
//    }
//
//    @Test
//    void testClearFile() throws IOException {
//        List<String> testData = Arrays.asList("Data 1", "Data 2", "Data 3");
//        dataVault.writeAll(testData);
//        dataVault.clearFile();
//        List<String> lines = Files.readAllLines(new File(TEST_FILE_PATH).toPath());
//        assertEquals(1, lines.size()); // Expecting an empty array '[]'
//    }
//}
