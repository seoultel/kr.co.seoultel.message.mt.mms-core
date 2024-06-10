package kr.co.seoultel.message.mt.mms.core.util;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.FormatException;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.MessageDeserializationException;

/**
 * {@link ConvertorUtil} 클래스의 유닛 테스트 클래스입니다.
 */
public class ConvertorUtilTest {

    /**
     * {@link ConvertorUtil#convertBytesToObject(byte[], Class)} 메서드를 테스트합니다.
     * 바이트 배열을 객체로 변환할 때 성공적으로 변환되는지 확인하는 테스트입니다.
     *
     * @throws FormatException 변환 과정에서 발생한 예외
     */
    @Test
    public void testConvertBytesToObject_Success() throws FormatException {
        String json = "{\"name\":\"test\"}";
        byte[] body = json.getBytes();
        ConvertorUtilTest.TestObject result = ConvertorUtil.convertBytesToObject(body, ConvertorUtilTest.TestObject.class);
        assertNotNull(result);
        assertEquals("test", result.getName());
    }

    /**
     * {@link ConvertorUtil#convertBytesToObject(byte[], Class)} 메서드를 테스트합니다.
     * 잘못된 JSON 형식의 바이트 배열을 객체로 변환할 때 예외가 발생하는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertBytesToObject_FormatException() {
        byte[] body = "invalid json".getBytes();
        assertThrows(MessageDeserializationException.class, () -> {
            ConvertorUtil.convertBytesToObject(body, ConvertorUtilTest.TestObject.class);
        });
    }

    /**
     * {@link ConvertorUtil#convertJsonToObject(String, Class)} 메서드를 테스트합니다.
     * JSON 문자열을 객체로 변환할 때 성공적으로 변환되는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertJsonToObject_Success() {
        String json = "{\"name\":\"test\"}";
        ConvertorUtilTest.TestObject obj = ConvertorUtil.convertJsonToObject(json, ConvertorUtilTest.TestObject.class);
        assertNotNull(obj);
        assertEquals("test", obj.getName());
    }

    /**
     * {@link ConvertorUtil#convertJsonToObject(String, Class)} 메서드를 테스트합니다.
     * 잘못된 JSON 형식의 문자열을 객체로 변환할 때 예외가 발생하는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertJsonToObject_JsonSyntaxException() {
        String json = "invalid json";
        ConvertorUtilTest.TestObject obj = ConvertorUtil.convertJsonToObject(json, ConvertorUtilTest.TestObject.class);
        assertNull(obj);
    }

    /**
     * {@link ConvertorUtil#convertPropertyToBytes(String, int)} 메서드를 테스트합니다.
     * 문자열을 EUC-KR 인코딩으로 바이트 배열로 변환할 때 올바르게 변환되는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertPropertyToBytesByEucKr_String() {
        String property = "test";
        byte[] bytes = ConvertorUtil.convertPropertyToBytesByEucKr(property, 10);
        assertEquals(10, bytes.length);
        assertEquals("test", new String(bytes, Charset.forName("euc-kr")).trim());
    }

    /**
     * 문자열이 지정된 최대 길이를 초과하는 경우 올바르게 자르고 변환되는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertPropertyToBytesByEucKr_String_MaxLength() {
        String property = "longstring";
        byte[] bytes = ConvertorUtil.convertPropertyToBytesByEucKr(property, 5);
        assertEquals(5, bytes.length);
        assertEquals("longs", new String(bytes, Charset.forName("euc-kr")));
    }

    /**
     * {@link ConvertorUtil#convertPropertyToBytes(String, int)} 메서드를 테스트합니다.
     * 문자열이 null일 때 빈 문자열로 변환되는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertPropertyToBytesByEucKr_String_Null() {
        byte[] bytes = ConvertorUtil.convertPropertyToBytesByEucKr((String) null, 10);
        assertEquals(10, bytes.length);
        assertEquals("", new String(bytes, Charset.forName("euc-kr")).trim());
    }

    /**
     * {@link ConvertorUtil#convertPropertyToBytes(int, int)} 메서드를 테스트합니다.
     * 정수를 EUC-KR 인코딩으로 바이트 배열로 변환할 때 올바르게 변환되는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertPropertyToBytesByEucKr_Int() {
        int property = 123;
        byte[] bytes = ConvertorUtil.convertPropertyToBytesByEucKr(property, 10);
        assertEquals(10, bytes.length);
        assertEquals("123", new String(bytes, Charset.forName("euc-kr")).trim());
    }

    /**
     * {@link ConvertorUtil#convertPropertyToBytes(int, int)} 메서드를 테스트합니다.
     * 정수가 null일 때 빈 문자열로 변환되는지 확인하는 테스트입니다.
     */
    @Test
    public void testConvertPropertyToBytesByEucKr_Int_Null() {
        byte[] bytes = ConvertorUtil.convertPropertyToBytesByEucKr((Integer) null, 10);
        assertEquals(10, bytes.length);
        assertEquals("", new String(bytes, Charset.forName("euc-kr")).trim());
    }

