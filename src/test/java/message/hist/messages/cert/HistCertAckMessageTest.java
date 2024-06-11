package message.hist.messages.cert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.cert.HistCertAckMessage;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class HistCertAckMessageTest {

    private static final String RESULT = "OK";
    private static final String CERT_KEY = "certKey123";
    private static final String SERVER_IP = "192.168.1.1";
    private static final int DELIVERY_PORT = 8080;
    private static final int REPORT_PORT = 9090;

    @Test
    public void testConstructor() {
        HistCertAckMessage message = new HistCertAckMessage();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_CERT_ACK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_CERT_ACK_MSG_LENG);
        assertEquals(message.getResult(), "");
        assertEquals(message.getCertKey(), "");
        assertEquals(message.getServerIp(), "");
        assertEquals(message.getDeliveryPort(), 0);
        assertEquals(message.getReportPort(), 0);
    }

    @Test
    public void testParameterizedConstructor() {
        HistCertAckMessage message = HistCertAckMessage.builder()
                .result(RESULT)
                .certKey(CERT_KEY)
                .serverIp(SERVER_IP)
                .deliveryPort(DELIVERY_PORT)
                .reportPort(REPORT_PORT)
                .build();

        assertNotNull(message);
        assertEquals(message.getHeadType(), HistProtocol.HIST_CERT_ACK_HEAD_TYPE);
        assertEquals(message.getMsgLeng(), HistProtocol.HIST_CERT_ACK_MSG_LENG);
        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getCertKey(), CERT_KEY);
        assertEquals(message.getServerIp(), SERVER_IP);
        assertEquals(message.getDeliveryPort(), DELIVERY_PORT);
        assertEquals(message.getReportPort(), REPORT_PORT);
    }

    @Test
    public void testToByteBuf() {
        HistCertAckMessage message = HistCertAckMessage.builder()
                .result(RESULT)
                .certKey(CERT_KEY)
                .serverIp(SERVER_IP)
                .deliveryPort(DELIVERY_PORT)
                .reportPort(REPORT_PORT)
                .build();

        ByteBuf byteBuf = Unpooled.buffer();
        message.toByteBuf(byteBuf);

        HistCertAckMessage resultMessage = new HistCertAckMessage();
        resultMessage.fromByteBuf(byteBuf);

        assertEquals(message, resultMessage);
    }

    @Test
    public void testFromByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer();

        HistCertAckMessage histCertAckMessage = new HistCertAckMessage(RESULT, CERT_KEY, SERVER_IP, DELIVERY_PORT, REPORT_PORT);
        histCertAckMessage.toByteBuf(byteBuf);

        HistCertAckMessage message = new HistCertAckMessage();
        message.fromByteBuf(byteBuf);

        assertEquals(message.getResult(), RESULT);
        assertEquals(message.getCertKey(), CERT_KEY);
        assertEquals(message.getServerIp(), SERVER_IP);
        assertEquals(message.getDeliveryPort(), DELIVERY_PORT);
        assertEquals(message.getReportPort(), REPORT_PORT);
    }

    @Test
    public void testEqualsAndHashCode() {
        HistCertAckMessage message1 = HistCertAckMessage.builder()
                .result(RESULT)
                .certKey(CERT_KEY)
                .serverIp(SERVER_IP)
                .deliveryPort(DELIVERY_PORT)
                .reportPort(REPORT_PORT)
                .build();

        HistCertAckMessage message2 = HistCertAckMessage.builder()
                .result(RESULT)
                .certKey(CERT_KEY)
                .serverIp(SERVER_IP)
                .deliveryPort(DELIVERY_PORT)
                .reportPort(REPORT_PORT)
                .build();

        HistCertAckMessage message3 = HistCertAckMessage.builder()
                .result("ERROR")
                .certKey("differentCertKey")
                .serverIp("10.0.0.1")
                .deliveryPort(1234)
                .reportPort(5678)
                .build();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }
}
