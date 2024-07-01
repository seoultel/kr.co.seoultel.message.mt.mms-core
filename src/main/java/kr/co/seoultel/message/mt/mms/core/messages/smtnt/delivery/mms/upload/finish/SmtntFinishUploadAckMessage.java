package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.finish;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class SmtntFinishUploadAckMessage extends SmtntMessage {

    @SerializedName("Result")
    private int result;

    public SmtntFinishUploadAckMessage() {
        super(SmtntProtocol.SMTNT_FINISH_UPLOAD_FILE_ACK_METHOD_HEADER);
    }

    @Builder
    public SmtntFinishUploadAckMessage(int result) {
        super(SmtntProtocol.SMTNT_FINISH_UPLOAD_FILE_ACK_METHOD_HEADER);

        this.result = result;
    }


    @Override
    public String toString() {
        return "SmtntFinishUploadAckMessage{" +
                "method='" + method + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
