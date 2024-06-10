package message.lghv.messages.delivery;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery.LghvDeliveryAckMessage;

/**
 * {@link LghvDeliveryAckMessage}에 대한 테스트 클래스입니다.
 */
public class LghvDeliveryAckMessageTest {

    protected static final String DEFAULT_RESULT = "RSLT";
    protected static final String DEFAULT_DELIVERY_TYPE = "LMS";
    protected static final String DEFAULT_MSG_ID = "MSG_ID";
    protected static final String DEFAULT_MSG_MSEC = "0";
    protected static final String DEFAULT_DEST_ADDR = "01012345678";
    protected static final String DEFAULT_RESERVED = "";


    /**
     * {@link LghvDeliveryAckMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        LghvDeliveryAckMessage lghvReportMessage = new LghvDeliveryAckMessage();

        assertNotNull(lghvReportMessage);
        assertEquals(lghvReportMessage.getMsgType(), DELIVERY_ACK_MSG_TYPE);
        assertEquals(lghvReportMessage.getMsgLen(), DELIVERY_ACK_BODY_LEN);

        assertNotNull(lghvReportMessage.getResult());
        assertNotNull(lghvReportMessage.getDeliveryType());
        assertNotNull(lghvReportMessage.getMsgId());
        assertNotNull(lghvReportMessage.getMsgMsec());
        assertNotNull(lghvReportMessage.getDestAddr());
        assertNotNull(lghvReportMessage.getReserved());
    }

    @Test
    public void testParameterizedConstructor() {
        LghvDeliveryAckMessage lghvDeliveryAckMessage = LghvDeliveryAckMessage.builder()
                                                                            .result(DEFAULT_RESULT)
                                                                            .deliveryType(DEFAULT_DELIVERY_TYPE)
                                                                            .msgId(DEFAULT_MSG_ID)
                                                                            .msgMsec(DEFAULT_MSG_MSEC)
                                                                            .destAddr(DEFAULT_DEST_ADDR)
                                                                            .reserved(DEFAULT_RESERVED)
                                                                            .build();

        assertNotNull(lghvDeliveryAckMessage);
        assertEquals(lghvDeliveryAckMessage.getMsgType(), DELIVERY_ACK_MSG_TYPE);
        assertEquals(lghvDeliveryAckMessage.getMsgLen(), DELIVERY_ACK_BODY_LEN);

        assertNotNull(lghvDeliveryAckMessage.getResult());
        assertNotNull(lghvDeliveryAckMessage.getDeliveryType());
        assertNotNull(lghvDeliveryAckMessage.getMsgId());
        assertNotNull(lghvDeliveryAckMessage.getMsgMsec());
        assertNotNull(lghvDeliveryAckMessage.getDestAddr());
        assertNotNull(lghvDeliveryAckMessage.getReserved());

        assertEquals(lghvDeliveryAckMessage.getResult(), DEFAULT_RESULT);
        assertEquals(lghvDeliveryAckMessage.getDeliveryType(), DEFAULT_DELIVERY_TYPE);
        assertEquals(lghvDeliveryAckMessage.getMsgId(), DEFAULT_MSG_ID);
        assertEquals(lghvDeliveryAckMessage.getMsgMsec(), DEFAULT_MSG_MSEC);
        assertEquals(lghvDeliveryAckMessage.getDestAddr(), DEFAULT_DEST_ADDR);
        assertEquals(lghvDeliveryAckMessage.getReserved(), DEFAULT_RESERVED);
    }

    @Test
    public void testEqualsAndHashCode() {
        LghvDeliveryAckMessage message1 = getTestLghvDeliveryAckMessage();
        LghvDeliveryAckMessage message2 = getTestLghvDeliveryAckMessage();
        LghvDeliveryAckMessage message3 = new LghvDeliveryAckMessage();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }

    private static LghvDeliveryAckMessage getTestLghvDeliveryAckMessage() {
        return LghvDeliveryAckMessage.builder()
                .result(DEFAULT_RESULT)
                .deliveryType(DEFAULT_DELIVERY_TYPE)
                .msgId(DEFAULT_MSG_ID)
                .msgMsec(DEFAULT_MSG_MSEC)
                .destAddr(DEFAULT_DEST_ADDR)
                .reserved(DEFAULT_RESERVED)
                .build();
    }
}
