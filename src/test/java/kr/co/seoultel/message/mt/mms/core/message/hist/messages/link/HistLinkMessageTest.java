package kr.co.seoultel.message.mt.mms.core.message.hist.messages.link;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.link.HistLinkMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HistLinkMessageTest {

    /**
     * {@link HistLinkMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        HistLinkMessage message = new HistLinkMessage();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_LINK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_LINK_MSG_LENG);
    }

    /**
     * {@link HistLinkMessage}의 {@code toByteBuf} 및 {@code fromByteBuf} 메서드를 테스트합니다.
     * 메시지를 바이트 버퍼로 변환하고 다시 읽어오는 과정을 확인합니다.
     */
    @Test
    public void testToAndFromByteBuf() {
        HistLinkMessage histLinkMessage = new HistLinkMessage();

        ByteBuf byteBuf = Unpooled.buffer();
        histLinkMessage.toByteBuf(byteBuf);

        HistLinkMessage readMessage = new HistLinkMessage();
        readMessage.fromByteBuf(byteBuf);

        assertEquals(histLinkMessage, readMessage);
    }

    /**
     * {@link HistLinkMessage}의 {@code equals()} 및 {@code hashCode()} 메서드를 테스트합니다.
     * 두 인스턴스 간의 동등성을 비교하여 메서드가 올바르게 작동하는지 확인합니다.
     */
    @Test
    public void testEqualsAndHashCode() {
        HistLinkMessage message1 = new HistLinkMessage();
        HistLinkMessage message2 = new HistLinkMessage();
        HistLinkMessage message3 = new HistLinkMessage();

        assertEquals(message1, message2);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertEquals(message2, message3);
        assertEquals(message2.hashCode(), message3.hashCode());
    }
}