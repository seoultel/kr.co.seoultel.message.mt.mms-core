package kr.co.seoultel.message.mt.mms.core.message.hist.messages.report;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.report.HistReportAckMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistReportAckMessageTest {

    private static final String RESULT = "OK";
    private static final String SERIAL = "Serial123";
    private static final String GW_SERIAL = "GwSerial123";

    @Test
    public void testConstructor() {
        HistReportAckMessage message = new HistReportAckMessage();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_REPORT_ACK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_REPORT_ACK_MSG_LENG);
        assertEquals(message.getResult(), "");
        assertEquals(message.getSerial(), "");
        assertEquals(message.getGwSerial(), "");
    }

    @Test
    public void testParameterizedConstructor() {
        HistReportAckMessage message = new HistReportAckMessage(RESULT, SERIAL, GW_SERIAL);

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_REPORT_ACK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_REPORT_ACK_MSG_LENG);
        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getSerial(), SERIAL);
        assertEquals(message.getGwSerial(), GW_SERIAL);
    }

    @Test
    public void testToByteBuf() {
        HistReportAckMessage message = new HistReportAckMessage(RESULT, SERIAL, GW_SERIAL);

        ByteBuf byteBuf = Unpooled.buffer();
        message.toByteBuf(byteBuf);

        HistReportAckMessage resultMessage = new HistReportAckMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistReportAckMessage histReportAckMessage = new HistReportAckMessage(RESULT, SERIAL, GW_SERIAL);
        histReportAckMessage.toByteBuf(byteBuf);

        HistReportAckMessage message = new HistReportAckMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getSerial(), SERIAL);
        assertEquals(message.getGwSerial(), GW_SERIAL);
    }

    @Test
    public void testEqualsAndHashCode() {
        HistReportAckMessage message1 = new HistReportAckMessage(RESULT, SERIAL, GW_SERIAL);
        HistReportAckMessage message2 = new HistReportAckMessage(RESULT, SERIAL, GW_SERIAL);
        HistReportAckMessage message3 = new HistReportAckMessage("ERROR", "DifferentSerial", "DifferentGwSerial");

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
