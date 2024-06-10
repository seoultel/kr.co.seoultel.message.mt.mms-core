package message.lghv.messages.report;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.bind.LghvBindMessage;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.report.LghvReportMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.REPORT_BODY_LEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * {@link LghvReportMessage}에 대한 테스트 클래스입니다.
 */
public class LghvReportMessageTest {

    protected static final String DEFAULT_RESULT = "0";
    protected static final String DEFAULT_DELIVERY_TYPE = "LMS";
    protected static final String DEFAULT_RTN_TYPE = "LMS";
    protected static final String DEFAULT_MSG_ID = "MSG_ID";
    protected static final String DEFAULT_DELIVERY_TIME = "20240610101000";
    protected static final String DEFAULT_NET_ID = "SKT";


    /**
     * {@link LghvReportMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvReportMessage lghvReportMessage = new LghvReportMessage();

        Assertions.assertNotNull(lghvReportMessage);
        Assertions.assertEquals(lghvReportMessage.getMsgType(), LghvProtocol.REPORT_MSG_TYPE);
        Assertions.assertEquals(lghvReportMessage.getMsgLen(), REPORT_BODY_LEN);

        Assertions.assertNotNull(lghvReportMessage.getResult());
        Assertions.assertNotNull(lghvReportMessage.getDeliveryType());
        Assertions.assertNotNull(lghvReportMessage.getRtnType());
        Assertions.assertNotNull(lghvReportMessage.getMsgId());
        Assertions.assertNotNull(lghvReportMessage.getDeliveryTime());
        Assertions.assertNotNull(lghvReportMessage.getNetId());
    }

    @Test
    public void testParameterizedConstructor() {
        LghvReportMessage lghvReportMessage = getDefaultLghvReportMessage();

        Assertions.assertNotNull(lghvReportMessage);
        Assertions.assertEquals(lghvReportMessage.getMsgType(), LghvProtocol.REPORT_MSG_TYPE);
        Assertions.assertEquals(lghvReportMessage.getMsgLen(), REPORT_BODY_LEN);

        Assertions.assertNotNull(lghvReportMessage.getResult());
        Assertions.assertNotNull(lghvReportMessage.getDeliveryType());
        Assertions.assertNotNull(lghvReportMessage.getRtnType());
        Assertions.assertNotNull(lghvReportMessage.getMsgId());
        Assertions.assertNotNull(lghvReportMessage.getDeliveryTime());
        Assertions.assertNotNull(lghvReportMessage.getNetId());

        Assertions.assertEquals(lghvReportMessage.getResult(), DEFAULT_RESULT);
        Assertions.assertEquals(lghvReportMessage.getDeliveryType(), DEFAULT_DELIVERY_TYPE);
        Assertions.assertEquals(lghvReportMessage.getRtnType(), DEFAULT_RTN_TYPE);
        Assertions.assertEquals(lghvReportMessage.getMsgId(), DEFAULT_MSG_ID);
        Assertions.assertEquals(lghvReportMessage.getDeliveryTime(), DEFAULT_DELIVERY_TIME);
        Assertions.assertEquals(lghvReportMessage.getNetId(), DEFAULT_NET_ID);
    }

    @Test
    public void testEqualsAndHashCode() {
        LghvReportMessage message1 = getDefaultLghvReportMessage();
        LghvReportMessage message2 = getDefaultLghvReportMessage();
        LghvReportMessage message3 = new LghvReportMessage();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }

    private static LghvReportMessage getDefaultLghvReportMessage() {
        return LghvReportMessage.builder()
                                // Header
                                .msgLen(REPORT_BODY_LEN)
                                // Common Body
                                .result(DEFAULT_RESULT)
                                .deliveryType(DEFAULT_DELIVERY_TYPE)
                                .rtnType(DEFAULT_RTN_TYPE)
                                .msgId(DEFAULT_MSG_ID)
                                .deliveryTime(DEFAULT_DELIVERY_TIME)
                                .netId(DEFAULT_NET_ID)
                                .build();
    }
}
