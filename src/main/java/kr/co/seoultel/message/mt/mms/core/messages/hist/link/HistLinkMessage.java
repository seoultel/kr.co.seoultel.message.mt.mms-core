package kr.co.seoultel.message.mt.mms.core.messages.hist.link;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;

import java.util.Objects;

public class HistLinkMessage extends HistMessage {
    public HistLinkMessage() {
        super(HistProtocol.HIST_LINK_HEAD_TYPE, HistProtocol.HIST_LINK_MSG_LENG);
    }

    @Override
    @Deprecated
    protected void writeBody(ByteBuf byteBuf) {}

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);
    }

    @Override
    public String toString() {
        return "HistLinkMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                '}';
    }
}
