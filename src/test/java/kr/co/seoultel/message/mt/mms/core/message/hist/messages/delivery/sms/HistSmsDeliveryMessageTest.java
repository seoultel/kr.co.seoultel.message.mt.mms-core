package kr.co.seoultel.message.mt.mms.core.message.hist.messages.delivery.sms;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.sms.HistSmsDeliveryMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistSmsDeliveryMessageTest {
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
        HistSmsDeliveryMessage message = new HistSmsDeliveryMessage();

        assertNotNull(message);
        assertEquals(HistProtocol.HIST_DELIVERY_HEAD_TYPE, message.getHeadType());
        assertEquals(HistProtocol.HIST_DELIVERY_MSG_LENG, message.getMsgLeng());
        assertEquals(HistProtocol.SMS_MSG_TYPE, message.getMsgType());
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
        HistSmsDeliveryMessage message = HistSmsDeliveryMessage.builder()
                .msgType(MSG_TYPE)
                .daAddr(DA_ADDR)
                .callback(CALLBACK)
                .encoding(ENCODING)
                .text(TEXT)
                .serial(SERIAL)
                .senderCode(SENDER_CODE)
                .mediaCnt(MEDIA_CNT)
                .extSize(EXT_SIZE)
                .build();

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
        ByteBuf byteBuf = Unpooled.buffer();
        HistSmsDeliveryMessage message = HistSmsDeliveryMessage.builder()
                .msgType(MSG_TYPE)
                .daAddr(DA_ADDR)
                .callback(CALLBACK)
                .encoding(ENCODING)
                .text(TEXT)
                .serial(SERIAL)
                .senderCode(SENDER_CODE)
                .mediaCnt(MEDIA_CNT)
                .extSize(EXT_SIZE)
                .build();
        message.toByteBuf(byteBuf);

        HistDeliveryMessage resultMessage = new HistDeliveryMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistDeliveryMessage originalMessage = HistSmsDeliveryMessage.builder()
                                                                    .msgType(MSG_TYPE)
                                                                    .daAddr(DA_ADDR)
                                                                    .callback(CALLBACK)
                                                                    .encoding(ENCODING)
                                                                    .text(TEXT)
                                                                    .serial(SERIAL)
                                                                    .senderCode(SENDER_CODE)
                                                                    .mediaCnt(MEDIA_CNT)
                                                                    .extSize(EXT_SIZE)
                                                                    .build();
        originalMessage.toByteBuf(byteBuf);

        HistDeliveryMessage message = new HistSmsDeliveryMessage();
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
        HistDeliveryMessage message1 = HistSmsDeliveryMessage.builder()
                .msgType(MSG_TYPE)
                .daAddr(DA_ADDR)
                .callback(CALLBACK)
                .encoding(ENCODING)
                .text(TEXT)
                .serial(SERIAL)
                .senderCode(SENDER_CODE)
                .mediaCnt(MEDIA_CNT)
                .extSize(EXT_SIZE)
                .build();

        HistDeliveryMessage message2 = HistSmsDeliveryMessage.builder()
                .msgType(MSG_TYPE)
                .daAddr(DA_ADDR)
                .callback(CALLBACK)
                .encoding(ENCODING)
                .text(TEXT)
                .serial(SERIAL)
                .senderCode(SENDER_CODE)
                .mediaCnt(MEDIA_CNT)
                .extSize(EXT_SIZE)
                .build();

        HistDeliveryMessage message3 = HistSmsDeliveryMessage.builder()
                .msgType(MSG_TYPE)
                .daAddr(DA_ADDR)
                .callback(CALLBACK)
                .encoding(ENCODING)
                .text(TEXT + ", diff")
                .serial(SERIAL)
                .senderCode(SENDER_CODE)
                .mediaCnt(MEDIA_CNT)
                .extSize(EXT_SIZE)
                .build();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
