package kr.co.seoultel.message.mt.mms.core.message.lghv.messages.bind;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.bind.LghvBindMessage;
import static kr.co.seoultel.message.mt.mms.core.common.protocol.LghvProtocol.*;

/**
 * {@link LghvBindMessage}에 대한 테스트 클래스입니다.
 */
public class LghvBindMessageTest {

    protected static final String AGENT_ID = "agentId";
    protected static final String USER_ID = "userId";
    protected static final String USER_PWD = "userPwd";

    /**
     * {@link LghvBindMessage}의 기본 생성자를 테스트합니다.
     * 기본값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvBindMessage lghvBindMessage = new LghvBindMessage();

        assertNotNull(lghvBindMessage);
        assertEquals(lghvBindMessage.getMsgType(), BIND_MSG_TYPE);
        assertEquals(lghvBindMessage.getMsgLen(), BIND_BODY_LENGTH);
        assertEquals(lghvBindMessage.getEncode(), BIND_DEFAULT_ENCODE);
        assertEquals(lghvBindMessage.getReport(), REQUIRED_VALUE_REPORT);
        assertEquals(lghvBindMessage.getInfo(), INFO_VALUE);
    }

    /**
     * {@link LghvBindMessage}의 매개변수가 있는 생성자를 테스트합니다.
     * 제공된 값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        LghvBindMessage lghvBindMessage = LghvBindMessage.builder()
                                                        .encode(BIND_DEFAULT_ENCODE)
                                                        .lineType(SEND_LINE_TYPE)
                                                        .userId(USER_ID)
                                                        .agentId(AGENT_ID)
                                                        .userPwd(USER_PWD)
                                                        .report(REQUIRED_VALUE_REPORT)
                                                        .info(INFO_VALUE)
                                                        .build();

        assertNotNull(lghvBindMessage);

        assertEquals(lghvBindMessage.getMsgType(), BIND_MSG_TYPE);
        assertEquals(lghvBindMessage.getMsgLen(), BIND_BODY_LENGTH);

        assertEquals(lghvBindMessage.getEncode(), BIND_DEFAULT_ENCODE);
        assertEquals(lghvBindMessage.getLineType(), SEND_LINE_TYPE);
        assertEquals(lghvBindMessage.getUserId(), USER_ID);
        assertEquals(lghvBindMessage.getAgentId(), AGENT_ID);
        assertEquals(lghvBindMessage.getUserPwd(), USER_PWD);
        assertEquals(lghvBindMessage.getReport(), REQUIRED_VALUE_REPORT);
        assertEquals(lghvBindMessage.getInfo(), INFO_VALUE);
    }
    

    /**
     * {@link LghvBindMessage}의 {@code equals()} 및 {@code hashCode()} 메서드를 테스트합니다.
     * 두 인스턴스 간의 동등성을 비교하여 메서드가 올바르게 작동하는지 확인합니다.
     */
    @Test
    public void testEqualsAndHashCode() {
        LghvBindMessage message1 = LghvBindMessage.createMessageForBindToDeliveryServer(USER_ID, AGENT_ID, USER_PWD);
        LghvBindMessage message2 = LghvBindMessage.createMessageForBindToDeliveryServer(USER_ID, AGENT_ID, USER_PWD);
        LghvBindMessage message3 = LghvBindMessage.createMessageForBindToDeliveryServer(USER_ID, AGENT_ID, "UserPwd");

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
