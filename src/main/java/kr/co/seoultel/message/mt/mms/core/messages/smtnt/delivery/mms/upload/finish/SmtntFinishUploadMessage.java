package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.finish;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class SmtntFinishUploadMessage extends SmtntMessage {

    @SerializedName("FileName")
    private String fileName;

    @SerializedName("FileSize")
    private int fileSize;

    public SmtntFinishUploadMessage() {
        super(SmtntProtocol.SMTNT_FINISH_UPLOAD_FILE_METHOD_HEADER);
    }

    @Builder
    public SmtntFinishUploadMessage(String fileName, int fileSize) {
        super(SmtntProtocol.SMTNT_FINISH_UPLOAD_FILE_METHOD_HEADER);

        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "SmtntFinishUploadMessage{" +
                    "method='" + method + '\'' +
                    ", fileName='" + fileName + '\'' +
                    ", fileSize=" + fileSize +
                '}';
    }
}
