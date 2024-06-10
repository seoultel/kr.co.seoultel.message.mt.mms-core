package kr.co.seoultel.message.mt.mms.core.messages.lghv.link;


import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;

@Getter
@NoArgsConstructor
public class LghvLinkAckMessage extends LghvMessage {


    private String result;

    @Builder
    public LghvLinkAckMessage(String result) {
        super(LINK_ACK_MSG_TYPE, LINK_ACK_RESULT_LENGTH);

        this.result = result;
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, LINK_ACK_RESULT_LENGTH));
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
        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LINK_ACK_RESULT_LENGTH);
    }

    @Override
    public String toString() {
        return "LghvLinkAckMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}