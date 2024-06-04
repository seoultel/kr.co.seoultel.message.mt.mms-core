package kr.co.seoultel.message.mt.mms.core.messages.lghv.report;


import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LghvReportMessage extends LghvMessage {

    private String result;          // 결과값
    private String deliveryType;    // 전송타입
    private String rtnType;           // 최종처리타입
    private String msgId;           // 메세지고유값
    private String deliveryTime;    // MSG 전송시간
    private String netId;


    public LghvReportMessage() {
        super(LghvProtocol.REPORT_MSG_TYPE, LghvProtocol.REPORT_BODY_LEN);
    }

    @Builder
    public LghvReportMessage(int msgLen, String result, String deliveryType, String rtnType, String msgId, String deliveryTime, String netId) {
        super(LghvProtocol.REPORT_MSG_TYPE, LghvProtocol.REPORT_BODY_LEN);

        this.result = result;
        this.deliveryType = deliveryType;
        this.rtnType = rtnType;
        this.msgId = msgId;
        this.deliveryTime = deliveryTime;
        this.netId = netId;
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, LghvProtocol.RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(deliveryType, LghvProtocol.DELIVERY_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(rtnType, LghvProtocol.RTN_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgId, LghvProtocol.MSG_ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(deliveryTime, LghvProtocol.DELIVERY_TIME_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(netId, LghvProtocol.NET_ID_LENGTH));
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
        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.RESULT_LENGTH);
        this.deliveryType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DELIVERY_TYPE_LENGTH);
        this.rtnType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.RTN_TYPE_LENGTH);
        this.msgId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.MSG_ID_LENGTH);
        this.deliveryTime = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DELIVERY_TIME_LENGTH);
        this.netId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.NET_ID_LENGTH);
    }




    public static LghvReportMessage createLMSMessage(String result, String umsMsgId, String netId) {
        return LghvReportMessage.builder()
                // Header
                .msgLen(LghvProtocol.REPORT_BODY_LEN)
                // Common Body
                .result(result)
                .deliveryType(LghvProtocol.LMS_DELIVERY_TYPE)
                .rtnType(LghvProtocol.LMS_DELIVERY_TYPE)
                .msgId(umsMsgId)
                .deliveryTime(DateUtil.getDate(0).substring(0, 15))
                .netId(netId)
                .build();
    }


    public static LghvReportMessage createMMSMessage(String result, String umsMsgId, String netId) {
        return LghvReportMessage.builder()
                // Header
                .msgLen(LghvProtocol.REPORT_BODY_LEN)
                // Common Body
                .result(result)
                .deliveryType(LghvProtocol.MMS_DELIVERY_TYPE)
                .rtnType(LghvProtocol.MMS_DELIVERY_TYPE)
                .msgId(umsMsgId)
                .deliveryTime(DateUtil.getDate(0).substring(0, 15))
                .netId(netId)
                .build();
    }

    @Override
    public String toString() {
        return "LghvReportMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' + ", tail.length=" + tail.length() +
                ", result='" + result + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", rtnType='" + rtnType + '\'' +
                ", msgId='" + msgId + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", netId='" + netId + '\'' +
                '}';
    }
}
