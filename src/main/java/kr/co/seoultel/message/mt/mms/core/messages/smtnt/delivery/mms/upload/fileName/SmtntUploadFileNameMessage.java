package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.fileName;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SmtntUploadFileNameMessage extends SmtntMessage {

    private final String JPG = ".jpg";
    @SerializedName("FileExtension")
    protected String fileExtension; // 사용자 데이터

    public SmtntUploadFileNameMessage() {
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_NAME_METHOD_HEADER);

        this.fileExtension = JPG;
    }

    @Builder
    public SmtntUploadFileNameMessage(String extension){
        super(SmtntProtocol.SMTNT_UPLOAD_FILE_NAME_METHOD_HEADER);

        this.fileExtension = Objects.requireNonNullElse(extension, JPG);
    }

    @Override
    public String toString() {
        return "SmtntUploadFileNameMessage{" +
                    "method='" + method + '\'' +
                    ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
