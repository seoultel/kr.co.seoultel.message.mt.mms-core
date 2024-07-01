package kr.co.seoultel.message.mt.mms.core.message.hist.messages.delivery;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMultipartData;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;
import static org.junit.jupiter.api.Assertions.*;

public class HistDeliveryMultipartDataTest {

    private static final String MESSAGE = "Test message";
    private static final String EXTENSION_JPG = "JPG";
    private static final byte[] MEDIA_FILE_BYTES = new byte[]{1, 2, 3, 4, 5};
    private static final String M_TYPE = "11";
    private static final int M_FILE_LEN = 5;

    @Test
    public void testDefaultConstructor() {
        HistDeliveryMultipartData data = new HistDeliveryMultipartData();

        assertNotNull(data);
        assertNull(data.getMType());
        assertEquals(HistProtocol.EUC_KR_ENCODING, data.getEncoding());
        assertEquals(0, data.getMFileLen());
        assertNull(data.getBytes());
    }

    @Test
    public void testStringConstructor() {
        HistDeliveryMultipartData data = new HistDeliveryMultipartData(MESSAGE);

        byte[] expectedBytes = MESSAGE.getBytes(Charset.forName(EUC_KR));

        assertNotNull(data);
        assertEquals(HistProtocol.M_TYPE_11, data.getMType());
        assertEquals(HistProtocol.EUC_KR_ENCODING, data.getEncoding());
        assertEquals(expectedBytes.length, data.getMFileLen());
        assertArrayEquals(expectedBytes, data.getBytes());
    }

    @Test
    public void testByteArrayConstructor() {
        HistDeliveryMultipartData data = new HistDeliveryMultipartData(EXTENSION_JPG, MEDIA_FILE_BYTES);

        assertNotNull(data);
        assertEquals(HistProtocol.M_TYPE_21, data.getMType());
        assertEquals(HistProtocol.EUC_KR_ENCODING, data.getEncoding());
        assertEquals(MEDIA_FILE_BYTES.length, data.getMFileLen());
        assertArrayEquals(MEDIA_FILE_BYTES, data.getBytes());
    }


    @Test
    public void testToByteBuf() {
        HistDeliveryMultipartData data = HistDeliveryMultipartData.builder()
                .mType(M_TYPE)
                .mFileLen(M_FILE_LEN)
                .media(MEDIA_FILE_BYTES)
                .build();

        ByteBuf byteBuf = Unpooled.buffer();
        data.toByteBuf(byteBuf);

        byte[] byteArray = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(byteArray);

        ByteBuf readBuf = Unpooled.wrappedBuffer(byteArray);
        HistDeliveryMultipartData resultData = new HistDeliveryMultipartData();
        resultData.fromByteBuf(readBuf);

        assertEquals(data.getMType(), resultData.getMType());
        assertEquals(data.getEncoding(), resultData.getEncoding());
        assertEquals(data.getMFileLen(), resultData.getMFileLen());
    }

    @Test
    public void testFromByteBuf() {
        HistDeliveryMultipartData originalData = HistDeliveryMultipartData.builder()
                .mType(M_TYPE)
                .mFileLen(M_FILE_LEN)
                .media(MEDIA_FILE_BYTES)
                .build();

        ByteBuf byteBuf = Unpooled.buffer();
        originalData.toByteBuf(byteBuf);

        HistDeliveryMultipartData data = new HistDeliveryMultipartData();
        data.fromByteBuf(byteBuf);

        assertEquals(M_TYPE, data.getMType());
        assertEquals(HistProtocol.EUC_KR_ENCODING, data.getEncoding());
        assertEquals(M_FILE_LEN, data.getMFileLen());
    }

    @Test
    public void testEqualsAndHashCode() {
        HistDeliveryMultipartData data1 = HistDeliveryMultipartData.builder()
                .mType(M_TYPE)
                .mFileLen(M_FILE_LEN)
                .media(MEDIA_FILE_BYTES)
                .build();

        HistDeliveryMultipartData data2 = HistDeliveryMultipartData.builder()
                .mType(M_TYPE)
                .mFileLen(M_FILE_LEN)
                .media(MEDIA_FILE_BYTES)
                .build();

        HistDeliveryMultipartData data3 = HistDeliveryMultipartData.builder()
                .mType("DIFFERENT")
                .mFileLen(10)
                .media(new byte[]{5, 4, 3, 2, 1})
                .build();

        assertEquals(data1, data2);
        assertNotEquals(data1, data3);
        assertEquals(data1.hashCode(), data2.hashCode());
        assertNotEquals(data1.hashCode(), data3.hashCode());
    }
}
