package kr.co.seoultel.message.mt.mms.core.messages.lghv.report;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;

@Getter
public class LghvReportAckMessage extends LghvMessage {

    private String result = "";
    private String msgId = "";
    private String reserved = "";

    public LghvReportAckMessage() {
        super(REPORT_ACK_MSG_TYPE, REPORT_ACK_BODY_LEN);
    }

    public LghvReportAckMessage(String msgId) {
        super(REPORT_ACK_MSG_TYPE, REPORT_ACK_BODY_LEN);

        this.result = REPORT_ACK_RESULT;
        this.msgId = msgId;
        this.reserved = "";
    }

    @Builder
    public LghvReportAckMessage(String result, String msgId, String reserved) {
        super(REPORT_ACK_MSG_TYPE, REPORT_ACK_BODY_LEN);

        this.result = Objects.requireNonNullElse(result, "");
        this.msgId = Objects.requireNonNullElse(msgId, "");
        this.reserved = Objects.requireNonNullElse(reserved, "");
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgId, MSG_ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(reserved, REPORT_ACK_RESERVED_LENGTH));
    }


    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        // Body
        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, RESULT_LENGTH);
        this.msgId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, MSG_ID_LENGTH);
        this.reserved = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, REPORT_ACK_RESERVED_LENGTH);
    }

    @Override
    public String toString() {
        return "LghvReportAckMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' + ", tail.length=" + tail.length() +
                ", result='" + result + '\'' +
                ", msgId='" + msgId + '\'' +
                ", reserved='" + reserved + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LghvReportAckMessage that = (LghvReportAckMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(msgId, that.msgId) && Objects.equals(reserved, that.reserved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, msgId, reserved);
    }
}