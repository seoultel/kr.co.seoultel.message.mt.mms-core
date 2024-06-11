package kr.co.seoultel.message.mt.mms.core.messages.hist.link;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;

public class HistLinkAckMessage extends HistMessage {
    public HistLinkAckMessage() {
        super(HistProtocol.HIST_LINK_ACK_HEAD_TYPE, HistProtocol.HIST_LINK_ACK_MSG_LENG);
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
        return "HistLinkAckMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                '}';
    }
}
