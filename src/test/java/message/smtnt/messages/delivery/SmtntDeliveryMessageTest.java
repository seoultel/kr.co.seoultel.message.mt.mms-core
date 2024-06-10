package message.smtnt.messages.delivery;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.MMS_MSG_TYPE;
import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_DELIVERY_METHOD_HEADER;

/**
 * {@link SmtntDeliveryMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntDeliveryMessageTest {

    /**
     * {@link SmtntDeliveryMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntDeliveryMessage smtntDeliveryMessage = new SmtntDeliveryMessage();
        assertNotNull(smtntDeliveryMessage);
        assertEquals(SMTNT_DELIVERY_METHOD_HEADER, smtntDeliveryMessage.getMethod());
        assertEquals("", smtntDeliveryMessage.getUserMsgId());
        assertEquals("", smtntDeliveryMessage.getUserMsgSubId());
        assertEquals(MMS_MSG_TYPE, smtntDeliveryMessage.getMsgType());
        assertEquals("", smtntDeliveryMessage.getPhoneNo());
        assertEquals("", smtntDeliveryMessage.getCallbackNo());
        assertEquals("", smtntDeliveryMessage.getTranId());
        assertEquals("제목 없음", smtntDeliveryMessage.getSubject());
        assertEquals("", smtntDeliveryMessage.getMessage());
        assertEquals("", smtntDeliveryMessage.getKakaoAdFlag());
        assertEquals("", smtntDeliveryMessage.getKakaoNationCode());
        assertEquals("", smtntDeliveryMessage.getKakaoSenderKey());
        assertEquals("", smtntDeliveryMessage.getKakaoTemplateCode());
        assertEquals(0, smtntDeliveryMessage.getKakaoTimeout());
        assertEquals("", smtntDeliveryMessage.getKakaoButton());
        assertEquals(0, smtntDeliveryMessage.getFileCount());
        assertEquals("", smtntDeliveryMessage.getFileType1());
        assertEquals("", smtntDeliveryMessage.getFileName1());
        assertEquals("", smtntDeliveryMessage.getFileType2());
        assertEquals("", smtntDeliveryMessage.getFileName2());
        assertEquals("", smtntDeliveryMessage.getFileType3());
        assertEquals("", smtntDeliveryMessage.getFileName3());
        assertEquals("", smtntDeliveryMessage.getUserData());
        assertEquals("", smtntDeliveryMessage.getResellerCode());
    }

    /**
     * {@link SmtntDeliveryMessage}의 매개변수를 사용한 생성자를 테스트합니다.
     * 메시지와 매개변수가 올바르게 설정되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        String userMsgId = "12345";
        String userMsgSubId = "67890";
        int msgType = MMS_MSG_TYPE;
        String phoneNo = "01012345678";
        String callbackNo = "01098765432";
        String tranId = "abc123";
        String subject = "Test Subject";
        String message = "Test Message";
        String kakaoAdFlag = "Y";
        String kakaoNationCode = "82";
        String kakaoSenderKey = "senderKey123";
        String kakaoTemplateCode = "templateCode456";
        int kakaoTimeout = 30;
        String kakaoButton = "Test Button";
        int fileCount = 2;
        String fileType1 = "IMG";
        String fileName1 = "image1.png";
        String fileType2 = "IMG";
        String fileName2 = "image2.png";
        String fileType3 = "IMG";
        String fileName3 = "image3.png";
        String userData = "Test User Data";
        String resellerCode = "reseller123";

        SmtntDeliveryMessage smtntDeliveryMessage = new SmtntDeliveryMessage(userMsgId, userMsgSubId, msgType, phoneNo, callbackNo,
                                                                            tranId, subject, message, kakaoAdFlag, kakaoNationCode,
                                                                            kakaoSenderKey, kakaoTemplateCode, kakaoTimeout,
                                                                            kakaoButton, fileCount, fileType1, fileName1,
                                                                            fileType2, fileName2, fileType3, fileName3, userData,
                                                                            resellerCode);
        assertNotNull(message);
        assertEquals(SMTNT_DELIVERY_METHOD_HEADER, smtntDeliveryMessage.getMethod());
        assertEquals(userMsgId, smtntDeliveryMessage.getUserMsgId());
        assertEquals(userMsgSubId, smtntDeliveryMessage.getUserMsgSubId());
        assertEquals(msgType, smtntDeliveryMessage.getMsgType());
        assertEquals(phoneNo, smtntDeliveryMessage.getPhoneNo());
        assertEquals(callbackNo, smtntDeliveryMessage.getCallbackNo());
        assertEquals(tranId, smtntDeliveryMessage.getTranId());
        assertEquals(subject, smtntDeliveryMessage.getSubject());
        assertEquals(message, smtntDeliveryMessage.getMessage());
        assertEquals(kakaoAdFlag, smtntDeliveryMessage.getKakaoAdFlag());
        assertEquals(kakaoNationCode, smtntDeliveryMessage.getKakaoNationCode());
        assertEquals(kakaoSenderKey, smtntDeliveryMessage.getKakaoSenderKey());
        assertEquals(kakaoTemplateCode, smtntDeliveryMessage.getKakaoTemplateCode());
        assertEquals(kakaoTimeout, smtntDeliveryMessage.getKakaoTimeout());
        assertEquals(kakaoButton, smtntDeliveryMessage.getKakaoButton());
        assertEquals(fileCount, smtntDeliveryMessage.getFileCount());
        assertEquals(fileType1, smtntDeliveryMessage.getFileType1());
        assertEquals(fileName1, smtntDeliveryMessage.getFileName1());
        assertEquals(fileType2, smtntDeliveryMessage.getFileType2());
        assertEquals(fileName2, smtntDeliveryMessage.getFileName2());
        assertEquals(fileType3, smtntDeliveryMessage.getFileType3());
        assertEquals(fileName3, smtntDeliveryMessage.getFileName3());
        assertEquals(userData, smtntDeliveryMessage.getUserData());
        assertEquals(resellerCode, smtntDeliveryMessage.getResellerCode());
    }
}
