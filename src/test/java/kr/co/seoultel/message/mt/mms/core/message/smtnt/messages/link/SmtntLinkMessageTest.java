package kr.co.seoultel.message.mt.mms.core.message.smtnt.messages.link;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkMessage;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_LINK_METHOD_HEADER;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SmtntLinkMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntLinkMessageTest {

    /**
     * {@link SmtntLinkMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntLinkMessage message = new SmtntLinkMessage();
        assertNotNull(message);
        assertEquals(SMTNT_LINK_METHOD_HEADER, message.getMethod());
    }
}
