package kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;


@Getter
public class SmtntDeliveryMessage extends SmtntMessage {

    @SerializedName("UserMsgId")
    protected String userMsgId = ""; //  문자 사용자 메시지 아이디
    @SerializedName("UserMsgSubId")
    protected String userMsgSubId = ""; //  문자 사용자 메시지 서브 아이디
    @SerializedName("MsgType")
    protected int msgType = SmtntProtocol.MMS_MSG_TYPE; //  4.SMS, 6 LMS/MMS, 7.알림톡, 71.알림톡 이미지 8.친구톡 9.RCS SMS, 10.RCS LMS, 11.RCS MMS, 12.RCS 템플릿
    @SerializedName("PhoneNo")
    protected String phoneNo = ""; //  문자 수신자번호
    @SerializedName("CallbackNo")
    protected String callbackNo = ""; //  문자 발신자번호
    @SerializedName("TranId")
    protected String tranId = ""; //  문자 고객사의 발신자 아이디
    @SerializedName("Subject")
    protected String subject = "제목 없음"; //  문자 제목(LMS, MMS일 때 사용)

    @SerializedName("Message")
    protected String message = ""; //  문자 메시지
    @SerializedName("KakaoAdFlag")
    protected String kakaoAdFlag = ""; //  문자 카카오 광고표시 “Y”.예, “N”.아니요
    @SerializedName("KakaoNationCode")
    protected String kakaoNationCode = ""; //  문자 카카오 발신 국가코드 “82”.한국
    @SerializedName("KakaoSenderKey")
    protected String kakaoSenderKey = ""; //  문자 카카오 발신 프로필키
    @SerializedName("KakaoTemplateCode")
    protected String kakaoTemplateCode = ""; //  문자 카카오 템플릿 코드
    @SerializedName("KakaoTimeout")
    protected int kakaoTimeout; //  문자 카카오 타임아웃
    @SerializedName("KakaoButton")
    protected String kakaoButton = ""; //  문자 카카오 버튼 메시지
    @SerializedName("FileCount")
    protected int fileCount; //  숫자 첨부파일 개수
    @SerializedName("FileType1")
    protected String fileType1 = ""; //  문자 파일타입 “IMG”.이미지
    @SerializedName("FileName1")
    protected String fileName1 = ""; //  문자 파일명
    @SerializedName("FileType2")
    protected String fileType2 = ""; //  문자 파일타입 “IMG”.이미지
    @SerializedName("FileName2")
    protected String fileName2 = ""; //  문자 파일명
    @SerializedName("FileType3")
    protected String fileType3 = ""; //  문자 파일타입 “IMG”.이미지
    @SerializedName("FileName3")
    protected String fileName3 = ""; //  문자 파일명
    @SerializedName("UserData")
    protected String userData = ""; //  문자 사용자 데이터
    // @SerializedName("KakaoImageLink")
    // protected String kakaoImageLink = ""; // 카카오 친구톡 이미지 링크
    // @SerializedName("KakaoUserKey")
    // protected String kakaoUserKey = ""; //  카카오 친구톡 사용자키
    // @SerializedName("KakaoTitle")
    // protected String kakaoTitle = ""; //  카카오 알림톡 문구강조
    // @SerializedName("KakaoQuick")
    // protected String kakaoQuick = ""; //  카카오 알림톡 퀵링크
    // @SerializedName("KakaoWide")
    // protected String kakaoWide = ""; //  카카오 친구톡 이미지 와이드(Y/N)
    // @SerializedName("RcsMessageBaseId")
    // protected String rcsMessageBaseId = ""; //  RCS Message BaseId(ex:RCS SMS-SS000000)
    // @SerializedName("RcsButton")
    // protected String rcsButton = ""; //  RCS 버튼
    // @SerializedName("RcsHeader")
    // protected int rcsHeader; //  RCS 광고문구여부(0:아님/1:문구임)
    // @SerializedName("RcsFooter")
    // protected String rcsFooter = ""; //  RCS 하단 수신거부 전화번호
    // @SerializedName("RcsCopyAllowed")
    // protected String rcsCopyAllowed = ""; //  RCS 단말 메시지의 복사기능 허용여부
    // @SerializedName("RcsAgencyId")
    // protected String rcsAgencyId = ""; //  RCS 발송 계정ID
    // @SerializedName("RcsBrandKey")
    // protected String rcsBrandKey = ""; //  RCS 브랜드Key
    // @SerializedName("RcsAgencyKey")
    // protected String rcsAgencyKey = ""; //  RCS 재판매사 Key
    @SerializedName("ResellerCode")
    protected String resellerCode = ""; //  문자 재판매사 식별코드(특부가 사업자인경우 필수-9자리)


    public SmtntDeliveryMessage() {
        super(SmtntProtocol.SMTNT_DELIVERY_METHOD_HEADER);
    }


