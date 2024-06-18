package kr.co.seoultel.message.mt.mms.core.message.hist.messages.delivery;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMessage;

public class HistDeliveryMessageTest {

    private static final String MSG_TYPE = HistProtocol.SMS_MSG_TYPE;
    private static final String DA_ADDR = "01012345678";
    private static final String CALLBACK = "0212345678";
    private static final String ENCODING = HistProtocol.EUC_KR_ENCODING;
    private static final String TEXT = "Hello, World!";
    private static final String SERIAL = "202112310012345";
    private static final String SENDER_CODE = "101280092";
    private static final int MEDIA_CNT = 0;
    private static final String EXT_SIZE = "0";

    @Test
    public void testConstructor() {
        HistDeliveryMessage message = new HistDeliveryMessage();

        assertNotNull(message);
        assertEquals(HistProtocol.HIST_DELIVERY_HEAD_TYPE, message.getHeadType());
        assertEquals(HistProtocol.HIST_DELIVERY_MSG_LENG, message.getMsgLeng());
        assertEquals("", message.getMsgType());
        assertEquals("", message.getDaAddr());
        assertEquals("", message.getCallback());
        assertEquals("", message.getEncoding());
        assertEquals("", message.getText());
        assertEquals("", message.getSerial());
        assertEquals("", message.getSenderCode());
        assertEquals(0, message.getMediaCnt());
        assertEquals("0", message.getExtSize());
    }

    @Test
    public void testParameterizedConstructor() {
        HistDeliveryMessage message = new HistDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, MEDIA_CNT, EXT_SIZE);

        assertNotNull(message);
        assertEquals(HistProtocol.HIST_DELIVERY_HEAD_TYPE, message.getHeadType());
        assertEquals(HistProtocol.HIST_DELIVERY_MSG_LENG, message.getMsgLeng());
        assertEquals(MSG_TYPE, message.getMsgType());
        assertEquals(DA_ADDR, message.getDaAddr());
        assertEquals(CALLBACK, message.getCallback());
        assertEquals(ENCODING, message.getEncoding());
        assertEquals(TEXT, message.getText());
        assertEquals(SERIAL, message.getSerial());
        assertEquals(SENDER_CODE, message.getSenderCode());
        assertEquals(MEDIA_CNT, message.getMediaCnt());
        assertEquals(EXT_SIZE, message.getExtSize());
    }

    @Test
    public void testToByteBuf() {
        HistDeliveryMessage message = new HistDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, MEDIA_CNT, EXT_SIZE);

        ByteBuf byteBuf = Unpooled.buffer();
        message.toByteBuf(byteBuf);

        HistDeliveryMessage resultMessage = new HistDeliveryMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistDeliveryMessage originalMessage = new HistDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, MEDIA_CNT, EXT_SIZE);
        originalMessage.toByteBuf(byteBuf);

        HistDeliveryMessage message = new HistDeliveryMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(MSG_TYPE, message.getMsgType());
        assertEquals(DA_ADDR, message.getDaAddr());
        assertEquals(CALLBACK, message.getCallback());
        assertEquals(ENCODING, message.getEncoding());
        assertEquals(TEXT, message.getText());
        assertEquals(SERIAL, message.getSerial());
        assertEquals(SENDER_CODE, message.getSenderCode());
        assertEquals(MEDIA_CNT, message.getMediaCnt());
        assertEquals(EXT_SIZE, message.getExtSize());
    }

    @Test
    public void testEqualsAndHashCode() {
        HistDeliveryMessage message1 = new HistDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, MEDIA_CNT, EXT_SIZE);
        HistDeliveryMessage message2 = new HistDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, MEDIA_CNT, EXT_SIZE);
        HistDeliveryMessage message3 = new HistDeliveryMessage(MSG_TYPE, DA_ADDR, CALLBACK, ENCODING, TEXT + ", diff", SERIAL, SENDER_CODE, MEDIA_CNT, EXT_SIZE);

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}