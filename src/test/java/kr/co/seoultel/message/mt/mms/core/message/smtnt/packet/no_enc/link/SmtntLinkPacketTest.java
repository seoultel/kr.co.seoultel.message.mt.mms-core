package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.link;

import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_LINK_METHOD_HEADER;
import static org.junit.jupiter.api.Assertions.*;


/**
 * {@link SmtntPacket} SmtntLinkPacketTest 클래스는 SmtntLinkPacket에 대한 테스트를 제공합니다.
 */
public class SmtntLinkPacketTest extends SmtntDefaultPacketTest {

    protected static final SmtntLinkMessage smtntLinkMessage = new SmtntLinkMessage();
    protected static final String jsonOfSmtntLinkPacket = "{\"Header\":{\"Method\":\"AliveReq\"}}";

    /**
     * {@link SmtntPacket} 생성자를 테스트합니다. 이 때 {@link SmtntLinkMessage} 객체를 사용합니다.
     * @throws CryptoException 암호화 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntLinkPacket = new SmtntPacket(smtntLinkMessage);
        System.out.println(smtntLinkPacket);

        assertNotNull(smtntLinkPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNull(smtntLinkPacket.getBody(), "SmtntPacket의 바디는 null이어야 합니다.");

        assertEquals(SMTNT_LINK_METHOD_HEADER, smtntLinkPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        SmtntPacket smtntPacket = ConvertorUtil.convertJsonToObject(jsonOfSmtntLinkPacket, SmtntPacket.class);
        System.out.println(smtntPacket);

        assertEquals(smtntLinkPacket.toJson(), smtntPacket.toJson());
        assertEquals(smtntPacket, smtntLinkPacket);
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
