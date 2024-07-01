package kr.co.seoultel.message.mt.mms.core.message.smtnt.messages.bind;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindAckMessage;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindAckMessage}에 대한 테스트 클래스입니다.
 */
public class SmtntBindAckMessageTest {

    /**
     * {@link kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindAckMessage}의 기본 생성자를 테스트합니다.
     * 기본값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        SmtntBindAckMessage message = new SmtntBindAckMessage();
        assertNotNull(message);
        assertEquals(0, message.getResult());
        assertNull(message.getBindId());
        assertEquals(0, message.getSpeed());
        assertEquals(0, message.getBeginTime());
        assertEquals(0, message.getEndTime());
        assertEquals(0, message.getEffectiveTime());
        assertEquals(SMTNT_BIND_ACK_METHOD_HEADER, message.getMethod());
    }

    /**
     * {@link SmtntBindAckMessage}의 빌더를 사용한 생성자를 테스트합니다.
     * 제공된 값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testBuilderConstructor() {
        int result = 1;
        String bindId = "testBindId";
        int speed = 100;
        int beginTime = 123456;
        int endTime = 654321;
        int effectiveTime = 3600;

        SmtntBindAckMessage message = SmtntBindAckMessage.builder()
                .result(result)
                .bindId(bindId)
                .speed(speed)
                .beginTime(beginTime)
                .endTime(endTime)
                .effectiveTime(effectiveTime)
                .build();

        assertNotNull(message);
        assertEquals(result, message.getResult());
        assertEquals(bindId, message.getBindId());
        assertEquals(speed, message.getSpeed());
        assertEquals(beginTime, message.getBeginTime());
        assertEquals(endTime, message.getEndTime());
        assertEquals(effectiveTime, message.getEffectiveTime());
        assertEquals(SMTNT_BIND_ACK_METHOD_HEADER, message.getMethod());
    }

    /**
     * {@link SmtntBindAckMessage}의 {@code equals()} 및 {@code hashCode()} 메서드를 테스트합니다.
     * 두 인스턴스 간의 동등성을 비교하여 메서드가 올바르게 작동하는지 확인합니다.
     */
    @Test
    public void testEqualsAndHashCode() {
        SmtntBindAckMessage message1 = SmtntBindAckMessage.builder()
                .result(1)
                .bindId("bindId")
                .speed(100)
                .beginTime(123456)
                .endTime(654321)
                .effectiveTime(3600)
                .build();

        SmtntBindAckMessage message2 = SmtntBindAckMessage.builder()
                .result(1)
                .bindId("bindId")
                .speed(100)
                .beginTime(123456)
                .endTime(654321)
                .effectiveTime(3600)
                .build();

        SmtntBindAckMessage message3 = SmtntBindAckMessage.builder()
                .result(2)
                .bindId("bindId2")
                .speed(200)
                .beginTime(654321)
                .endTime(123456)
                .effectiveTime(7200)
                .build();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
