package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.file;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.SmtntMultipartData;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;


@Slf4j
@Getter
public class SmtntUploadFileMessage extends SmtntMessage {

    @SerializedName("Data")
    private String data;

    public SmtntUploadFileMessage() {
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_METHOD_HEADER);
    }
    public SmtntUploadFileMessage(SmtntMultipartData smtntMultipartData) {

        super(SmtntProtocol.SMTNT_UPLOAD_FILE_METHOD_HEADER);

        this.data = Base64.getEncoder().encodeToString(smtntMultipartData.getBytes());
    }


    @Override
    public String toString() {
        return "SmtntUploadFileMessage{" +
                    "method='" + method + '\'' +
                    ", data.length=" + data.length() +
                '}';
    }
}
