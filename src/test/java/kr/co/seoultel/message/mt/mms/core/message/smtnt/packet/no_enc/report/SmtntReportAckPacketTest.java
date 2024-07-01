package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.report;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import static kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.report.SmtntReportAckMessage;


/**
 * {@link SmtntPacket} SmtntReportAckPacketTest 클래스는 SmtntReportAckPacket에 대한 테스트를 제공합니다.
 */
public class SmtntReportAckPacketTest extends SmtntDefaultPacketTest {
    protected static final String USER_MSG_ID = "1234";
    protected static final String USER_MSG_SUB_ID = "315135";
    protected static final String SERVER_MSG_ID = "16103";
    protected static final SmtntReportAckMessage smtntReportAckMessage = SmtntReportAckMessage.builder()
                                                                                            .userMsgId(USER_MSG_ID)
                                                                                            .userMsgSubId(USER_MSG_SUB_ID)
                                                                                            .serverMsgId(SERVER_MSG_ID)
                                                                                            .result(DELIVERY_RESULT_SUCCESS)
                                                                                            .build();
    protected static final String jsonOfSmtntReportAckMessage = "{\"method\":\"ReportRes\",\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"ServerMsgId\":\"16103\",\"Result\":0}";
    protected static final String jsonOfSmtntReportAckPacket = "{\"Header\":{\"Method\":\"ReportRes\"},\"Body\":{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"ServerMsgId\":\"16103\",\"Result\":0}}";

    public static void main(String[] args) throws CryptoException {
        SmtntPacket smtntPacket = new SmtntPacket(smtntReportAckMessage);
        System.out.println(smtntPacket);
        System.out.println(smtntPacket.toJson());
    }

    /**
     * SmtntPacket의 생성자 {@link SmtntReportAckMessage} 를 테스트합니다.
     * 생성된 SmtntPacket의 헤더와 바디가 null이 아니어야 합니다.
     * 메서드는 일치해야 합니다.
     *
     * @throws CryptoException 암호화 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);

        assertNotNull(smtntReportAckPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNotNull(smtntReportAckPacket.getBody(), "SmtntPacket의 바디는 null이 아니어야 합니다.");

        assertEquals(SMTNT_REPORT_ACK_METHOD_HEADER, smtntReportAckPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        SmtntReportAckMessage smtntReportAckPacketBody = (SmtntReportAckMessage) smtntReportAckPacket.getBody();
        assertEquals(smtntReportAckMessage, smtntReportAckPacketBody);
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
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntReportAckPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_REPORT_ACK_METHOD_HEADER, smtntReportAckPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");
    }

    /**
     * SmtntPacket의 바디 값에 대한 테스트를 수행합니다.
     * 테스트 중에 생성된 SmtntPacket의 바디가 null이 아니어야 합니다.
     * SmtntReportAckMessage와 SmtntReportAckPacket의 Body는 일치해야 합니다.
     *
     * @throws CryptoException 암호화 예외가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketBodyVaule() throws CryptoException {
        // SmtntReportAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntReportAckPacket.getBody(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        SmtntReportAckMessage smtntReportAckPacketBody = (SmtntReportAckMessage) smtntReportAckPacket.getBody();
        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(smtntReportAckMessage, smtntReportAckPacketBody, "smtntReportAckMessage와 SmtntReportAckPacket의 Body는 일치해야 합니다.");
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
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);

        String smtntReportAckPacketJson = smtntReportAckPacket.toJson();
        assertEquals(smtntReportAckPacketJson, jsonOfSmtntReportAckPacket);
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
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);
        assertNotNull(smtntReportAckPacket);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(jsonOfSmtntReportAckPacket);

        assertNotNull(smtntPacket);
        assertEquals(smtntReportAckPacket, smtntPacket);
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
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);
        assertNotNull(smtntReportAckPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntReportAckPacket.toByteBuf(byteBuf);

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
        SmtntPacket smtntReportAckPacket = new SmtntPacket(smtntReportAckMessage);
        assertNotNull(smtntReportAckPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntReportAckPacket.toByteBuf(byteBuf);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromByteBuf(byteBuf);

        assertEquals(smtntReportAckPacket, smtntPacket);
    }
}
