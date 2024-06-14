package kr.co.seoultel.message.mt.mms.core.message.hist.messages.delivery.mms;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMultipartData;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.mms.HistMmsDeliveryMessage;



public class HistMmsDeliveryMessageTest {

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
    public void testHistMmsDeliveryMessageConstructor() {
        HistMmsDeliveryMessage message = new HistMmsDeliveryMessage();

        assertEquals(HistProtocol.MMS_MSG_TYPE, message.getMsgType());
        assertEquals("", message.getDaAddr());
        assertEquals("", message.getCallback());
        assertEquals("", message.getEncoding());
        assertEquals("", message.getText());
        assertEquals("", message.getSerial());
        assertEquals("", message.getSenderCode());
        assertEquals(0, message.getMediaCnt());
        assertEquals("", message.getExtSize());
    }

    @Test
    public void testHistMmsDeliveryMessageParameterizedConstructor() {
        HistMmsDeliveryMessage message = new HistMmsDeliveryMessage(DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE);

        assertEquals(HistProtocol.MMS_MSG_TYPE, message.getMsgType());
        assertEquals(DA_ADDR, message.getDaAddr());
        assertEquals(CALLBACK, message.getCallback());
        assertEquals(ENCODING, message.getEncoding());
        assertEquals(TEXT, message.getText());
        assertEquals(SERIAL, message.getSerial());
        assertEquals(SENDER_CODE, message.getSenderCode());
        assertEquals(0, message.getMediaCnt());
        assertEquals(EXT_SIZE, message.getExtSize());
    }

    @Test
    public void testToByteBufAndFromByteBuf() {
        HistMmsDeliveryMessage originalMessage = new HistMmsDeliveryMessage(DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE);

        // Prepare sample media data
        HistDeliveryMultipartData multipartData1 = new HistDeliveryMultipartData("JPG", "sampleImage".getBytes(Charset.forName(EUC_KR)));
        originalMessage.addMedia(multipartData1);

        ByteBuf buffer = Unpooled.buffer();
        originalMessage.toByteBuf(buffer);

        HistMmsDeliveryMessage decodedMessage = new HistMmsDeliveryMessage();
        decodedMessage.fromByteBuf(buffer);

        assertEquals(originalMessage, decodedMessage);
    }

    @Test
    public void testEqualsAndHashCode() {
        HistMmsDeliveryMessage message1 = new HistMmsDeliveryMessage(DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE);
        HistMmsDeliveryMessage message2 = new HistMmsDeliveryMessage(DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE);

        HistDeliveryMultipartData multipartData1 = new HistDeliveryMultipartData("JPG", "sampleImage".getBytes(Charset.forName(EUC_KR)));
        message1.addMedia(multipartData1);
        message2.addMedia(multipartData1);

        assertEquals(message1, message2);
        assertEquals(message1.hashCode(), message2.hashCode());
    }

    @Test
    public void testInequality() {
        HistMmsDeliveryMessage message1 = new HistMmsDeliveryMessage(DA_ADDR, CALLBACK, ENCODING, TEXT, SERIAL, SENDER_CODE, EXT_SIZE);
        HistMmsDeliveryMessage message2 = new HistMmsDeliveryMessage(DA_ADDR, CALLBACK, ENCODING, TEXT + ", diff", SERIAL, SENDER_CODE, EXT_SIZE);

        assertNotEquals(message1, message2);
    }
}