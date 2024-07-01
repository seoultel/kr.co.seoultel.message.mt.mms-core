package kr.co.seoultel.message.mt.mms.core.message.smtnt.messages.bind;

import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindMessage;

/**
 * {@link SmtntBindMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntBindMessageTest {

    /**
     * {@link SmtntBindMessage}의 기본 생성자를 테스트합니다.
     * 기본값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntBindMessage message = new SmtntBindMessage();
        assertNotNull(message);
        assertEquals(1, message.getVersion());
        assertNull(message.getBindId());
        assertNull(message.getBindPwd());
        assertEquals(SmtntProtocol.SMTNT_BIND_METHOD_HEADER, message.getMethod());
    }

    /**
     * {@link SmtntBindMessage}의 매개변수가 있는 생성자를 테스트합니다.
     * 제공된 값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        String bindId = "testBindId";
        String bindPwd = "testBindPwd";
        SmtntBindMessage message = new SmtntBindMessage(bindId, bindPwd);
        assertNotNull(message);
        assertEquals(bindId, message.getBindId());
        assertEquals(bindPwd, message.getBindPwd());
        assertEquals(1, message.getVersion());
        assertEquals(SmtntProtocol.SMTNT_BIND_METHOD_HEADER, message.getMethod());
    }
    

    /**
     * {@link SmtntBindMessage}의 {@code equals()} 및 {@code hashCode()} 메서드를 테스트합니다.
     * 두 인스턴스 간의 동등성을 비교하여 메서드가 올바르게 작동하는지 확인합니다.
     */
    @Test
    public void testEqualsAndHashCode() {
        SmtntBindMessage message1 = new SmtntBindMessage("bindId1", "bindPwd1");
        SmtntBindMessage message2 = new SmtntBindMessage("bindId1", "bindPwd1");
        SmtntBindMessage message3 = new SmtntBindMessage("bindId2", "bindPwd2");

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
