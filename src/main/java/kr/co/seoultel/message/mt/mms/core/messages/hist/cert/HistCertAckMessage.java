package kr.co.seoultel.message.mt.mms.core.messages.hist.cert;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.entity.ServerInfo;
import kr.co.seoultel.message.mt.mms.core.entity.ServerType;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistCertAckMessage extends HistMessage {

    protected String result = "";        // 결과 코드
    protected String certKey = "";       // Cert Key
    protected String serverIp = "";   // 연동할 G/W IP
    protected String deliveryPort = "";    // Delivery Port
    protected String reportPort = "";    // Report Port

    public HistCertAckMessage() {
        super(HistProtocol.HIST_CERT_ACK_HEAD_TYPE, HistProtocol.HIST_CERT_ACK_MSG_LENG);
    }

    @Builder
    public HistCertAckMessage(String result, String certKey, String serverIp, String deliveryPort, String reportPort) {
        super(HistProtocol.HIST_CERT_ACK_HEAD_TYPE, HistProtocol.HIST_CERT_ACK_MSG_LENG);

        this.result = Objects.requireNonNullElse(result, "");
        this.certKey = Objects.requireNonNullElse(certKey, "");
        this.serverIp = Objects.requireNonNullElse(serverIp, "");
        this.deliveryPort = Objects.requireNonNullElse(deliveryPort, "");
        this.reportPort = Objects.requireNonNullElse(reportPort, "");
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
        this.deliveryPort = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.DELIVERY_PORT_LENGTH);
        this.reportPort = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.REPORT_PORT_LENGTH);
    }

    public ServerInfo getDeliveryServerInfo() {
        return new ServerInfo(serverIp, deliveryPort, ServerType.DELIVERY);
    }

    public ServerInfo getReportServerInfo() {
        return new ServerInfo(serverIp, reportPort, ServerType.REPORT);
    }

    @Override
    public String toString() {
        return "HistCertAckMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", result='" + result + '\'' +
                ", certKey='" + certKey + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", deliveryPort='" + deliveryPort + '\'' +
                ", reportPort='" + reportPort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistCertAckMessage that = (HistCertAckMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(certKey, that.certKey) && Objects.equals(serverIp, that.serverIp) && Objects.equals(deliveryPort, that.deliveryPort) && Objects.equals(reportPort, that.reportPort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, certKey, serverIp, deliveryPort, reportPort);
    }
}
