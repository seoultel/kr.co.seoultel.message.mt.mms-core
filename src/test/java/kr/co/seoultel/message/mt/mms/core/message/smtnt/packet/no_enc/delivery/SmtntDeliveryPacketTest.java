package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.delivery;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.lms.SmtntLmsMessage;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import org.junit.jupiter.api.Test;


import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SmtntPacket} SmtntDeliveryPacketTest 클래스는 SmtntDeliveryPacket 에 대한 테스트를 제공합니다.
 */
public class SmtntDeliveryPacketTest extends SmtntDefaultPacketTest {

    // 테스트에 사용할 기본값
    private static final String DEFAULT_USER_MSG_ID = "1234";
    private static final String DEFAULT_USER_MSG_SUB_ID = "315135";
    private static final String DEFAULT_PHONE_NO = "01012345678";
    private static final String DEFAULT_SUBJECT = "제목 없음";
    private static final String DEFAULT_TRANS_ID = "testArreo";
    private static final String DEFAULT_ORIGIN_CODE = "123456789";

    protected static final SmtntDeliveryMessage smtntDeliveryMessage = SmtntLmsMessage.builder()
                                                                                    .userMsgId(DEFAULT_USER_MSG_ID)
                                                                                    .userMsgSubId(DEFAULT_USER_MSG_SUB_ID)
                                                                                    .phoneNo(DEFAULT_PHONE_NO)
                                                                                    .callbackNo(DEFAULT_PHONE_NO)
                                                                                    .subject(DEFAULT_SUBJECT)
                                                                                    .message("")
                                                                                    .tranId(DEFAULT_TRANS_ID)
                                                                                    .userData("LMS")
                                                                                    .resellerCode(DEFAULT_ORIGIN_CODE)
                                                                                    .build();


    protected static final String jsonOfsmtntDeliveryMessage = "{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"MsgType\":6,\"PhoneNo\":\"01012345678\",\"CallbackNo\":\"01012345678\",\"TranId\":\"testArreo\",\"Subject\":\"제목 없음\",\"Message\":\"\",\"KakaoAdFlag\":\"\",\"KakaoNationCode\":\"\",\"KakaoSenderKey\":\"\",\"KakaoTemplateCode\":\"\",\"KakaoTimeout\":0,\"KakaoButton\":\"\",\"FileCount\":0,\"FileType1\":\"\",\"FileName1\":\"\",\"FileType2\":\"\",\"FileName2\":\"\",\"FileType3\":\"\",\"FileName3\":\"\",\"UserData\":\"LMS\",\"ResellerCode\":\"123456789\"}";
    protected static final String jsonOfsmtntDeliveryPacket = "{\"Header\":{\"Method\":\"MsgSendReq\"},\"Body\":{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"MsgType\":6,\"PhoneNo\":\"01012345678\",\"CallbackNo\":\"01012345678\",\"TranId\":\"testArreo\",\"Subject\":\"제목 없음\",\"Message\":\"\",\"KakaoAdFlag\":\"\",\"KakaoNationCode\":\"\",\"KakaoSenderKey\":\"\",\"KakaoTemplateCode\":\"\",\"KakaoTimeout\":0,\"KakaoButton\":\"\",\"FileCount\":0,\"FileType1\":\"\",\"FileName1\":\"\",\"FileType2\":\"\",\"FileName2\":\"\",\"FileType3\":\"\",\"FileName3\":\"\",\"UserData\":\"LMS\",\"ResellerCode\":\"123456789\"}}";


    /**
     * SmtntPacket의 생성자 {@link SmtntDeliveryMessage}에 대한 테스트 케이스입니다.
     * SmtntPacket의 생성이 올바른지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);

        assertNotNull(smtntDeliveryPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNotNull(smtntDeliveryPacket.getBody(), "SmtntPacket의 바디는 null이 아니어야 합니다.");

        assertEquals(SMTNT_DELIVERY_METHOD_HEADER, smtntDeliveryPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        SmtntDeliveryMessage smtntDeliveryPacketBody = (SmtntDeliveryMessage) smtntDeliveryPacket.getBody();
        assertEquals(smtntDeliveryPacketBody, smtntDeliveryMessage);
    }

    /**
     * SmtntPacket의 헤더 값에 대한 테스트 케이스입니다.
     * 생성된 SmtntPacket의 헤더 메서드가 올바른지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketHeaderVaule() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntDeliveryPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_DELIVERY_METHOD_HEADER, smtntDeliveryPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");
    }


    /**
     * SmtntPacket의 바디 값에 대한 테스트 케이스입니다.
     * 생성된 SmtntPacket의 바디가 smtntDeliveryMessage와 일치하는지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketBodyVaule() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntDeliveryPacket.getBody(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        SmtntDeliveryMessage smtntDeliveryPacketBody = (SmtntDeliveryMessage) smtntDeliveryPacket.getBody();
        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(smtntDeliveryMessage, smtntDeliveryPacketBody, "smtntDeliveryMessage와 smtntDeliveryPacket의 Body는 일치해야 합니다.");
    }

    /**
     * SmtntPacket을 JSON으로 변환하는 테스트 케이스입니다.
     *
     * SmtntPacket을 JSON으로 변환하는 테스트 케이스입니다.
     * SmtntPacket의 JSON 표현이 예상한 JSON 문자열과 일치하는지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertToJson() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);

        String smtntDeliveryPacketJson = smtntDeliveryPacket.toJson();
        assertEquals(smtntDeliveryPacketJson, jsonOfsmtntDeliveryPacket);
    }

    /**
     * JSON에서 SmtntPacket을 복원하는 테스트 케이스입니다.
     * JSON에서 재구성된 SmtntPacket이 원래의 SmtntPacket과 일치하는지 확인합니다.
     *
     * @throws Exception 테스트 중에 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromJson() throws Exception {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);
        assertNotNull(smtntDeliveryPacket);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(jsonOfsmtntDeliveryPacket);

        SmtntDeliveryMessage smtntDeliveryPacketBody = (SmtntDeliveryMessage) smtntDeliveryPacket.getBody();
        SmtntDeliveryMessage smtntPacketBody = (SmtntDeliveryMessage) smtntPacket.getBody();

        assertNotNull(smtntPacket);
        assertTrue(smtntDeliveryPacketBody.equals(smtntPacketBody));
    }

    /**
     * SmtntPacket을 ByteBuf로 변환하는 테스트 케이스입니다.
     * SmtntPacket을 ByteBuf로 변환하고 길이가 1 이상인지 확인합니다.
     *
     * @throws Exception 테스트 중에 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertToByteBuf() throws Exception {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);
        assertNotNull(smtntDeliveryPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntDeliveryPacket.toByteBuf(byteBuf);

        assertTrue(byteBuf.writerIndex() >= 1);
    }

    /**
     * ByteBuf에서 SmtntPacket을 복원하는 테스트 케이스입니다.
     * 생성된 SmtntPacket이 원래의 SmtntPacket과 일치하는지 확인합니다.
     *
     * @throws Exception 테스트 중에 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromByteBuf() throws Exception {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryPacket = new SmtntPacket(smtntDeliveryMessage);
        assertNotNull(smtntDeliveryPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntDeliveryPacket.toByteBuf(byteBuf);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromByteBuf(byteBuf);

        assertEquals(smtntDeliveryPacket, smtntPacket);
    }
}
