package kr.co.seoultel.message.mt.mms.core.messages.smtnt.report;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;


@Getter
public class SmtntReportAckMessage extends SmtntMessage {

    @SerializedName("UserMsgId")
    protected String userMsgId = "";    //  사용자 메시지 아이디
    @SerializedName("UserMsgSubId")
    protected String userMsgSubId = ""; //  사용자 메시지 서브 아이디
    @SerializedName("ServerMsgId")
    protected String serverMsgId = "";  //  서버 메시지 아이디
    @SerializedName("Result")
    protected int result;       //  결과코드, 발송결과코드 참조

    public SmtntReportAckMessage() {
        super(SmtntProtocol.SMTNT_REPORT_ACK_METHOD_HEADER);
    }

    @Builder
    public SmtntReportAckMessage(String userMsgId, String userMsgSubId, String serverMsgId, int result) {
        super(SmtntProtocol.SMTNT_REPORT_ACK_METHOD_HEADER);

        this.userMsgId = userMsgId == null ? "" : userMsgId;            //  사용자 메시지 아이디
        this.userMsgSubId = userMsgSubId == null ? "" : userMsgSubId;   //  사용자 메시지 서브 아이디
        this.serverMsgId = serverMsgId == null ? "" : serverMsgId;      //  서버 메시지 아이디
        this.result = result;                                           //  결과코드, 발송결과코드 참조
    }



    @Override
    public String toString() {
        return "SmtntReportAckMessage{" +
                    "method='" + method + '\'' +
                    ", userMsgId='" + userMsgId + '\'' +
                    ", userMsgSubId='" + userMsgSubId + '\'' +
                    ", serverMsgId='" + serverMsgId + '\'' +
                    ", result=" + result +
                '}';
    }
}
