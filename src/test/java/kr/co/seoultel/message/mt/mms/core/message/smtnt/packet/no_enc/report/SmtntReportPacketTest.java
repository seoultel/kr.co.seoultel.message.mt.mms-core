package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.report;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.report.SmtntReportMessage;
import kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SmtntPacket} SmtntReportPacketTest 클래스는 SmtntReportPacket 에 대한 테스트를 제공합니다.
 */
public class SmtntReportPacketTest extends SmtntDefaultPacketTest {
    // 테스트에 사용할 기본값
    private static final String DEFAULT_USER_MSG_ID = "1234";
    private static final String DEFAULT_USER_MSG_SUB_ID = "315135";
    private static final String DEFAULT_SERVER_MSG_ID = "16103";
    private static final int DEFAULT_MSG_TYPE = 6;
    private static final String DEFAULT_PHONE_NO = "01012345678";
    private static final int DEFAULT_RESULT = DELIVERY_RESULT_SUCCESS;
    private static final String DEFAULT_RESULT_MESSAGE = "OK";
    private static final String DEFAULT_TELECOM = "KT";
    private static final String DEFAULT_DELIVERY_TIME = "2024-06-09T18:31:07.776674";
    private static final String DEFAULT_USER_ID = "userId";
    private static final String DEFAULT_USER_DATA = "LMS";

    protected static final SmtntReportMessage smtntReportMessage = SmtntReportMessage.builder()
                                                                                            .userMsgId(DEFAULT_USER_MSG_ID)
                                                                                                .userMsgSubId(DEFAULT_USER_MSG_SUB_ID)
                                                                                                .serverMsgId(DEFAULT_SERVER_MSG_ID)
                                                                                                .msgType(DEFAULT_MSG_TYPE)
                                                                                                .phoneNo(DEFAULT_PHONE_NO)
                                                                                                .result(DEFAULT_RESULT)
                                                                                                .resultMessage(DEFAULT_RESULT_MESSAGE)
                                                                                                .telecom(DEFAULT_TELECOM)
                                                                                                .deliveryTime(DEFAULT_DELIVERY_TIME)
                                                                                                .userId(DEFAULT_USER_ID)
                                                                                                .userData(DEFAULT_USER_DATA)
                                                                                                .build();

    protected static final String jsonOfSmtntReportMessage = "{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"ServerMsgId\":\"16103\",\"MsgType\":6,\"PhoneNo\":\"01012345678\",\"Result\":0,\"ResultMessage\":\"OK\",\"Telecom\":\"KT\",\"DeliveryTime\":\"2024-06-09T18:31:07.776674\",\"UserId\":\"userId\",\"UserData\":\"LMS\"}";
    protected static final String jsonOfSmtntReportPacket = "{\"Header\":{\"Method\":\"ReportReq\"},\"Body\":{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"ServerMsgId\":\"16103\",\"MsgType\":6,\"PhoneNo\":\"01012345678\",\"Result\":0,\"ResultMessage\":\"OK\",\"Telecom\":\"KT\",\"DeliveryTime\":\"2024-06-09T18:31:07.776674\",\"UserId\":\"userId\",\"UserData\":\"LMS\"}}";


    /**
     * SmtntPacket의 생성자 {@link SmtntReportMessage} 를 테스트합니다.
     * 생성된 SmtntPacket의 헤더와 바디가 null이 아니어야 합니다.
     * 메서드는 일치해야 합니다.
     *
     * @throws CryptoException 암호화 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);

        assertNotNull(smtntReportPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNotNull(smtntReportPacket.getBody(), "SmtntPacket의 바디는 null이 아니어야 합니다.");

        assertEquals(SMTNT_REPORT_METHOD_HEADER, smtntReportPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        SmtntReportMessage smtntReportPacketBody = (SmtntReportMessage) smtntReportPacket.getBody();
        assertEquals(smtntReportPacketBody, smtntReportMessage);
    }

    /**
     * SmtntPacket의 헤더 값에 대한 테스트를 수행합니다.
     * 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아니어야 합니다.
     * 메서드는 일치해야 합니다.
     *
     * @throws CryptoException 암호화 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketHeaderVaule() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntReportPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_REPORT_METHOD_HEADER, smtntReportPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");
    }

    /**
     * SmtntPacket의 바디 값에 대한 테스트를 수행합니다.
     * 테스트 중에 생성된 SmtntPacket의 바디가 null이 아니어야 합니다.
     * SmtntReportMessage와 SmtntReportPacket의 Body는 일치해야 합니다.
     *
     * @throws CryptoException 암호화 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketBodyVaule() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntReportPacket.getBody(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        SmtntReportMessage smtntReportPacketBody = (SmtntReportMessage) smtntReportPacket.getBody();
        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(smtntReportMessage, smtntReportPacketBody, "smtntReportMessage와 SmtntReportPacket의 Body는 일치해야 합니다.");
    }

    /**
     * SmtntPacket을 JSON 형식으로 변환하는 메서드를 테스트합니다.
     * 생성된 JSON 문자열과 기대값이 일치해야 합니다.
     *
     * @throws CryptoException 암호화 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertToJson() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);

        String smtntReportPacketJson = smtntReportPacket.toJson();
        assertEquals(smtntReportPacketJson, jsonOfSmtntReportPacket);
    }

    /**
     * JSON 형식의 문자열을 SmtntPacket으로 변환하는 메서드를 테스트합니다.
     * 변환된 SmtntPacket이 예상한 값과 동일해야 합니다.
     *
     * @throws Exception 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromJson() throws Exception {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);
        assertNotNull(smtntReportPacket);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(jsonOfSmtntReportPacket);

        assertNotNull(smtntPacket);
         assertEquals(smtntReportPacket, smtntPacket);
    }

    /**
     * SmtntPacket을 ByteBuf로 변환하는 메서드를 테스트합니다.
     * 변환된 ByteBuf의 writerIndex는 1 이상이어야 합니다.
     *
     * @throws Exception 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertToByteBuf() throws Exception {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);
        assertNotNull(smtntReportPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntReportPacket.toByteBuf(byteBuf);

        assertTrue(byteBuf.writerIndex() >= 1);
    }

    /**
     * ByteBuf를 SmtntPacket으로 변환하는 메서드를 테스트합니다.
     * 변환된 SmtntPacket이 예상한 값과 동일해야 합니다.
     *
     * @throws Exception 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromByteBuf() throws Exception {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportPacket = new SmtntPacket(smtntReportMessage);
        assertNotNull(smtntReportPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntReportPacket.toByteBuf(byteBuf);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromByteBuf(byteBuf);

        assertEquals(smtntReportPacket, smtntPacket);
    }
}
