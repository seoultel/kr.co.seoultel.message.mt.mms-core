package kr.co.seoultel.message.mt.mms.core.messages.lghv.bind;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;

@NoArgsConstructor
public class LghvBindMessage extends LghvMessage {

    private String encode;
    private String lineType;
    private String userId;
    private String agentId;
    private String userPwd;
    private String report;
    private String info;

    @Builder
    public LghvBindMessage(String encode, String lineType, String userId, String agentId, String userPwd, String report, String info) {
        super(BIND_MSG_TYPE, BIND_BODY_LENGTH);
        
        this.encode = encode;
        this.lineType = lineType;
        this.userId = userId;
        this.agentId = agentId;
        this.userPwd = userPwd;
        this.report = report;
        this.info = info;
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(encode, ENCODE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(lineType, LINE_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(userId, USER_ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(agentId, AGENT_ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(userPwd, USER_PWD_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(report, REPORT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(info, INFO_LENGTH));
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
        this.encode = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, ENCODE_LENGTH);
        this.lineType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LINE_TYPE_LENGTH);
        this.userId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, USER_ID_LENGTH);
        this.agentId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, AGENT_ID_LENGTH);
        this.userPwd = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, USER_PWD_LENGTH);
        this.report = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, REPORT_LENGTH);
        this.info = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, INFO_LENGTH);
    }




    public static LghvBindMessage createMessageForBindToDeliveryServer(String userId, String agentId, String userPwd) {
        return LghvBindMessage.builder()
                .encode(BIND_DEFAULT_ENCODE)
                .lineType(SEND_LINE_TYPE)
                .userId(userId)
                .agentId(agentId)
                .userPwd(userPwd)
                .report(REQUIRED_VALUE_REPORT)
                .info(INFO_VALUE)
                .build();
    }

    public static LghvBindMessage createMessageForBindToReportServer(String userId, String agentId, String userPwd) {
        return LghvBindMessage.builder()
                .encode(BIND_DEFAULT_ENCODE)
                .lineType(RECV_LINE_TYPE)
                .userId(userId)
                .agentId(agentId)
                .report(REQUIRED_VALUE_REPORT)
                .userPwd(userPwd)
                .info(INFO_VALUE)
                .build();
    }

    @Override
    public String toString() {
        return "LghvBindMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' + ", tail.length=" + tail.length() +
                ", encode='" + encode + '\'' +
                ", lineType='" + lineType + '\'' +
                ", userId='" + userId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", report='" + report + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}