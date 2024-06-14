package kr.co.seoultel.message.mt.mms.core.message.lghv.messages.link;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.link.LghvLinkAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.link.LghvLinkMessage;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.LINK_ACK_MSG_TYPE;
import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.LINK_MSG_TYPE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link LghvLinkAckMessage}에 대한 테스트 클래스입니다.
 */
public class LghvLinkAckMessageTest {

    protected static final String DEFAULT_RESULT = "R";

    /**
     * {@link LghvLinkAckMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvLinkAckMessage message = new LghvLinkAckMessage();

        assertNotNull(message);
        assertEquals(message.getMsgType(), LINK_ACK_MSG_TYPE);
        assertEquals(message.getMsgLen(), LghvProtocol.LINK_ACK_RESULT_LENGTH);
    }

    /**
     * {@link LghvLinkAckMessage}의 Parameterized Constructor 를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        LghvLinkAckMessage lghvLinkAckMessage1 = new LghvLinkAckMessage();
        LghvLinkAckMessage lghvLinkAckMessage2 = new LghvLinkAckMessage(DEFAULT_RESULT);

        assertNotNull(lghvLinkAckMessage2);
        assertEquals(lghvLinkAckMessage2.getMsgType(), LINK_ACK_MSG_TYPE);
        assertEquals(lghvLinkAckMessage2.getResult(), DEFAULT_RESULT);
        assertEquals(lghvLinkAckMessage2.getMsgLen(), LghvProtocol.LINK_ACK_RESULT_LENGTH);

        assertNotEquals(lghvLinkAckMessage1, lghvLinkAckMessage2);
    }
}