    /**
     * {@link ConvertorUtil#getStrPropertyInByteBuf(ByteBuf, int)} 메서드를 테스트합니다.
     * 바이트 버퍼에서 문자열 속성을 읽어올 때 올바르게 읽어오는지 확인하는 테스트입니다.
     */
    @Test
    public void testGetStrPropertyInByteBuf() {
        ByteBuf buf = Unpooled.copiedBuffer("test\u0000\u0000", Charset.forName("euc-kr"));
        String result = ConvertorUtil.getStrPropertyInByteBuf(buf, 6);
        assertEquals("test", result);
    }

    /**
     * {@link ConvertorUtil#getStrPropertyInByteBuf(ByteBuf, int)} 메서드를 테스트합니다.
     * 바이트 버퍼에서 문자열 속성을 읽어올 때 지정된 바이트 수만큼 건너뛰고 올바르게 읽어오는지 확인하는 테스트입니다.
     */
    @Test
    public void testGetStrPropertyInByteBuf_SkipBytes() {
        ByteBuf buf = Unpooled.copiedBuffer("skiptest\u0000\u0000", Charset.forName("euc-kr"));
        String result = ConvertorUtil.getStrPropertyInByteBuf(buf, 6, 4);
        assertEquals("test", result);
    }

    /**
     * {@link ConvertorUtil#getStrPropertyInByteBuf(ByteBuf, int)} 메서드를 테스트합니다.
     * 바이트 버퍼에서 문자열 속성을 읽어온 후 지정된 바이트 수만큼 건너뛰고 올바르게 읽어오는지 확인하는 테스트입니다.
     */
    @Test
    public void testGetStrPropertyInByteBuf_SkipBytes_AfterRead() {
        ByteBuf buf = Unpooled.copiedBuffer("skiptest\u0000\u0000skip", Charset.forName("euc-kr"));
        String result = ConvertorUtil.getStrPropertyInByteBuf(buf, 6, 4, 4);
        assertEquals("test", result);
    }


    /**
     * {@link ConvertorUtil#getIntPropertyInByteBuf(ByteBuf, int)} 메서드를 테스트합니다.
     * 바이트 버퍼에서 정수 속성을 올바르게 읽어오는지 확인하는 테스트입니다.
     */
    @Test
    public void testGetIntPropertyInByteBuf() {
        ByteBuf buf = Unpooled.copiedBuffer("123", Charset.forName("euc-kr"));
        int result = ConvertorUtil.getIntPropertyInByteBuf(buf, 3);
        assertEquals(123, result);
    }

    /**
     * {@link ConvertorUtil#getIntPropertyInByteBuf(ByteBuf, int)} 메서드를 테스트합니다.
     * 바이트 버퍼에서 일정 바이트 수를 건너뛴 후 정수 속성을 올바르게 읽어오는지 확인하는 테스트입니다.
     */
    @Test
    public void testGetIntPropertyInByteBuf_SkipBytes() {
        ByteBuf buf = Unpooled.copiedBuffer("skip123", Charset.forName("euc-kr"));
        int result = ConvertorUtil.getIntPropertyInByteBuf(buf, 3, 4);
        assertEquals(123, result);
    }

    /**
     * {@link ConvertorUtil#getIntPropertyInByteBuf(ByteBuf, int)} 메서드를 테스트합니다.
     * 바이트 버퍼에서 일정 바이트 수를 건너뛰고 일정 바이트를 읽은 후 다시 일정 바이트를 건너뛴 후 정수 속성을 올바르게 읽어오는지 확인하는 테스트입니다.
     */
    @Test
    public void testGetIntPropertyInByteBuf_SkipBytes_AfterRead() {
        ByteBuf buf = Unpooled.copiedBuffer("skip123skip", Charset.forName("euc-kr"));
        int result = ConvertorUtil.getIntPropertyInByteBuf(buf, 3, 4, 4);
        assertEquals(123, result);
    }

    // Test class for the purposes of this test suite
    public static class TestObject {
        private String name;

        public TestObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
