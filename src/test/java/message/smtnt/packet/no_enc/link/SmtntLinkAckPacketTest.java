package message.smtnt.packet.no_enc.link;

import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_LINK_ACK_METHOD_HEADER;
import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_LINK_METHOD_HEADER;
import static org.junit.jupiter.api.Assertions.*;


/**
 * {@link SmtntPacket} SmtntLinkAckPacketTest 클래스는 SmtntLinkAckPacket에 대한 테스트를 제공합니다.
 */
public class SmtntLinkAckPacketTest extends SmtntDefaultPacketTest {

    protected static final SmtntLinkAckMessage smtntLinkAckMessage = new SmtntLinkAckMessage();
    protected static final String jsonOfSmtntLinkAckPacket = "{\"Header\":{\"Method\":\"AliveRes\"}}";

    /**
     * {@link SmtntPacket} 생성자를 테스트합니다. 이 때 {@link SmtntLinkAckMessage} 객체를 사용합니다.
     * @throws CryptoException 암호화 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntLinkAckPacket = new SmtntPacket(smtntLinkAckMessage);
        System.out.println(smtntLinkAckPacket);

        assertNotNull(smtntLinkAckPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNull(smtntLinkAckPacket.getBody(), "SmtntPacket의 바디는 null이어야 합니다.");

        assertEquals(SMTNT_LINK_ACK_METHOD_HEADER, smtntLinkAckPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        SmtntPacket smtntPacket = ConvertorUtil.convertJsonToObject(jsonOfSmtntLinkAckPacket, SmtntPacket.class);
        System.out.println(smtntPacket);

        assertEquals(smtntLinkAckPacket.toJson(), smtntPacket.toJson());
        assertEquals(smtntPacket, smtntLinkAckPacket);
    }

    @Override
    protected void testPacketHeaderVaule() throws CryptoException {

    }

    @Override
    protected void testPacketBodyVaule() throws CryptoException {

    }

    @Override
    protected void testPacketConvertToJson() throws CryptoException {

    }

    @Override
    protected void testPacketConvertFromJson() throws Exception {

    }

    @Override
    protected void testPacketConvertToByteBuf() throws Exception {

    }

    @Override
    protected void testPacketConvertFromByteBuf() throws Exception {

    }
}
