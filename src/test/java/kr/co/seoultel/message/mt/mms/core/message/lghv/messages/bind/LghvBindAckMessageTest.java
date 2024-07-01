package kr.co.seoultel.message.mt.mms.core.message.lghv.messages.bind;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.bind.LghvBindAckMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static kr.co.seoultel.message.mt.mms.core.common.protocol.LghvProtocol.*;

/**
 * {@link LghvBindAckMessage}에 대한 테스트 클래스입니다.
 */
public class LghvBindAckMessageTest {

    protected static final String TEST_RESULT = "0";
    protected static final String TEST_SMS_TPS = "0";
    protected static final String TEST_LMS_TPS = "0";
    protected static final String TEST_MMS_TPS = "0";
    protected static final String TEST_KKO_TPS = "0";
    protected static final String TEST_KKF_TPS = "0";
    protected static final String TEST_TEMP1 = "TEMP1";
    protected static final String TEST_TEMP2 = "TEMP2";

    /**
     * {@link LghvBindAckMessage}의 기본 생성자를 테스트합니다.
     * 기본값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvBindAckMessage LghvBindAckMessage = new LghvBindAckMessage();

        assertNotNull(LghvBindAckMessage);
        assertEquals(LghvBindAckMessage.getMsgType(), BIND_ACK_MSG_TYPE);
        assertEquals(LghvBindAckMessage.getMsgLen(), BIND_ACK_BODY_LENGTH);
    }

    /**
     * {@link LghvBindAckMessage}의 매개변수가 있는 생성자를 테스트합니다.
     * 제공된 값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        LghvBindAckMessage LghvBindAckMessage = kr.co.seoultel.message.mt.mms.core.messages.lghv.bind.LghvBindAckMessage.builder()
                                                                                                                        .result(TEST_RESULT)
                                                                                                                        .tpsSms(TEST_SMS_TPS)
                                                                                                                        .tpsLms(TEST_LMS_TPS)
                                                                                                                        .tpsMms(TEST_MMS_TPS)
                                                                                                                        .tpsKko(TEST_KKO_TPS)
                                                                                                                        .tpsKkf(TEST_KKF_TPS)
                                                                                                                        .temp1(TEST_TEMP1)
                                                                                                                        .temp2(TEST_TEMP2)
                                                                                                                        .build();

        assertNotNull(LghvBindAckMessage);

        assertEquals(LghvBindAckMessage.getMsgType(), BIND_ACK_MSG_TYPE);
        assertEquals(LghvBindAckMessage.getMsgLen(), BIND_ACK_BODY_LENGTH);

        assertEquals(LghvBindAckMessage.getResult(), TEST_RESULT);
        assertEquals(LghvBindAckMessage.getSmsTps(), TEST_SMS_TPS);
        assertEquals(LghvBindAckMessage.getLmsTps(), TEST_LMS_TPS);
        assertEquals(LghvBindAckMessage.getMmsTps(), TEST_MMS_TPS);
        assertEquals(LghvBindAckMessage.getKkoTps(), TEST_KKO_TPS);
        assertEquals(LghvBindAckMessage.getKkfTps(), TEST_KKF_TPS);
        assertEquals(LghvBindAckMessage.getTemp1(), TEST_TEMP1);
        assertEquals(LghvBindAckMessage.getTemp2(), TEST_TEMP2);
    }


    /**
     * {@link LghvBindAckMessage}의 {@code equals()} 및 {@code hashCode()} 메서드를 테스트합니다.
     * 두 인스턴스 간의 동등성을 비교하여 메서드가 올바르게 작동하는지 확인합니다.
     */
    @Test
    public void testEqualsAndHashCode() {
        LghvBindAckMessage lghvBindAckMessage1 = getTestLghvBindAckMessage(TEST_RESULT, TEST_LMS_TPS, TEST_MMS_TPS, TEST_TEMP1, TEST_TEMP2);
        LghvBindAckMessage lghvBindAckMessage2 = getTestLghvBindAckMessage(TEST_RESULT, TEST_LMS_TPS, TEST_MMS_TPS, TEST_TEMP1, TEST_TEMP2);
        LghvBindAckMessage lghvBindAckMessage3 = getTestLghvBindAckMessage(TEST_RESULT, TEST_LMS_TPS, TEST_MMS_TPS, "abcde", TEST_TEMP2);

        assertEquals(lghvBindAckMessage1, lghvBindAckMessage2);
        assertNotEquals(lghvBindAckMessage1, lghvBindAckMessage3);
        assertEquals(lghvBindAckMessage1.hashCode(), lghvBindAckMessage2.hashCode());
        assertNotEquals(lghvBindAckMessage1.hashCode(), lghvBindAckMessage3.hashCode());
    }

    private static LghvBindAckMessage getTestLghvBindAckMessage(String result, String lmsTps, String mmsTps, String temp1, String temp2) {
        return LghvBindAckMessage.builder()
                                .result(result)
                                .tpsLms(lmsTps)
                                .tpsMms(mmsTps)
                                .temp1(temp1)
                                .temp2(temp2)
                                .build();
    }
}
