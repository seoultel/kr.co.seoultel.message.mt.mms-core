package kr.co.seoultel.message.mt.mms.core.messages.hist.event;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;

import java.util.Objects;

public class HistEventMessage extends HistMessage {

    protected String type = "";
    protected String data = "";


    public HistEventMessage() {
        super(HistProtocol.HIST_EVENT_HEAD_TYPE, HistProtocol.HIST_EVENT_MSG_LENG);
    }

    public HistEventMessage(String headType, int msgLeng, String type, String data) {
        super(HistProtocol.HIST_EVENT_HEAD_TYPE, HistProtocol.HIST_EVENT_MSG_LENG);

        this.type = Objects.requireNonNullElse(type, "");
        this.data = Objects.requireNonNullElse(data, "");
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(type, HistProtocol.EVENT_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(data, HistProtocol.EVENT_DATA_LENGTH));
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        this.type = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.EVENT_TYPE_LENGTH);
        this.data = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.EVENT_DATA_LENGTH);
    }
}
