package kr.co.seoultel.message.mt.mms.core.messages.lghv.link;


import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.common.protocol.LghvProtocol.*;

@Getter
public class LghvLinkAckMessage extends LghvMessage {


    private String result = "";

    public LghvLinkAckMessage() {
        super(LINK_ACK_MSG_TYPE, LINK_ACK_RESULT_LENGTH);
    }

    @Builder
    public LghvLinkAckMessage(String result) {
        super(LINK_ACK_MSG_TYPE, LINK_ACK_RESULT_LENGTH);

        this.result = Objects.requireNonNullElse(result, "");
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LghvLinkAckMessage that = (LghvLinkAckMessage) object;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}