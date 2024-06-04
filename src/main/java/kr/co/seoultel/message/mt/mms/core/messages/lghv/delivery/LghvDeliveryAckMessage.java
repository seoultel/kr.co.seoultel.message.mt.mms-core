package kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;


@Getter
@NoArgsConstructor
public class LghvDeliveryAckMessage extends LghvMessage {

    private String result;              // 결과값
    private String deliveryType;        // 전송타입
    private String msgId;               // 메세지고유값
    private String msgMsec;             // 전송처리시간
    private String destAddr;            // 수신번호
    private String reserved;            // 예비

    @Builder
    public LghvDeliveryAckMessage(int msgLen, String result, String deliveryType, String msgId, String msgMsec, String destAddr, String reserved) {
        super(DELIVERY_ACK_MSG_TYPE, DELIVERY_ACK_BODY_LEN);

        this.result = result;
        this.deliveryType = deliveryType;
        this.msgId = msgId;
        this.msgMsec = msgMsec;
        this.destAddr = destAddr;
        this.reserved = reserved;
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(deliveryType, DELIVERY_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgId, MSG_ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgMsec, MSG_MSEC_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(destAddr, DESTADDR_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(reserved, DELIVERY_ACK_RESERVED_LENGTH));
    }


    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf); // 8

        // Body
        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, RESULT_LENGTH); // 5
        this.deliveryType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, DELIVERY_TYPE_LENGTH);  // 4
        this.msgId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, MSG_ID_LENGTH);  // 20
        this.msgMsec = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, MSG_MSEC_LENGTH);  // 7
        this.destAddr = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, DESTADDR_LENGTH); // 50
        this.reserved = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, DELIVERY_ACK_RESERVED_LENGTH); // 6
    }

    @Override
    public String toString() {
        return "LghvDeliveryAckMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' + ", tail.length=" + tail.length() +
                ", result='" + result + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", msgId='" + msgId + '\'' +
                ", msgMsec='" + msgMsec + '\'' +
                ", destAddr='" + destAddr + '\'' +
                ", reserved='" + reserved + '\'' +
                '}';
    }
}