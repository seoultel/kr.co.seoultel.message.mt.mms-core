package kr.co.seoultel.message.mt.mms.core.message.smtnt.messages.report;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.report.SmtntReportAckMessage;

/**
 * {@link SmtntReportAckMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntReportAckMessageTest {

    /**
     * {@link SmtntReportAckMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntReportAckMessage smtntReportAckMessage = new SmtntReportAckMessage();
        assertNotNull(smtntReportAckMessage);
        assertEquals(SMTNT_REPORT_ACK_METHOD_HEADER, smtntReportAckMessage.getMethod());
        assertEquals("", smtntReportAckMessage.getUserMsgId());
        assertEquals("", smtntReportAckMessage.getUserMsgSubId());
        assertEquals("", smtntReportAckMessage.getServerMsgId());
        assertEquals(0, smtntReportAckMessage.getResult());
    }

    /**
     * {@link SmtntReportAckMessage}의 매개변수를 사용한 생성자를 테스트합니다.
     * 메시지와 매개변수가 올바르게 설정되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        String userMsgId = "12345";
        String userMsgSubId = "67890";
        String serverMsgId = "789012";
        int result = 1;

        SmtntReportAckMessage smtntReportAckMessage = SmtntReportAckMessage.builder()
                                                            .userMsgId(userMsgId)
                                                            .userMsgSubId(userMsgSubId)
                                                            .serverMsgId(serverMsgId)
                                                            .result(result)
                                                            .build();

        assertNotNull(smtntReportAckMessage);
        assertEquals(SMTNT_REPORT_ACK_METHOD_HEADER, smtntReportAckMessage.getMethod());
        assertEquals(userMsgId, smtntReportAckMessage.getUserMsgId());
        assertEquals(userMsgSubId, smtntReportAckMessage.getUserMsgSubId());
        assertEquals(serverMsgId, smtntReportAckMessage.getServerMsgId());
        assertEquals(result, smtntReportAckMessage.getResult());
    }
}

