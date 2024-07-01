package kr.co.seoultel.message.mt.mms.core.messages.hist.bind;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistBindAckMessage extends HistMessage {

    protected String result = "";        // 결과 코드
    protected int smsTps;       // 1초당 SMS 발송 가능 건수
    protected int mmsTps;   // 1초당 MMS 발송 가능 건수
    protected int kkoTps;    // 1초당 알림톡/친구톡 발송 가능 건수
    protected String message = "";    // 결과 메세지

    public HistBindAckMessage() {
        super(HistProtocol.HIST_BIND_ACK_HEAD_TYPE, HistProtocol.HIST_BIND_ACK_MSG_LENG);
    }

    @Builder
    public HistBindAckMessage(String result, int smsTps, int mmsTps, int kkoTps, String message) {
        super(HistProtocol.HIST_BIND_ACK_HEAD_TYPE, HistProtocol.HIST_BIND_ACK_MSG_LENG);

        this.result = Objects.requireNonNullElse(result, "");
        this.smsTps = smsTps;
        this.mmsTps = mmsTps;
        this.kkoTps = kkoTps;
        this.message = Objects.requireNonNullElse(message, "");
    }


    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, HistProtocol.RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(smsTps, HistProtocol.SMS_TPS_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(mmsTps, HistProtocol.MMS_TPS_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(kkoTps, HistProtocol.KKO_TPS_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(message, HistProtocol.MESSAGE_LENGTH));
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
        this.smsTps = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.SMS_TPS_LENGTH);
        this.mmsTps = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.MMS_TPS_LENGTH);
        this.kkoTps = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.KKO_TPS_LENGTH);
        this.message = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.MESSAGE_LENGTH);
    }

    @Override
    public String toString() {
        return "HistBindAckMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", result='" + result + '\'' +
                ", smsTps=" + smsTps +
                ", mmsTps=" + mmsTps +
                ", kkoTps=" + kkoTps +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistBindAckMessage that = (HistBindAckMessage) object;
        return smsTps == that.smsTps && mmsTps == that.mmsTps && kkoTps == that.kkoTps && Objects.equals(result, that.result) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, smsTps, mmsTps, kkoTps, message);
    }
}
