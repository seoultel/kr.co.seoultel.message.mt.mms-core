package kr.co.seoultel.message.mt.mms.core.messages.lghv.bind;


import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;


@Getter
public class LghvBindAckMessage extends LghvMessage {

    private String result = "";
    private String smsTps = "0";
    private String lmsTps = "0";
    private String mmsTps = "0";
    private String kkoTps = "0";
    private String kkfTps = "0";
    private String temp1 = "";
    private String temp2 = "";

    public LghvBindAckMessage() {
        super(BIND_ACK_MSG_TYPE, BIND_ACK_BODY_LENGTH);
    }

    @Builder
    public LghvBindAckMessage(String result, String tpsSms, String tpsLms, String tpsMms, String tpsKko, String tpsKkf, String temp1, String temp2) {
        super(BIND_ACK_MSG_TYPE, BIND_ACK_BODY_LENGTH);

        this.result = Objects.requireNonNullElse(result, "");
        this.smsTps = Objects.requireNonNullElse(tpsSms, "0");
        this.lmsTps = Objects.requireNonNullElse(tpsLms, "0");
        this.mmsTps = Objects.requireNonNullElse(tpsMms, "0");
        this.kkoTps = Objects.requireNonNullElse(tpsKko, "0");
        this.kkfTps = Objects.requireNonNullElse(tpsKkf, "0");
        this.temp1 = Objects.requireNonNullElse(temp1, "");
        this.temp2 = Objects.requireNonNullElse(temp2, "");
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(smsTps, TPS_SMS_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(lmsTps, TPS_LMS_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(mmsTps, TPS_MMS_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(kkoTps, TPS_KKO_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(kkfTps, TPS_KKF_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(temp1, BIND_ACK_TEMP1_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(temp2, BIND_ACK_TEMP2_LENGTH));
    }


    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        // Header Skip
        super.fromByteBuf(byteBuf);

        // Body
        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, RESULT_LENGTH);
        this.smsTps = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, TPS_SMS_LENGTH);
        this.lmsTps = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, TPS_LMS_LENGTH);
        this.mmsTps = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, TPS_MMS_LENGTH);
        this.kkoTps = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, TPS_KKO_LENGTH);
        this.kkfTps = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, TPS_KKF_LENGTH);
        this.temp1 = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, BIND_ACK_TEMP1_LENGTH);
        this.temp2 = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, BIND_ACK_TEMP2_LENGTH);
    }


    @Override
    public String toString() {
        return "LghvBindAckMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' + ", tail.length=" + tail.length() +
                ", result='" + result + '\'' +
                ", smsTps='" + smsTps + '\'' +
                ", lmsTps='" + lmsTps + '\'' +
                ", mmsTps='" + mmsTps + '\'' +
                ", kkoTps='" + kkoTps + '\'' +
                ", kkfTps='" + kkfTps + '\'' +
                ", temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LghvBindAckMessage that = (LghvBindAckMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(smsTps, that.smsTps) && Objects.equals(lmsTps, that.lmsTps) && Objects.equals(mmsTps, that.mmsTps) && Objects.equals(kkoTps, that.kkoTps) && Objects.equals(kkfTps, that.kkfTps) && Objects.equals(temp1, that.temp1) && Objects.equals(temp2, that.temp2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, smsTps, lmsTps, mmsTps, kkoTps, kkfTps, temp1, temp2);
    }
}
