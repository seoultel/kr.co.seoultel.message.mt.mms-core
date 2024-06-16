package kr.co.seoultel.message.mt.mms.core.message.hist.messages.delivery;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryAckMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistDeliveryAckMessageTest {

    private static final String RESULT = "OK";
    private static final String DA_ADDR = "01012345678";
    private static final String SERIAL = "Serial123";

    @Test
    public void testConstructor() {
        HistDeliveryAckMessage message = new HistDeliveryAckMessage();

        assertNotNull(message);
        assertEquals(HistProtocol.HIST_DELIVERY_ACK_HEAD_TYPE, message.getHeadType());
        assertEquals(HistProtocol.HIST_DELIVERY_ACK_MSG_LENG, message.getMsgLeng());
        assertEquals("", message.getResult());
        assertEquals("", message.getDaAddr());
        assertEquals("", message.getSerial());
    }

    @Test
    public void testParameterizedConstructor() {
        HistDeliveryAckMessage message = new HistDeliveryAckMessage(RESULT, DA_ADDR, SERIAL);

        assertNotNull(message);
        assertEquals(HistProtocol.HIST_DELIVERY_ACK_HEAD_TYPE, message.getHeadType());
        assertEquals(HistProtocol.HIST_DELIVERY_ACK_MSG_LENG, message.getMsgLeng());
        assertEquals(RESULT, message.getResult());
        assertEquals(DA_ADDR, message.getDaAddr());
        assertEquals(SERIAL, message.getSerial());
    }

    @Test
    public void testToByteBuf() {
        HistDeliveryAckMessage message = new HistDeliveryAckMessage(RESULT, DA_ADDR, SERIAL);

        ByteBuf byteBuf = Unpooled.buffer();
        message.toByteBuf(byteBuf);

        HistDeliveryAckMessage resultMessage = new HistDeliveryAckMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistDeliveryAckMessage originalMessage = new HistDeliveryAckMessage(RESULT, DA_ADDR, SERIAL);
        originalMessage.toByteBuf(byteBuf);

        HistDeliveryAckMessage message = new HistDeliveryAckMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(RESULT, message.getResult());
        assertEquals(DA_ADDR, message.getDaAddr());
        assertEquals(SERIAL, message.getSerial());
    }

    @Test
    public void testEqualsAndHashCode() {
        HistDeliveryAckMessage message1 = new HistDeliveryAckMessage(RESULT, DA_ADDR, SERIAL);
        HistDeliveryAckMessage message2 = new HistDeliveryAckMessage(RESULT, DA_ADDR, SERIAL);
        HistDeliveryAckMessage message3 = new HistDeliveryAckMessage("DIFFERENT", "01098765432", "DIFFERENT");

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
