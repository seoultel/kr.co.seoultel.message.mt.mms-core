package kr.co.seoultel.message.mt.mms.core.message.smtnt.messages.delivery;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryAckMessage;

/**
 * {@link SmtntDeliveryAckMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntDeliveryAckMessageTest {

    /**
     * {@link SmtntDeliveryAckMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntDeliveryAckMessage message = new SmtntDeliveryAckMessage();
        assertNotNull(message);
        assertEquals(SMTNT_DELIVERY_ACK_METHOD_HEADER, message.getMethod());
        assertEquals("", message.getUserMsgId());
        assertEquals("", message.getUserMsgSubId());
        assertEquals(MMS_MSG_TYPE, message.getMsgType());
        assertEquals("", message.getPhoneNo());
        assertEquals(0, message.getResult());
        assertEquals("", message.getResultMessage());
        assertEquals("", message.getUserData());
    }

    /**
     * {@link SmtntDeliveryAckMessage}의 매개변수를 사용한 생성자를 테스트합니다.
     * 메시지와 매개변수가 올바르게 설정되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        String userMsgId = "12345";
        String userMsgSubId = "67890";
        String phoneNo = "01012345678";
        int result = DELIVERY_RESULT_SUCCESS;
        String resultMessage = "Message delivered successfully";
        String userData = "Test User Data";

        SmtntDeliveryAckMessage message = SmtntDeliveryAckMessage.builder()
                                                                    .userMsgId(userMsgId)
                                                                    .userMsgSubId(userMsgSubId)
                                                                    .phoneNo(phoneNo)
                                                                    .result(result)
                                                                    .resultMessage(resultMessage)
                                                                    .userData(userData)
                                                                    .build();

        assertNotNull(message);
        assertEquals(SMTNT_DELIVERY_ACK_METHOD_HEADER, message.getMethod());
        assertEquals(userMsgId, message.getUserMsgId());
        assertEquals(userMsgSubId, message.getUserMsgSubId());
        assertEquals(MMS_MSG_TYPE, message.getMsgType());
        assertEquals(phoneNo, message.getPhoneNo());
        assertEquals(result, message.getResult());
        assertEquals(resultMessage, message.getResultMessage());
        assertEquals(userData, message.getUserData());
    }

    /**
     * {@link SmtntDeliveryAckMessage#isSubmitSuccess()} 메서드를 테스트합니다.
     * 결과가 성공인지 올바르게 확인합니다.
     */
    @Test
    public void testIsSubmitSuccess() {
        SmtntDeliveryAckMessage successMessage = SmtntDeliveryAckMessage.builder().result(SmtntProtocol.DELIVERY_RESULT_SUCCESS).build();
        assertTrue(successMessage.isSubmitSuccess());

        SmtntDeliveryAckMessage failureMessage = SmtntDeliveryAckMessage.builder().result(DELIVERY_RESULT_MSG_SEND_FAIL).build();
        assertFalse(failureMessage.isSubmitSuccess());
    }

    /**
     * {@link SmtntDeliveryAckMessage#isDuplicated()} 메서드를 테스트합니다.
     * 결과가 중복 발송 에러인지 올바르게 확인합니다.
     */
    @Test
    public void testIsDuplicated() {
        SmtntDeliveryAckMessage duplicatedMessage = SmtntDeliveryAckMessage.builder().result(SmtntProtocol.DELIVERY_RESULT_DUP_SEND_ERR).build();
        assertTrue(duplicatedMessage.isDuplicated());

        SmtntDeliveryAckMessage nonDuplicatedMessage = SmtntDeliveryAckMessage.builder().result(SmtntProtocol.DELIVERY_RESULT_SUCCESS).build();
        assertFalse(nonDuplicatedMessage.isDuplicated());
    }

    /**
     * {@link SmtntDeliveryAckMessage#isTpsOver()} 메서드를 테스트합니다.
     * 결과가 TPS 초과인지 올바르게 확인합니다.
     */
    @Test
    public void testIsTpsOver() {
        SmtntDeliveryAckMessage tpsOverMessage = SmtntDeliveryAckMessage.builder()
                .result(SmtntProtocol.DELIVERY_RESULT_EXCEED_TPS)
                .build();
        assertTrue(tpsOverMessage.isTpsOver());

        SmtntDeliveryAckMessage speedOverMessage = SmtntDeliveryAckMessage.builder()
                .result(SmtntProtocol.DELIVERY_RESULT_EXCEED_SEND_SPEED)
                .build();
        assertTrue(speedOverMessage.isTpsOver());

        SmtntDeliveryAckMessage normalMessage = SmtntDeliveryAckMessage.builder()
                .result(SmtntProtocol.DELIVERY_RESULT_SUCCESS)
                .build();
        assertFalse(normalMessage.isTpsOver());
    }
}
