package message.hist.messages.delivery.lms;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMultipartData;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.lms.HistLmsDeliveryMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistLmsDeliveryMessageTest {

    private static final String MSG_TYPE = HistProtocol.LMS_MSG_TYPE;
    private static final String DA_ADDR = "01012345678";
    private static final String CALLBACK = "01012345678";
    private static final String ENCODING = HistProtocol.EUC_KR_ENCODING;
    private static final String TEXT = "Hello, World!";
    private static final String SERIAL = "202112310012345";
    private static final String SENDER_CODE = "101280092";
    private static final int MEDIA_CNT = 1;
    private static final String EXT_SIZE = "10";

    @Test
    public void testDefaultConstructor() {
        HistLmsDeliveryMessage message = new HistLmsDeliveryMessage();

        assertNotNull(message);
        assertEquals(MSG_TYPE, message.getMsgType());
        assertEquals("", message.getDaAddr());
        assertEquals("", message.getCallback());
        assertEquals("", message.getEncoding());
        assertEquals("", message.getText());
        assertEquals("", message.getSerial());
        assertEquals("", message.getSenderCode());
        assertEquals(0, message.getMediaCnt());
        assertEquals("", message.getExtSize());
        assertNotNull(message.getMedia());
    }

    @Test
    public void testParameterizedConstructor() {
        HistLmsDeliveryMessage message = new HistLmsDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE, "message");

        assertNotNull(message);
        assertEquals(MSG_TYPE, message.getMsgType());
        assertEquals(DA_ADDR, message.getDaAddr());
        assertEquals(CALLBACK, message.getCallback());
        assertEquals(ENCODING, message.getEncoding());
        assertEquals(TEXT, message.getText());
        assertEquals(SERIAL, message.getSerial());
        assertEquals(SENDER_CODE, message.getSenderCode());
        assertEquals(MEDIA_CNT, message.getMediaCnt());
        assertEquals(EXT_SIZE, message.getExtSize());
        assertNotNull(message.getMedia());
    }

    @Test
    public void testToByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistLmsDeliveryMessage message = new HistLmsDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE, "message");
        message.toByteBuf(byteBuf);

        byte[] byteArray = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(byteArray);

        ByteBuf readBuf = Unpooled.wrappedBuffer(byteArray);
        HistLmsDeliveryMessage resultMessage = new HistLmsDeliveryMessage();
        resultMessage.fromByteBuf(readBuf);

        assertEquals(message, resultMessage);
    }


    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistLmsDeliveryMessage originalMessage = new HistLmsDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE, "message");
        HistDeliveryMultipartData multipartData = new HistDeliveryMultipartData("message");
        originalMessage.toByteBuf(byteBuf);

        HistLmsDeliveryMessage message = new HistLmsDeliveryMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(originalMessage, message);
    }

    @Test
    public void testEqualsAndHashCode() {
        HistLmsDeliveryMessage message2 = new HistLmsDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE, "message");
        HistLmsDeliveryMessage message1 = new HistLmsDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE, "message");
        HistLmsDeliveryMessage message3 = new HistLmsDeliveryMessage("DIFFERENT", DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE, "message");

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}

