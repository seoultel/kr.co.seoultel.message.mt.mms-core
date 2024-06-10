package message.smtnt.messages.link;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkAckMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SmtntLinkAckMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntLinkAckMessageTest {

    /**
     * {@link SmtntLinkAckMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntLinkAckMessage message = new SmtntLinkAckMessage();
        assertNotNull(message);
        assertEquals(SmtntProtocol.SMTNT_LINK_ACK_METHOD_HEADER, message.getMethod());
    }
}
