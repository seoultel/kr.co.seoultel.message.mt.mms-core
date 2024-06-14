package kr.co.seoultel.message.mt.mms.core.message.hist.messages.bind;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.bind.HistBindMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HistBindMessageTest {

    protected static final String ID = "testId";
    protected static final String PWD = "testPwd";
    protected static final String CERT_KEY = "testCertKey";
    protected static final String TYPE = HistProtocol.DEFAULT_TYPE;
    protected static final String KIND = HistProtocol.DELIVERY_KIND;
    protected static final String VERSION = HistProtocol.HIST_VERSION;
    protected static final int KEYPOS = 0;

    /**
     * {@link HistBindMessage}의 기본 생성자를 테스트합니다.
     * 기본값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructor() {
        HistBindMessage histBindMessage = new HistBindMessage();

        assertNotNull(histBindMessage);
        assertEquals(histBindMessage.getHeadType(), HistProtocol.HIST_BIND_HEAD_TYPE);
        assertEquals(histBindMessage.getMsgLeng(), HistProtocol.HIST_BIND_MSG_LENG);
        assertEquals(histBindMessage.getId(), "");
        assertEquals(histBindMessage.getPwd(), "");
        assertEquals(histBindMessage.getCertKey(), "");
        assertEquals(histBindMessage.getType(), "");
        assertEquals(histBindMessage.getKind(), "");
        assertEquals(histBindMessage.getVersion(), HistProtocol.HIST_VERSION);
        assertEquals(histBindMessage.getKeypos(), 0);
    }

    /**
     * {@link HistBindMessage}의 매개변수가 있는 생성자를 테스트합니다.
     * 제공된 값으로 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testParameterizedConstructor() {
        HistBindMessage histBindMessage = HistBindMessage.builder()
                                                            .id(ID)
                                                            .pwd(PWD)
                                                            .certKey(CERT_KEY)
                                                            .type(TYPE)
                                                            .kind(KIND)
                                                            .keypos(KEYPOS)
                                                            .build();

        assertNotNull(histBindMessage);
        assertEquals(histBindMessage.getHeadType(), HistProtocol.HIST_BIND_HEAD_TYPE);
        assertEquals(histBindMessage.getMsgLeng(), HistProtocol.HIST_BIND_MSG_LENG);

        assertEquals(histBindMessage.getId(), ID);
        assertEquals(histBindMessage.getPwd(), PWD);
        assertEquals(histBindMessage.getCertKey(), CERT_KEY);
        assertEquals(histBindMessage.getType(), TYPE);
        assertEquals(histBindMessage.getKind(), KIND);
        assertEquals(histBindMessage.getVersion(), VERSION);
        assertEquals(histBindMessage.getKeypos(), KEYPOS);
    }

    /**
     * {@link HistBindMessage}의 {@code equals()} 및 {@code hashCode()} 메서드를 테스트합니다.
     * 두 인스턴스 간의 동등성을 비교하여 메서드가 올바르게 작동하는지 확인합니다.
     */
    @Test
    public void testEqualsAndHashCode() {
        HistBindMessage message1 = HistBindMessage.builder()
                                                    .id(ID)
                                                    .pwd(PWD)
                                                    .certKey(CERT_KEY)
                                                    .type(TYPE)
                                                    .kind(KIND)
                                                    .keypos(KEYPOS)
                                                    .build();

        HistBindMessage message2 = HistBindMessage.builder()
                                                    .id(ID)
                                                    .pwd(PWD)
                                                    .certKey(CERT_KEY)
                                                    .type(TYPE)
                                                    .kind(KIND)
                                                    .keypos(KEYPOS)
                                                    .build();

        HistBindMessage message3 = HistBindMessage.builder()
                                                    .id(ID + "2")
                                                    .pwd(PWD)
                                                    .certKey(CERT_KEY)
                                                    .type(TYPE)
                                                    .kind(KIND)
                                                    .keypos(KEYPOS)
                                                    .build();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }

    /**
     * {@link HistBindMessage}의 {@code toByteBuf} 및 {@code fromByteBuf} 메서드를 테스트합니다.
     * 메시지를 바이트 버퍼로 변환하고 다시 읽어오는 과정을 확인합니다.
     */
    @Test
    public void testToAndFromByteBuf() {
        HistBindMessage histBindMessage = HistBindMessage.builder()
                                                        .id(ID)
                                                        .pwd(PWD)
                                                        .certKey(CERT_KEY)
                                                        .type(TYPE)
                                                        .kind(KIND)
                                                        .keypos(KEYPOS)
                                                        .build();

        ByteBuf byteBuf = Unpooled.buffer();
        histBindMessage.toByteBuf(byteBuf);

        HistBindMessage readMessage = new HistBindMessage();
        readMessage.fromByteBuf(byteBuf);

        assertEquals(histBindMessage, readMessage);
    }
}
