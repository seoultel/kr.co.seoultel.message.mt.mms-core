package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload;

import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.finish.SmtntFinishUploadAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.finish.SmtntFinishUploadMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.file.SmtntUploadFileAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.fileName.SmtntUploadFileNameAckMessage;
import kr.co.seoultel.message.mt.mms.core.util.CommonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Getter
@NoArgsConstructor
public class SmtntMultipartData {

    /*
     * 이미지 고유 값
     * 1. imageId : ImageId in MCMP
     * 2. bytes : bytes of Image[imageId]
     */
    private String imageId;
    private byte[] bytes;


    /*  ACK 공통 결과  */
    private int result;

    /*  업로드 파일명 응답  */
    @Setter
    private String fileName; // for smtnt


    private LocalDateTime lastUpdateTime;
    private final String fileType = "IMG";

    @Getter @Setter
    private SmtntMultipartStatus status = SmtntMultipartStatus.REQ_FILE_NAME_STATUS;



    public SmtntMultipartData(String imageId, byte[] bytes) {
        this.imageId = imageId;
        this.bytes = bytes;
        this.lastUpdateTime = LocalDateTime.now();
    }




    public boolean isSuccessfulResponse() {
        return this.result == SmtntProtocol.RESULT_SUCCESS;
    }




    /*
     * ============================================================
     * ==                                                        ==
     * ==                  UPLOAD FILE NAME REQ                  ==
     * ==                                                        ==
     * ============================================================
     */


    /*
     * SmtntClient의 doSubmit() 메서드에서 전송한 업로드 이미지명 요청에 대한 응답이 오기까지 Blocking;
     * 이후 업로드 이미지명 요청의 성공 여부를 반환한다.
     */
    public boolean isRecvUploadFileNameAckAndIsSuccess() {
        CompletableFuture.runAsync(() -> {
            do {
                CommonUtil.doThreadSleep(3);
            } while (status.type.equals(SmtntProtocol.WAIT_RES_UPLOAD_FILE_NAME));
        }).join();

        return status.equals(SmtntMultipartStatus.SUCC_RES_FILE_NAME_STATUS) && isSuccessfulResponse();
    }


    public void recvUploadFileNameAckMessage(SmtntUploadFileNameAckMessage smtntUploadFileNameAckMessage) {
        this.result = smtntUploadFileNameAckMessage.getResult();
        this.status = SmtntMultipartStatus.SUCC_RES_FILE_NAME_STATUS;
        this.lastUpdateTime = LocalDateTime.now();

        this.fileName = smtntUploadFileNameAckMessage.getFileName();
    }

    public boolean isWaitResFileNameStatus() {
        return this.status.equals(SmtntMultipartStatus.WAIT_RES_FILE_NAME_STATUS);
    }



    /*
     * ============================================================
     * ==                                                        ==
     * ==                    UPLOAD FILE REQ                     ==
     * ==                                                        ==
     * ============================================================
     */


    /* ABOUT FILE REQUEST */
    public boolean isRecvUploadFileAckAndIsSuccess() {
        CompletableFuture.runAsync(() -> {
            do {
                CommonUtil.doThreadSleep(3);
            } while (status.type.equals(SmtntProtocol.WAIT_RES_UPLOAD_FILE));
        }).join();

        return status.equals(SmtntMultipartStatus.SUCC_RES_UPLOAD_FILE_STATUS) && isSuccessfulResponse();
    }

    public void recvUploadFileAckMessage(SmtntUploadFileAckMessage smtntUploadFileAckMessage) {
        this.lastUpdateTime = LocalDateTime.now();
        this.result = smtntUploadFileAckMessage.getResult();
        this.status = SmtntMultipartStatus.SUCC_RES_UPLOAD_FILE_STATUS;
    }

    public boolean isWaitResUploadFileStatus() {
        return this.status.equals(SmtntMultipartStatus.WAIT_RES_UPLOAD_FILE_STATUS);
    }


    /*
     * ============================================================
     * ==                                                        ==
     * ==                   FINISH UPLOAD REQ                    ==
     * ==                                                        ==
     * ============================================================
     */

    public void recvFinishUploadFileAckMessage(SmtntFinishUploadAckMessage smtntFinishUploadAckMessage) {
        this.lastUpdateTime = LocalDateTime.now();
        this.result = smtntFinishUploadAckMessage.getResult();
        this.status = SmtntMultipartStatus.SUCC_RES_UPLOAD_FINISH_STATUS;
    }

    public boolean isRecvUploadFinishAckAndIsSuccess() {
        CompletableFuture.runAsync(() -> {
            do {
                CommonUtil.doThreadSleep(3);
            } while (status.type.equals(SmtntProtocol.WAIT_UPLOAD_FINISH_TYPE));
        }).join();

        return status.equals(SmtntMultipartStatus.SUCC_RES_UPLOAD_FINISH_STATUS) && isSuccessfulResponse();
    }

    public boolean isWaitResFinishUploadFileStatus() {
        return this.status.equals(SmtntMultipartStatus.WAIT_RES_UPLOAD_FINISH_STATUS);
    }

    public SmtntFinishUploadMessage createSmtntFinishUploadMessage() {
        return SmtntFinishUploadMessage.builder().fileName(fileName).fileSize(bytes.length).build();
    }


//    @Override
//    public String toString() {
//        return "SmtntMultipartData{" +
//                "bytes=" + Arrays.toString(bytes) +
//                ", imageId='" + imageId + '\'' +
//                ", fileType='" + fileType + '\'' +
//                ", uploadFileName='" + uploadFileName + '\'' +
//                ", status=" + status +
//                '}';
//    }


    @Override
    public String toString() {
        return "SmtntMultipartData{" +
                "bytes=" + Arrays.toString(bytes) +
                ", result='" + result + '\'' +
                ", imageId='" + imageId + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadFileName='" + fileName + '\'' +
                ", status=" + status +
                '}';
    }
}
