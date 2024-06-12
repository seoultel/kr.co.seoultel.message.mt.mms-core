package kr.co.seoultel.message.mt.mms.core.messages.hist.cert;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistCertAckMessage extends HistMessage {

    protected String result = "";        // 결과 코드
    protected String certKey = "";       // Cert Key
    protected String serverIp = "";   // 연동할 G/W IP
    protected int deliveryPort;    // Delivery Port
    protected int reportPort;    // Report Port

    public HistCertAckMessage() {
        super(HistProtocol.HIST_CERT_ACK_HEAD_TYPE, HistProtocol.HIST_CERT_ACK_MSG_LENG);
    }

    @Builder
    public HistCertAckMessage(String result, String certKey, String serverIp, int deliveryPort, int reportPort) {
        super(HistProtocol.HIST_CERT_ACK_HEAD_TYPE, HistProtocol.HIST_CERT_ACK_MSG_LENG);

        this.result = Objects.requireNonNullElse(result, "");
        this.certKey = Objects.requireNonNullElse(certKey, "");
        this.serverIp = Objects.requireNonNullElse(serverIp, "");
        this.deliveryPort = deliveryPort;
        this.reportPort = reportPort;
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, HistProtocol.RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(certKey, HistProtocol.CERT_KEY_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(serverIp, HistProtocol.SERVER_IP_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(deliveryPort, HistProtocol.DELIVERY_PORT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(reportPort, HistProtocol.REPORT_PORT_LENGTH));
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.RESULT_LENGTH);
        this.certKey = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.CERT_KEY_LENGTH);
        this.serverIp = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SERVER_IP_LENGTH);
        this.deliveryPort = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.DELIVERY_PORT_LENGTH);
        this.reportPort = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.REPORT_PORT_LENGTH);
    }

    @Override
    public String toString() {
        return "HistCertAckMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", result='" + result + '\'' +
                ", certKey='" + certKey + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", deliveryPort=" + deliveryPort +
                ", reportPort=" + reportPort +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistCertAckMessage that = (HistCertAckMessage) object;
        return deliveryPort == that.deliveryPort && reportPort == that.reportPort && Objects.equals(result, that.result) && Objects.equals(certKey, that.certKey) && Objects.equals(serverIp, that.serverIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, certKey, serverIp, deliveryPort, reportPort);
    }
}
