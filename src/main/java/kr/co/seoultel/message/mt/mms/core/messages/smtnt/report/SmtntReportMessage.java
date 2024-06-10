package kr.co.seoultel.message.mt.mms.core.messages.smtnt.report;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class SmtntReportMessage extends SmtntMessage {
    @SerializedName("UserMsgId")
    protected String userMsgId = "";    // 사용자 메시지 아이디(umsMsgId)

    @SerializedName("UserMsgSubId")
    protected String userMsgSubId = ""; // 사용자 메시지 서브 아이디(srcMsgId)

    @SerializedName("ServerMsgId")
    protected String serverMsgId = "";  // 서버 메시지 아이디

    @SerializedName("MsgType")
    protected int msgType;      // 메시지 타입

    @SerializedName("PhoneNo")
    protected String phoneNo = "";      // 수신자 번호

    @SerializedName("Result")
    protected int result;       // 결과코드, 발송결과코드 참조

    @SerializedName("ResultMessage")
    protected String resultMessage = "";       // 결과코드, 발송결과코드 참조

    @SerializedName("Telecom")
    protected String telecom = "";      // 이통사

    @SerializedName("DeliveryTime")
    protected String deliveryTime = ""; // 휴대폰 도착 시간

    @SerializedName("UserId")
    protected String userId = "";     // 사용자 데이터

    @SerializedName("UserData")
    protected String userData = "";     // 사용자 데이터

    public SmtntReportMessage() {
        super(SmtntProtocol.SMTNT_REPORT_METHOD_HEADER);
    }

    @Builder
    public SmtntReportMessage(String userMsgId, String userMsgSubId, String serverMsgId,
                        int msgType, String phoneNo, int result, String resultMessage, String telecom,
                        String deliveryTime, String userData, String userId) {
        super(SmtntProtocol.SMTNT_REPORT_METHOD_HEADER);

        this.userMsgId = Objects.requireNonNullElse(userMsgId, "");           // 사용자 메시지 아이디
        this.userMsgSubId = Objects.requireNonNullElse(userMsgSubId, "");     // 사용자 메시지 서브 아이디
        this.serverMsgId = Objects.requireNonNullElse(serverMsgId, "");       // 서버 메시지 아이디
        this.msgType = msgType;                                               // 메시지 타입
        this.phoneNo = Objects.requireNonNullElse(phoneNo, "");               // 수신자 번호
        this.result = result;
        this.resultMessage = Objects.requireNonNullElse(resultMessage, "");   // 결과코드, 발송결과코드 참조
        this.telecom = Objects.requireNonNullElse(telecom, "");               // 이통사
        this.deliveryTime = Objects.requireNonNullElse(deliveryTime, "");     // 휴대폰 도착 시간
        this.userData = Objects.requireNonNullElse(userData, "");             // 사용자 데이터
        this.userId = Objects.requireNonNullElse(userId, "");
    }

    public boolean isTpsOver() {
        if (result == SmtntProtocol.DELIVERY_RESULT_EXCEED_TPS || result == SmtntProtocol.DELIVERY_RESULT_EXCEED_SEND_SPEED) return true;
        else return false;
    }
    @Override
    public String toString() {
        return "SmtntReportMessage{" +
                "method='" + method + '\'' +
                ", userMsgId='" + userMsgId + '\'' +
                ", userMsgSubId='" + userMsgSubId + '\'' +
                ", serverMsgId='" + serverMsgId + '\'' +
                ", msgType=" + msgType +
                ", phoneNo='" + phoneNo + '\'' +
                ", result=" + result + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", telecom='" + telecom + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", userId='" + userId + '\'' +
                ", userData='" + userData + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmtntReportMessage that = (SmtntReportMessage) o;
        return msgType == that.msgType && result == that.result && Objects.equals(userMsgId, that.userMsgId) && Objects.equals(userMsgSubId, that.userMsgSubId) && Objects.equals(serverMsgId, that.serverMsgId) && Objects.equals(phoneNo, that.phoneNo) && Objects.equals(resultMessage, that.resultMessage) && Objects.equals(telecom, that.telecom) && Objects.equals(deliveryTime, that.deliveryTime) && Objects.equals(userId, that.userId) && Objects.equals(userData, that.userData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMsgId, userMsgSubId, serverMsgId, msgType, phoneNo, result, resultMessage, telecom, deliveryTime, userId, userData);
    }
}