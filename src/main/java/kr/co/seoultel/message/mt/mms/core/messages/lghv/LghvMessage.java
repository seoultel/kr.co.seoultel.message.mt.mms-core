package kr.co.seoultel.message.mt.mms.core.messages.lghv;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.Message;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
public abstract class LghvMessage extends Message implements ConvertableToByteBuf {
    /**
     *  ** Lghv Header **
     *
     *  1. MsgType(1C)
     *     ->  BIND = 'b', BIND_ACK = 'B', DELIVERY = 'd', DELIVERY_ACK = 'D', REPORT = 'r', REPORT_ACK = 'R', LINK_CHECK = 'l', LINK_ACK = 'L'
     *
     *  2. MsgLen(4C)
     *     ->  0 ~ 9999
     *
     *  3. Tail(3C)
     *     ->  0x00 0xfe 0xff
     *
     */
    @Getter
    protected String msgType;

    @Setter
    protected int msgLen;

    @Getter
    protected String tail = LghvProtocol.TAIL;


    public LghvMessage(String msgType) {
        this.msgType = msgType;
    }


    public LghvMessage(String msgType, int msgLen) {
        this.msgType = msgType;
        this.msgLen = msgLen;
    }


//    private String certKey;

    protected void writeHeader(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgType, LghvProtocol.MSG_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgLen, LghvProtocol.MSG_LEN_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(tail, LghvProtocol.TAIL_LENGTH));
    }

    protected abstract void writeBody(ByteBuf byteBuf);

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        // Body
        this.msgType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.MSG_TYPE_LENGTH);
        this.msgLen = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, LghvProtocol.MSG_LEN_LENGTH);
        byteBuf.skipBytes(LghvProtocol.TAIL_LENGTH);
//        this.tail = ByteBufConvertor.getStrPropertyInByteBuf(byteBuf, TAIL_LENGTH);
    }


    public void addMsgLen(int msgLen) {
        this.msgLen += msgLen;
    }

}
