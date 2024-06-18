package kr.co.seoultel.message.mt.mms.core.messages.hist;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.encrpyt.HistEncryptor;
import kr.co.seoultel.message.mt.mms.core.messages.Message;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class HistMessage extends Message implements ConvertableToByteBuf {

    protected String headType;
    protected int msgLeng;

    protected HistMessage(String headType, int msgLeng) {
        this.headType = headType;
        this.msgLeng = msgLeng;
    }

    protected void writeHeader(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(headType, HistProtocol.HIST_HEAD_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgLeng, HistProtocol.HIST_MSG_LENG));
    }

    protected abstract void writeBody(ByteBuf byteBuf);

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        this.headType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.HIST_HEAD_TYPE_LENGTH);
        this.msgLeng = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.HIST_MSG_LENG);
    }

    protected void addMsgLeng(int alpha) {
        this.msgLeng += alpha;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HistMessage that = (HistMessage) object;
        return msgLeng == that.msgLeng && Objects.equals(headType, that.headType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headType, msgLeng);
    }
}