    public SmtntDeliveryMessage(String userMsgId, String userMsgSubId, int msgType, String phoneNo, String callbackNo,
                                String tranId, String subject, String message, String kakaoAdFlag, String kakaoNationCode, String kakaoSenderKey,
                                String kakaoTemplateCode, int kakaoTimeout, String kakaoButton, int fileCount, String fileType1, String fileName1,
                                String fileType2, String fileName2, String fileType3, String fileName3, String userData,
                                // String kakaoImageLink,
                                // String kakaoUserKey, String kakaoTitle, String kakaoQuick, String kakaoWide, String rcsMessageBaseId, String rcsButton,
                                // int rcsHeader, String rcsFooter, String rcsCopyAllowed, String rcsAgencyId, String rcsBrandKey, String rcsAgencyKey,
                                String resellerCode) {
        super(SmtntProtocol.SMTNT_DELIVERY_METHOD_HEADER);

        this.userMsgId = Objects.requireNonNullElse(userMsgId, "");
        this.userMsgSubId = Objects.requireNonNullElse(userMsgSubId, "");
        this.msgType = Objects.requireNonNullElse(msgType, SmtntProtocol.MMS_MSG_TYPE);
        this.phoneNo = Objects.requireNonNullElse(phoneNo, "");
        this.callbackNo = Objects.requireNonNullElse(callbackNo, "");
        this.tranId = Objects.requireNonNullElse(tranId, "");
        this.subject = Objects.requireNonNullElse(subject, "");
        this.message = Objects.requireNonNullElse(message, "");
        this.kakaoAdFlag = Objects.requireNonNullElse(kakaoAdFlag, "");
        this.kakaoNationCode = Objects.requireNonNullElse(kakaoNationCode, "");
        this.kakaoSenderKey = Objects.requireNonNullElse(kakaoSenderKey, "");
        this.kakaoTemplateCode = Objects.requireNonNullElse(kakaoTemplateCode, "");
        this.kakaoTimeout = Objects.requireNonNullElse(kakaoTimeout, 0);
        this.kakaoButton = Objects.requireNonNullElse(kakaoButton, "");
        this.fileCount = Objects.requireNonNullElse(fileCount, 0);
        this.fileType1 = Objects.requireNonNullElse(fileType1, "");
        this.fileName1 = Objects.requireNonNullElse(fileName1, "");
        this.fileType2 = Objects.requireNonNullElse(fileType2, "");
        this.fileName2 = Objects.requireNonNullElse(fileName2, "");
        this.fileType3 = Objects.requireNonNullElse(fileType3, "");
        this.fileName3 = Objects.requireNonNullElse(fileName3, "");
        this.userData = Objects.requireNonNullElse(userData, "");
        // this.kakaoImageLink = Objects.requireNonNullElse(kakaoImageLink, "");
        // this.kakaoUserKey = Objects.requireNonNullElse(kakaoUserKey, "");
        // this.kakaoTitle = Objects.requireNonNullElse(kakaoTitle, "");
        // this.kakaoQuick = Objects.requireNonNullElse(kakaoQuick, "");
        // this.kakaoWide = Objects.requireNonNullElse(kakaoWide, "");
        // this.rcsMessageBaseId = Objects.requireNonNullElse(rcsMessageBaseId, "");
        // this.rcsButton = Objects.requireNonNullElse(rcsButton, "");
        // this.rcsHeader = rcsHeader;
        // this.rcsFooter = Objects.requireNonNullElse(rcsFooter, "");
        // this.rcsCopyAllowed = Objects.requireNonNullElse(rcsCopyAllowed, "");
        // this.rcsAgencyId = Objects.requireNonNullElse(rcsAgencyId, "");
        // this.rcsBrandKey = Objects.requireNonNullElse(rcsBrandKey, "");
        // this.rcsAgencyKey = Objects.requireNonNullElse(rcsAgencyKey, "");
        this.resellerCode = Objects.requireNonNullElse(resellerCode, "");
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
                ", resellerCode='" + resellerCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof SmtntDeliveryMessage)) return false;
        if (!super.equals(o)) return false;
        SmtntDeliveryMessage that = (SmtntDeliveryMessage) o;
        return msgType == that.msgType &&
                kakaoTimeout == that.kakaoTimeout &&
                fileCount == that.fileCount &&
                Objects.equals(userMsgId, that.userMsgId) &&
                Objects.equals(userMsgSubId, that.userMsgSubId) &&
                Objects.equals(phoneNo, that.phoneNo) &&
                Objects.equals(callbackNo, that.callbackNo) &&
                Objects.equals(tranId, that.tranId) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(message, that.message) &&
                Objects.equals(kakaoAdFlag, that.kakaoAdFlag) &&
                Objects.equals(kakaoNationCode, that.kakaoNationCode) &&
                Objects.equals(kakaoSenderKey, that.kakaoSenderKey) &&
                Objects.equals(kakaoTemplateCode, that.kakaoTemplateCode) &&
                Objects.equals(kakaoButton, that.kakaoButton) &&
                Objects.equals(fileType1, that.fileType1) &&
                Objects.equals(fileName1, that.fileName1) &&
                Objects.equals(fileType2, that.fileType2) &&
                Objects.equals(fileName2, that.fileName2) &&
                Objects.equals(fileType3, that.fileType3) &&
                Objects.equals(fileName3, that.fileName3) &&
                Objects.equals(userData, that.userData) &&
                Objects.equals(resellerCode, that.resellerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userMsgId, userMsgSubId, msgType, phoneNo, callbackNo, tranId, subject, message, kakaoAdFlag, kakaoNationCode, kakaoSenderKey, kakaoTemplateCode, kakaoTimeout, kakaoButton, fileCount, fileType1, fileName1, fileType2, fileName2, fileType3, fileName3, userData, resellerCode);
    }
}
