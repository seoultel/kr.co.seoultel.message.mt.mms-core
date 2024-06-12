package kr.co.seoultel.message.mt.mms.core.messages.lghv.link;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;

@Getter
public class LghvLinkMessage extends LghvMessage {
    private String code = "";

    public LghvLinkMessage() {
        super(LINK_MSG_TYPE, LINK_CODE_LENGTH);
    }

    @Builder
    public LghvLinkMessage(String code) {
        super(LINK_MSG_TYPE, LINK_CODE_LENGTH);

        this.code = Objects.requireNonNullElse(code, "");
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(code, LINK_CODE_LENGTH));
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
        this.code = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LINK_CODE_LENGTH);
    }


    @Override
    public String toString() {
        return "LghvLinkMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LghvLinkMessage that = (LghvLinkMessage) object;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}