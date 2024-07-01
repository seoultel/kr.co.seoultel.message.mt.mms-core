package kr.co.seoultel.message.mt.mms.core.message.hist.messages.cert;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.cert.HistCertMessage;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol.HIST_VERSION;
import static org.junit.jupiter.api.Assertions.*;

public class HistCertMessageTest {

    private static final String ID = "testId";
    private static final String PWD = "testPwd";
    private static final String VERSION = HIST_VERSION;
    private static final String KEYPOS = "5";

    @Test
    public void testConstructor() {
        HistCertMessage message = new HistCertMessage();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_CERT_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_CERT_MSG_LENG);
        assertEquals(message.getId(), "");
        assertEquals(message.getPwd(), "");
        assertEquals(message.getVersion(), HIST_VERSION);
        assertEquals(message.getKeypos(), 0);
    }

    @Test
    public void testParameterizedConstructor() {
        HistCertMessage message = HistCertMessage.builder()
                .id(ID)
                .pwd(PWD)
                .keypos(KEYPOS)
                .build();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_CERT_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_CERT_MSG_LENG);
        assertEquals(message.getId(), ID);
        assertEquals(message.getPwd(), PWD);
        assertEquals(message.getVersion(), VERSION);
        assertEquals(message.getKeypos(), Integer.parseInt(KEYPOS));
    }

    @Test
    public void testToByteBuf() throws CryptoException {
        HistCertMessage message = HistCertMessage.builder()
                .id(ID)
                .pwd(PWD)
                .keypos(KEYPOS)
                .build();

        ByteBuf byteBuf = Unpooled.buffer();
        message.toByteBuf(byteBuf);

        HistCertMessage resultMessage = new HistCertMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() throws CryptoException {
        ByteBuf byteBuf = Unpooled.buffer();

        HistCertMessage histCertMessage = new HistCertMessage(ID, PWD, KEYPOS);
        histCertMessage.toByteBuf(byteBuf);

        HistCertMessage message = new HistCertMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(message.getId(), ID);
        assertEquals(message.getPwd(), PWD);
        assertEquals(message.getVersion(), VERSION);
        assertEquals(message.getKeypos(), Integer.parseInt(KEYPOS));
    }

    @Test
    public void testEqualsAndHashCode() {
        HistCertMessage message1 = HistCertMessage.builder()
                .id(ID)
                .pwd(PWD)
                .keypos(KEYPOS)
                .build();

        HistCertMessage message2 = HistCertMessage.builder()
                .id(ID)
                .pwd(PWD)
                .keypos(KEYPOS)
                .build();

        HistCertMessage message3 = HistCertMessage.builder()
                .id("differentId")
                .pwd("differentPwd")
                .keypos("10")
                .build();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}