package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.delivery;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.DELIVERY_RESULT_SUCCESS;
import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_DELIVERY_ACK_METHOD_HEADER;
import static org.junit.jupiter.api.Assertions.*;


/**
 * {@link SmtntPacket} SmtntDeliveryAckPacketTest 클래스는 SmtntDeliveryAckPacket 에 대한 테스트를 제공합니다.
 */
public class SmtntDeliveryAckPacketTest extends SmtntDefaultPacketTest {
    protected static final String USER_MSG_ID = "1234";
    protected static final String USER_MSG_SUB_ID = "315135";
    protected static final String SERVER_MSG_ID = "16103";
    protected static final String USER_DATA = "LMS";

    protected static final SmtntDeliveryAckMessage smtntDeliveryAckMessage = SmtntDeliveryAckMessage.builder()
                                                                                                    .userMsgId(USER_MSG_ID)
                                                                                                    .userMsgSubId(USER_MSG_SUB_ID)
                                                                                                    .phoneNo(SERVER_MSG_ID)
                                                                                                    .result(DELIVERY_RESULT_SUCCESS)
                                                                                                    .resultMessage("RESULT_SUCCESS")
                                                                                                    .userData(USER_DATA)
                                                                                                    .build();

    protected static final String jsonOfSmtntDeliveryAckMessage = "{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"PhoneNo\":\"16103\",\"MsgType\":6,\"Result\":0,\"ResultMessage\":\"RESULT_SUCCESS\",\"UserData\":\"LMS\"}";
    protected static final String jsonOfSmtntDeliveryAckPacket = "{\"Header\":{\"Method\":\"MsgSendRes\"},\"Body\":{\"UserMsgId\":\"1234\",\"UserMsgSubId\":\"315135\",\"PhoneNo\":\"16103\",\"MsgType\":6,\"Result\":0,\"ResultMessage\":\"RESULT_SUCCESS\",\"UserData\":\"LMS\"}}";

    public static void main(String[] args) throws CryptoException {
        SmtntPacket smtntPacket = new SmtntPacket(smtntDeliveryAckMessage);
        System.out.println(smtntPacket);
        System.out.println(smtntPacket.toJson());
    }


    /**
     * SmtntPacket의 생성자 {@link SmtntDeliveryAckMessage}에 대한 테스트 케이스입니다.
     * SmtntPacket의 생성이 올바른지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);

        assertNotNull(smtntDeliveryAckPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNotNull(smtntDeliveryAckPacket.getBody(), "SmtntPacket의 바디는 null이 아니어야 합니다.");

        assertEquals(SMTNT_DELIVERY_ACK_METHOD_HEADER, smtntDeliveryAckPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        SmtntDeliveryAckMessage smtntDeliveryAckPacketBody = (SmtntDeliveryAckMessage) smtntDeliveryAckPacket.getBody();
        assertEquals(smtntDeliveryAckMessage, smtntDeliveryAckPacketBody);
    }

    /**
     * 패킷 헤더 값에 대한 테스트 케이스입니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketHeaderVaule() throws CryptoException {
        // SmtntDeliveryAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntDeliveryAckPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_DELIVERY_ACK_METHOD_HEADER, smtntDeliveryAckPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");
    }

    /**
     * 패킷 바디 값에 대한 테스트 케이스입니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketBodyVaule() throws CryptoException {
        // SmtntDeliveryAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntDeliveryAckPacket.getBody(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        SmtntDeliveryAckMessage smtntDeliveryAckPacketBody = (SmtntDeliveryAckMessage) smtntDeliveryAckPacket.getBody();
        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(smtntDeliveryAckMessage, smtntDeliveryAckPacketBody, "SmtntDeliveryAckMessage와 smtntDeliveryAckPacket의 Body는 일치해야 합니다.");
    }

    /**
     * 패킷을 JSON 형식으로 변환하는 테스트 케이스입니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertToJson() throws CryptoException {
        // SmtntDeliveryAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);

        String smtntDeliveryAckPacketJson = smtntDeliveryAckPacket.toJson();
        assertEquals(smtntDeliveryAckPacketJson, jsonOfSmtntDeliveryAckPacket);
    }

    /**
     * JSON 형식의 문자열을 패킷으로 변환하는 테스트 케이스입니다.
     *
     * @throws Exception 변환 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromJson() throws Exception {
        // SmtntDeliveryAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);
        assertNotNull(smtntDeliveryAckPacket);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(jsonOfSmtntDeliveryAckPacket);

        assertNotNull(smtntPacket);
        assertEquals(smtntDeliveryAckPacket, smtntPacket);
    }


    /**
     * 패킷을 ByteBuf 형식으로 변환하는 테스트 케이스입니다.
     *
     * @throws Exception 변환 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertToByteBuf() throws Exception {
        // SmtntDeliveryAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);
        assertNotNull(smtntDeliveryAckPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntDeliveryAckPacket.toByteBuf(byteBuf);

        assertTrue(byteBuf.writerIndex() >= 1);
    }

    /**
     * ByteBuf 형식의 데이터를 패킷으로 변환하는 테스트 케이스입니다.
     *
     * @throws Exception 변환 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromByteBuf() throws Exception {
        // SmtntDeliveryAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntDeliveryAckPacket = new SmtntPacket(smtntDeliveryAckMessage);
        assertNotNull(smtntDeliveryAckPacket);

        ByteBuf byteBuf = Unpooled.buffer();
        smtntDeliveryAckPacket.toByteBuf(byteBuf);

        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromByteBuf(byteBuf);

        assertEquals(smtntDeliveryAckPacket, smtntPacket);
    }
}
