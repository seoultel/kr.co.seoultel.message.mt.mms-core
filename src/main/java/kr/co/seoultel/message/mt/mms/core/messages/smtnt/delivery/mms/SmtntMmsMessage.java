package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.SmtntMultipartData;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public class SmtntMmsMessage extends SmtntDeliveryMessage {

    @Builder
    public SmtntMmsMessage(String userMsgId, String userMsgSubId, String phoneNo, String callbackNo,
                            String tranId, String subject, String message, String userData , String resellerCode,
                            List<SmtntMultipartData> smtntMultipartDataList) {
        this.userMsgId = userMsgId == null ? "" : userMsgId;
        this.userMsgSubId = userMsgSubId == null ? "" : userMsgSubId;
        this.phoneNo = phoneNo == null ? "" : phoneNo;
        this.callbackNo = callbackNo == null ? "" : callbackNo;
        this.tranId = tranId == null ? "" : tranId;
        this.subject = subject == null ? "" : subject;
        this.message = message == null ? "" : message;
        this.userData = userData == null ? "" : userData;
        this.resellerCode = resellerCode == null ? "" : resellerCode;
        this.fileCount = 0;

        for (SmtntMultipartData smtntMultipartData : smtntMultipartDataList) {
            if (smtntMultipartData == null) break;

            addFile(smtntMultipartData);
        }
    }

    public void addFile(@NonNull SmtntMultipartData smtntMultipartData) {
        switch (fileCount) {
            case 0:
                this.fileName1 = smtntMultipartData.getFileName();
                this.fileType1 = smtntMultipartData.getFileType();
                break;

            case 1:
                this.fileName2 = smtntMultipartData.getFileName();
                this.fileType2 = smtntMultipartData.getFileType();
                break;

            case 2:
                this.fileName3 = smtntMultipartData.getFileName();
                this.fileType3 = smtntMultipartData.getFileType();
                break;
        }

        fileCount += 1;
    }

    @Override
    public String toString() {
        return "SmtntDeliveryMessage{" +
                "method='" + method + '\'' +
                ", userMsgId='" + userMsgId + '\'' +
                ", userMsgSubId='" + userMsgSubId + '\'' +
                ", msgType=" + msgType +
                ", phoneNo='" + phoneNo + '\'' +
                ", callbackNo='" + callbackNo + '\'' +
                ", tranId='" + tranId + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", fileCount=" + fileCount +
                ", fileType1='" + fileType1 + '\'' +
                ", fileName1='" + fileName1 + '\'' +
                ", fileType2='" + fileType2 + '\'' +
                ", fileName2='" + fileName2 + '\'' +
                ", fileType3='" + fileType3 + '\'' +
                ", fileName3='" + fileName3 + '\'' +
                ", userData='" + userData + '\'' +
                '}';
    }
}
