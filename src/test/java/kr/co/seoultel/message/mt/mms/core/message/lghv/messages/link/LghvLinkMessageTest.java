package message.lghv.messages.link;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.link.LghvLinkMessage;
import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;

/**
 * {@link LghvLinkMessage}에 대한 테스트 클래스입니다.
 */
public class LghvLinkMessageTest {

    protected static final String DEFAULT_CODE = "C";

    /**
     * {@link LghvLinkMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvLinkMessage message = new LghvLinkMessage();

        assertNotNull(message);
        assertEquals(message.getMsgType(), LINK_MSG_TYPE);
        Assertions.assertEquals(message.getMsgLen(), LghvProtocol.LINK_CODE_LENGTH);
    }

    @Test
    public void testParameterizedConstructor() {
        LghvLinkMessage lghvLinkMessage1 = new LghvLinkMessage();
        LghvLinkMessage lghvLinkMessage2 = new LghvLinkMessage(DEFAULT_CODE);

        assertNotNull(lghvLinkMessage1);
        assertEquals(lghvLinkMessage1.getMsgType(), LINK_MSG_TYPE);
        assertEquals(lghvLinkMessage1.getMsgLen(), LghvProtocol.LINK_CODE_LENGTH);
        assertEquals(lghvLinkMessage1.getCode(), "");
        assertEquals(lghvLinkMessage2.getCode(), DEFAULT_CODE);
        assertEquals(lghvLinkMessage2.getMsgLen(), LghvProtocol.LINK_CODE_LENGTH);

        assertNotEquals(lghvLinkMessage1, lghvLinkMessage2);
    }
}
