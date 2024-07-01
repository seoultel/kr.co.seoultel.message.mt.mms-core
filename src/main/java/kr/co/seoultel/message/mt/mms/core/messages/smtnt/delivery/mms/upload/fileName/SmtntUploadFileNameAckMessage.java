package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.fileName;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class SmtntUploadFileNameAckMessage extends SmtntMessage {

    @SerializedName("FileName")
    protected String fileName; // 사용자 데이터

    @SerializedName("Result")
    protected int result; // 사용자 데이터

    public SmtntUploadFileNameAckMessage() {
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_NAME_ACK_METHOD_HEADER);
    }

    @Builder
    public SmtntUploadFileNameAckMessage(String fileName, int result){
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_NAME_ACK_METHOD_HEADER);

        this.fileName = Objects.requireNonNull(fileName);
        this.result = result;
    }

    @Override
    public String toString() {
        return "SmtntUploadFileNameAckMessage{" +
                    "method='" + method + '\'' +
                    ", fileName='" + fileName + '\'' +
                    ", result='" + result + '\'' +
                '}';
    }
}
