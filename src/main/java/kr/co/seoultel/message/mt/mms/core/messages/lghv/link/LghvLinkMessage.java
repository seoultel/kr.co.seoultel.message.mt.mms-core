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
public class LghvLinkMessage extends LghvMessage {
    private String code;              // °á°ú°ª

    @Builder
    public LghvLinkMessage(String code) {
        super(LINK_MSG_TYPE, LINK_CODE_LENGTH);

        this.code = code;
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
}