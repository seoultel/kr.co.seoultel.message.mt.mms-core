package kr.co.seoultel.message.mt.mms.core.message.smtnt.messages.report;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import static kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.report.SmtntReportMessage;

/**
 * {@link SmtntReportMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntReportMessageTest {

    /**
     * {@link SmtntReportMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntReportMessage smtntReportMessage = new SmtntReportMessage();
        assertNotNull(smtntReportMessage);
        assertEquals(SMTNT_REPORT_METHOD_HEADER, smtntReportMessage.getMethod());
        assertEquals("", smtntReportMessage.getUserMsgId());
        assertEquals("", smtntReportMessage.getUserMsgSubId());
        assertEquals("", smtntReportMessage.getServerMsgId());
        assertEquals(0, smtntReportMessage.getMsgType());
        assertEquals("", smtntReportMessage.getPhoneNo());
        assertEquals(0, smtntReportMessage.getResult());
        assertEquals("", smtntReportMessage.getResultMessage());
        assertEquals("", smtntReportMessage.getTelecom());
        assertEquals("", smtntReportMessage.getDeliveryTime());
        assertEquals("", smtntReportMessage.getUserId());
        assertEquals("", smtntReportMessage.getUserData());
    }

    /**
     * {@link SmtntReportMessage}의 매개변수를 사용한 생성자를 테스트합니다.
     * 메시지와 매개변수가 올바르게 설정되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        String userMsgId = "12345";
        String userMsgSubId = "67890";
        String serverMsgId = "789012";
        int msgType = 1;
        String phoneNo = "01012345678";
        int result = 1;
        String resultMessage = "Message delivered successfully";
        String telecom = "SKT";
        String deliveryTime = "2024-06-10 10:00:00";
        String userData = "Test User Data";
        String userId = "testUserId";

        SmtntReportMessage smtntReportMessage = SmtntReportMessage.builder()
                                                                    .userMsgId(userMsgId)
                                                                    .userMsgSubId(userMsgSubId)
                                                                    .serverMsgId(serverMsgId)
                                                                    .msgType(msgType)
                                                                    .phoneNo(phoneNo)
                                                                    .result(result)
                                                                    .resultMessage(resultMessage)
                                                                    .telecom(telecom)
                                                                    .deliveryTime(deliveryTime)
                                                                    .userData(userData)
                                                                    .userId(userId)
                                                                    .build();

        assertNotNull(smtntReportMessage);
        assertEquals(SMTNT_REPORT_METHOD_HEADER, smtntReportMessage.getMethod());
        assertEquals(userMsgId, smtntReportMessage.getUserMsgId());
        assertEquals(userMsgSubId, smtntReportMessage.getUserMsgSubId());
        assertEquals(serverMsgId, smtntReportMessage.getServerMsgId());
        assertEquals(msgType, smtntReportMessage.getMsgType());
        assertEquals(phoneNo, smtntReportMessage.getPhoneNo());
        assertEquals(result, smtntReportMessage.getResult());
        assertEquals(resultMessage, smtntReportMessage.getResultMessage());
        assertEquals(telecom, smtntReportMessage.getTelecom());
        assertEquals(deliveryTime, smtntReportMessage.getDeliveryTime());
        assertEquals(userData, smtntReportMessage.getUserData());
        assertEquals(userId, smtntReportMessage.getUserId());
    }

    /**
     * {@link SmtntReportMessage#isTpsOver()} 메서드를 테스트합니다.
     * 결과가 TPS 초과인지 올바르게 확인합니다.
     */
    @Test
    public void testIsTpsOver() {
        SmtntReportMessage tpsOverMessage = SmtntReportMessage.builder()
                .result(SmtntProtocol.DELIVERY_RESULT_EXCEED_TPS)
                .build();
        assertTrue(tpsOverMessage.isTpsOver());

        SmtntReportMessage speedOverMessage = SmtntReportMessage.builder()
                .result(SmtntProtocol.DELIVERY_RESULT_EXCEED_SEND_SPEED)
                .build();
        assertTrue(speedOverMessage.isTpsOver());

        SmtntReportMessage normalMessage = SmtntReportMessage.builder()
                .result(SmtntProtocol.DELIVERY_RESULT_SUCCESS)
                .build();
        assertFalse(normalMessage.isTpsOver());
    }
}
