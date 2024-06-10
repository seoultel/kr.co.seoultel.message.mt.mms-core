package kr.co.seoultel.message.mt.mms.core.messages.lghv.report;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;

@Getter
@ToString
@NoArgsConstructor
public class LghvReportAckMessage extends LghvMessage {


    private String result;
    private String msgId;
    private String reserved;

    @Builder
    public LghvReportAckMessage(String msgId) {
        super(REPORT_ACK_MSG_TYPE, REPORT_ACK_BODY_LEN);

        this.result = REPORT_ACK_RESULT;
        this.msgId = msgId;
        this.reserved = "";
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
}