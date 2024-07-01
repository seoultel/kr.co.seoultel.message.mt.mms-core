package kr.co.seoultel.message.mt.mms.core.message.lghv.messages.report;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.common.protocol.LghvProtocol;
import static kr.co.seoultel.message.mt.mms.core.common.protocol.LghvProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.report.LghvReportAckMessage;

/**
 * {@link LghvReportAckMessage}에 대한 테스트 클래스입니다.
 */
public class LghvReportAckMessageTest {

    protected static final String DEFAULT_RESULT = "RSLT";
    protected static final String DEFAULT_MSG_ID = "MSG_ID";
    protected static final String DEFAULT_RESERVED = "";


    /**
     * {@link LghvReportAckMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvReportAckMessage lghvReportMessage = new LghvReportAckMessage();

        assertNotNull(lghvReportMessage);
        assertEquals(lghvReportMessage.getMsgType(), REPORT_ACK_MSG_TYPE);
        assertEquals(lghvReportMessage.getMsgLen(), REPORT_ACK_BODY_LEN);

        assertNotNull(lghvReportMessage.getResult());
        assertNotNull(lghvReportMessage.getMsgId());
        assertNotNull(lghvReportMessage.getReserved());
    }

    @Test
    public void testParameterizedConstructor() {
        LghvReportAckMessage lghvReportAckMessage = new LghvReportAckMessage(DEFAULT_RESULT, DEFAULT_MSG_ID, DEFAULT_RESERVED);

        assertNotNull(lghvReportAckMessage);
        assertEquals(lghvReportAckMessage.getMsgType(), LghvProtocol.REPORT_ACK_MSG_TYPE);
        assertEquals(lghvReportAckMessage.getMsgLen(), REPORT_ACK_BODY_LEN);

        assertNotNull(lghvReportAckMessage.getResult());
        assertNotNull(lghvReportAckMessage.getMsgId());
        assertNotNull(lghvReportAckMessage.getReserved());

        assertEquals(lghvReportAckMessage.getResult(), DEFAULT_RESULT);
        assertEquals(lghvReportAckMessage.getMsgId(), DEFAULT_MSG_ID);
        assertEquals(lghvReportAckMessage.getReserved(), DEFAULT_RESERVED);
    }

    @Test
    public void testEqualsAndHashCode() {
        LghvReportAckMessage message1 = new LghvReportAckMessage(DEFAULT_RESULT, DEFAULT_MSG_ID, DEFAULT_RESERVED);
        LghvReportAckMessage message2 = new LghvReportAckMessage(DEFAULT_RESULT, DEFAULT_MSG_ID, DEFAULT_RESERVED);
        LghvReportAckMessage message3 = new LghvReportAckMessage();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
