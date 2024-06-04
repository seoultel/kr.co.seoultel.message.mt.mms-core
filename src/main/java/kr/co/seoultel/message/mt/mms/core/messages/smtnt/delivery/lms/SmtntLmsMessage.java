package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.lms;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import lombok.Builder;
import lombok.Getter;


@Getter
public class SmtntLmsMessage extends SmtntDeliveryMessage {
    @Builder
    public SmtntLmsMessage(String userMsgId, String userMsgSubId, String phoneNo, String callbackNo,
                            String tranId, String subject, String message, String userData , String resellerCode) {
        this.userMsgId = userMsgId == null ? "" : userMsgId;
        this.userMsgSubId = userMsgSubId == null ? "" : userMsgSubId;
        this.msgType = SmtntProtocol.MMS_MSG_TYPE;
        this.phoneNo = phoneNo == null ? "" : phoneNo;
        this.callbackNo = callbackNo == null ? "" : callbackNo;
        this.tranId = tranId == null ? "" : tranId;
        this.subject = subject == null ? "" : subject;
        this.message = message == null ? "" : message;
        this.userData = userData == null ? "" : userData;
        this.resellerCode = resellerCode == null ? "" : resellerCode;
    }

    @Override
    public String toString() {
        return "SmtntLmsMessage{" +
                    "method='" + method + '\'' +
                    ", userMsgId='" + userMsgId + '\'' +
                    ", userMsgSubId='" + userMsgSubId + '\'' +
                    ", msgType=" + msgType +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", callbackNo='" + callbackNo + '\'' +
                    ", tranId='" + tranId + '\'' +
                    ", subject='" + subject + '\'' +
                    ", message='" + message + '\'' +
                    ", kakaoAdFlag='" + kakaoAdFlag + '\'' +
                    ", kakaoNationCode='" + kakaoNationCode + '\'' +
                    ", kakaoSenderKey='" + kakaoSenderKey + '\'' +
                    ", kakaoTemplateCode='" + kakaoTemplateCode + '\'' +
                    ", kakaoTimeout=" + kakaoTimeout +
                    ", kakaoButton='" + kakaoButton + '\'' +
                    ", fileCount=" + fileCount +
                    ", fileType1='" + fileType1 + '\'' +
                    ", fileName1='" + fileName1 + '\'' +
                    ", fileType2='" + fileType2 + '\'' +
                    ", fileName2='" + fileName2 + '\'' +
                    ", fileType3='" + fileType3 + '\'' +
                    ", fileName3='" + fileName3 + '\'' +
                    ", userData='" + userData + '\'' +
                    // ", kakaoImageLink='" + kakaoImageLink + '\'' +
                    // ", kakaoUserKey='" + kakaoUserKey + '\'' +
                    // ", kakaoTitle='" + kakaoTitle + '\'' +
                    // ", kakaoQuick='" + kakaoQuick + '\'' +
                    // ", kakaoWide='" + kakaoWide + '\'' +
                    // ", rcsMessageBaseId='" + rcsMessageBaseId + '\'' +
                    // ", rcsButton='" + rcsButton + '\'' +
                    // ", rcsHeader=" + rcsHeader +
                    // ", rcsFooter='" + rcsFooter + '\'' +
                    // ", rcsCopyAllowed='" + rcsCopyAllowed + '\'' +
                    // ", rcsAgencyId='" + rcsAgencyId + '\'' +
                    // ", rcsBrandKey='" + rcsBrandKey + '\'' +
                    // ", rcsAgencyKey='" + rcsAgencyKey + '\'' +
                    ", resellerCode='" + resellerCode + '\'' +
                '}';
    }
}
