package kr.co.seoultel.message.mt.mms.core.message.hist.messages.report;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.report.HistReportMessage;

import static org.junit.jupiter.api.Assertions.*;

public class HistReportMessageTest {

    private static final String RESULT = "OK";
    private static final String MESSAGE = "Message";
    private static final String DA_ADDR = "01012345678";
    private static final String SERIAL = "Serial123";
    private static final String GW_SERIAL = "GwSerial123";
    private static final String SEND_TIME = "20230610123000";
    private static final String TELCO_INFO = "Telc";

    @Test
    public void testConstructor() {
        HistReportMessage message = new HistReportMessage();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_REPORT_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_REPORT_MSG_LENG);
        assertEquals(message.getResult(), "");
        assertEquals(message.getMessage(), "");
        assertEquals(message.getDaAddr(), "");
        assertEquals(message.getSerial(), "");
        assertEquals(message.getGwSerial(), "");
        assertEquals(message.getSendTime(), "");
        assertEquals(message.getTelcoInfo(), "");
    }

    @Test
    public void testParameterizedConstructor() {
        HistReportMessage message = new HistReportMessage(RESULT, MESSAGE, DA_ADDR, SERIAL, GW_SERIAL, SEND_TIME, TELCO_INFO);

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_REPORT_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_REPORT_MSG_LENG);
        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getMessage(), MESSAGE);
        assertEquals(message.getDaAddr(), DA_ADDR);
        assertEquals(message.getSerial(), SERIAL);
        assertEquals(message.getGwSerial(), GW_SERIAL);
        assertEquals(message.getSendTime(), SEND_TIME);
        assertEquals(message.getTelcoInfo(), TELCO_INFO);
    }

    @Test
    public void testToByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistReportMessage message = new HistReportMessage(RESULT, MESSAGE, DA_ADDR, SERIAL, GW_SERIAL, SEND_TIME, TELCO_INFO);
        message.toByteBuf(byteBuf);

        HistReportMessage resultMessage = new HistReportMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistReportMessage histReportMessage = new HistReportMessage(RESULT, MESSAGE, DA_ADDR, SERIAL, GW_SERIAL, SEND_TIME, TELCO_INFO);
        histReportMessage.toByteBuf(byteBuf);

        HistReportMessage message = new HistReportMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getMessage(), MESSAGE);
        assertEquals(message.getDaAddr(), DA_ADDR);
        assertEquals(message.getSerial(), SERIAL);
        assertEquals(message.getGwSerial(), GW_SERIAL);
        assertEquals(message.getSendTime(), SEND_TIME);
        assertEquals(message.getTelcoInfo(), TELCO_INFO);
    }

    @Test
    public void testEqualsAndHashCode() {
        HistReportMessage message1 = new HistReportMessage(RESULT, MESSAGE, DA_ADDR, SERIAL, GW_SERIAL, SEND_TIME, TELCO_INFO);

        HistReportMessage message2 = new HistReportMessage(RESULT, MESSAGE, DA_ADDR, SERIAL, GW_SERIAL, SEND_TIME, TELCO_INFO);

        HistReportMessage message3 = new HistReportMessage("ERROR", "DifferentMessage", "01087654321", "DifferentSerial", "DifferentGwSerial", "20240610123000", "DifferentTelcoInfo");

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
