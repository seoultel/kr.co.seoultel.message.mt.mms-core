package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SmtntDeliveryAckMessage extends SmtntMessage {

    @SerializedName("UserMsgId")
    protected String userMsgId = ""; //  문자 사용자 메시지 아이디

    @SerializedName("UserMsgSubId")
    protected String userMsgSubId = ""; //  문자 사용자 메시지 서브 아이디

    @SerializedName("PhoneNo")
    protected String phoneNo = ""; //  문자 수신자번호

    @SerializedName("MsgType")
    protected int msgType = SmtntProtocol.MMS_MSG_TYPE; //  숫자 메시지 타입 4.SMS, 6 LMS/MMS, 7.알림톡 8.친구톡

    @SerializedName("Result")
    protected int result;   // 메세지 발송 결과, 발송결과코드

    @SerializedName("ResultMessage")
    protected String resultMessage = "";   // 메세지 발송 결과, 발송결과코드

    @SerializedName("UserData")
    protected String userData = ""; // 사용자 데이터

    public SmtntDeliveryAckMessage() {
        super(SmtntProtocol.SMTNT_DELIVERY_ACK_METHOD_HEADER);
    }


    @Builder
    public SmtntDeliveryAckMessage(String userMsgId, String userMsgSubId, String phoneNo, int result, String resultMessage, String userData){
        super(SmtntProtocol.SMTNT_DELIVERY_ACK_METHOD_HEADER);

        this.userMsgId = Objects.requireNonNullElse(userMsgId, "");
        this.userMsgSubId = Objects.requireNonNullElse(userMsgSubId, "");
        this.msgType = SmtntProtocol.MMS_MSG_TYPE;
        this.phoneNo = Objects.requireNonNullElse(phoneNo, "");
        this.result = result;
        this.resultMessage = Objects.requireNonNullElse(resultMessage, "");
        this.userData = Objects.requireNonNullElse(userData, "");
    }


    public boolean isSubmitSuccess() {
        return this.result == SmtntProtocol.DELIVERY_RESULT_SUCCESS;
    }

    public boolean isDuplicated() {
        return this.result == SmtntProtocol.DELIVERY_RESULT_DUP_SEND_ERR;
    }

    public boolean isTpsOver() {
        return (result == SmtntProtocol.DELIVERY_RESULT_EXCEED_TPS || result == SmtntProtocol.DELIVERY_RESULT_EXCEED_SEND_SPEED);
    }

    @Override
    public String toString() {
        return "SmtntDeliveryAckMessage{" +
                    "method='" + method + '\'' +
                    ", userMsgId='" + userMsgId + '\'' +
                    ", userMsgSubId='" + userMsgSubId + '\'' +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", msgType=" + msgType +
                    ", result='" + result + '\'' +
                    ", resultMessage='" + resultMessage + '\'' +
                    ", userData='" + userData + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmtntDeliveryAckMessage that = (SmtntDeliveryAckMessage) o;
        return msgType == that.msgType && result == that.result && Objects.equals(userMsgId, that.userMsgId) && Objects.equals(userMsgSubId, that.userMsgSubId) && Objects.equals(phoneNo, that.phoneNo) && Objects.equals(resultMessage, that.resultMessage) && Objects.equals(userData, that.userData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userMsgId, userMsgSubId, phoneNo, msgType, result, resultMessage, userData);
    }
}
