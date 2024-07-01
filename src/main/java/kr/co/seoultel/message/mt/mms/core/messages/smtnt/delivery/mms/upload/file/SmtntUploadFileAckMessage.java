package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.file;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class SmtntUploadFileAckMessage extends SmtntMessage {

    @SerializedName("Result")
    private int result;

    public SmtntUploadFileAckMessage() {
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_ACK_METHOD_HEADER);
    }

    public SmtntUploadFileAckMessage(int result) {
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_ACK_METHOD_HEADER);

        this.result = result;
    }

    @Override
    public String toString() {
        return "SmtntUploadFileAckMessage{" +
                    "method='" + method + '\'' +
                    ", result='" + result + '\'' +
                '}';
    }
}
