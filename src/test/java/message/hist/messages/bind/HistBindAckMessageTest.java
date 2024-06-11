package message.hist.messages.bind;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.bind.HistBindAckMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class HistBindAckMessageTest {

    private static final String RESULT = "00";
    private static final int SMS_TPS = 10;
    private static final int MMS_TPS = 20;
    private static final int KKO_TPS = 30;
    private static final String MESSAGE = "Success";

    @Test
    public void testConstructor() {
        HistBindAckMessage message = new HistBindAckMessage();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_BIND_ACK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_BIND_ACK_MSG_LENG);
        assertEquals(message.getResult(), "");
        assertEquals(message.getSmsTps(), 0);
        assertEquals(message.getMmsTps(), 0);
        assertEquals(message.getKkoTps(), 0);
        assertEquals(message.getMessage(), "");
    }

    @Test
    public void testParameterizedConstructor() {
        HistBindAckMessage message = HistBindAckMessage.builder()
                                                        .result(RESULT)
                                                        .smsTps(SMS_TPS)
                                                        .mmsTps(MMS_TPS)
                                                        .kkoTps(KKO_TPS)
                                                        .message(MESSAGE)
                                                        .build();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_BIND_ACK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_BIND_ACK_MSG_LENG);
        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getSmsTps(), SMS_TPS);
        assertEquals(message.getMmsTps(), MMS_TPS);
        assertEquals(message.getKkoTps(), KKO_TPS);
        assertEquals(message.getMessage(), MESSAGE);
    }

    @Test
    public void testToByteBuf() {
        HistBindAckMessage message = HistBindAckMessage.builder()
                                                        .result(RESULT)
                                                        .smsTps(SMS_TPS)
                                                        .mmsTps(MMS_TPS)
                                                        .kkoTps(KKO_TPS)
                                                        .message(MESSAGE)
                                                        .build();

        ByteBuf byteBuf = Unpooled.buffer();
        message.toByteBuf(byteBuf);

        HistBindAckMessage resultMessage = new HistBindAckMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        HistBindAckMessage histBindAckMessage = new HistBindAckMessage(RESULT, SMS_TPS, MMS_TPS, KKO_TPS, MESSAGE);
        histBindAckMessage.toByteBuf(byteBuf);

        HistBindAckMessage message = new HistBindAckMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getSmsTps(), SMS_TPS);
        assertEquals(message.getMmsTps(), MMS_TPS);
        assertEquals(message.getKkoTps(), KKO_TPS);
        assertEquals(message.getMessage(), MESSAGE);
    }

    @Test
    public void testEqualsAndHashCode() {
        HistBindAckMessage message1 = HistBindAckMessage.builder()
                .result(RESULT)
                .smsTps(SMS_TPS)
                .mmsTps(MMS_TPS)
                .kkoTps(KKO_TPS)
                .message(MESSAGE)
                .build();

        HistBindAckMessage message2 = HistBindAckMessage.builder()
                .result(RESULT)
                .smsTps(SMS_TPS)
                .mmsTps(MMS_TPS)
                .kkoTps(KKO_TPS)
                .message(MESSAGE)
                .build();

        HistBindAckMessage message3 = HistBindAckMessage.builder()
                .result("01")
                .smsTps(15)
                .mmsTps(25)
                .kkoTps(35)
                .message("Failure")
                .build();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
