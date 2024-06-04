package kr.co.seoultel.message.mt.mms.core.messages.smtnt;

public class SmtntProtocol {
    public static final String SMTNT = "SMTNT";
    public static final String SMTNT_GROUP = "SMTT";

    public static final String SMTNT_HEADER = "Header";
    public static final String SMTNT_BODY = "Body";

    public static final String SMTNT_METHOD = "Method";


    public static final String SMTNT_COMMON_SUCCESS_RESULT_STRING = "0"; // 성공


    /* PRIVATE KEY REQUEST */
    public static final String SMTNT_PRIVATE_KEY_METHOD_HEADER = "PrivateKeyReq";
    public static final String SMTNT_PRIVATE_KEY_ACK_METHOD_HEADER = "PrivateKeyRes";


    public static final String SMTNT_BIND_METHOD_HEADER = "BindReq";


    public static final String BIND_ID = "BindId";
    public static final String BIND_PWD = "BindPwd";
    public static final String VERSION = "Version";


    public static final String SMTNT_BIND_ACK_METHOD_HEADER = "BindRes";




    public static final int BIND_SUCCESS = 0; // 시스템 오류
    public static final int BIND_SYS_FAIL = 1; // 시스템 오류
    public static final int BIND_DUP_ERR = 2; // 중복 접속
    public static final int BIND_BLK_IP = 3; // 허용된 아이피가 아님
    public static final int BIND_ID_ERR = 4; // 사용자 아이디 오류
    public static final int BIND_PWD_ERR = 5; // 사용자 암호 오류
    public static final int BIND_BLK_ID = 6; // 사용이 중지된 아이디
    public static final int BIND_VERSION_ERR = 7; // 버전 오류
    public static final int BIND_INSUFF_CNT_OF_MONTH = 904; // 월 발송건수 부족
    public static final int BIND_INSUFF_CNT_OF_DAY = 905; // 일일 발송건수 부족
    public static final int BIND_INSUFF_POINT = 906; // 보유금액 부족(선불)
    public static final int BIND_RCV_DATA_ERR = 911; // 수신데이터 오류



    /* LINK */
    public static final String SMTNT_LINK_METHOD_HEADER = "AliveReq";
    public static final String SMTNT_LINK_ACK_METHOD_HEADER = "AliveRes";


    /* DELIVERY */
    public static final String SMTNT_DELIVERY_METHOD_HEADER = "MsgSendReq";

    public static final int MMS_MSG_TYPE = 6;


    /* DELIVERY_ACK */
    public static final String SMTNT_DELIVERY_ACK_METHOD_HEADER = "MsgSendRes";


    public static final int RESULT_SUCCESS =  0; //  성공
    public static final int DELIVERY_RESULT_SUCCESS =  0; //  성공
    public static final int DELIVERY_RESULT_API_VERSION_ERR =  1; //  API 버전오류
    public static final int DELIVERY_RESULT_AUTH_FAILED =  2; //  인증실패
    public static final int DELIVERY_RESULT_BIND_ERR =  3; //  BIND 미수형
    public static final int DELIVERY_RESULT_HOST_ERR =  4; //  호스팅 시스템 내부오류
    public static final int DELIVERY_RESULT_MSG_FORMAT_ERR =  5; //  에시지 형식 오류
    public static final int DELIVERY_RESULT_EXPIRED_MSG =  6; //  유효기간 만료
    public static final int DELIVERY_RESULT_RETIRED_NUMBER =  7; //  결번
    public static final int DELIVERY_RESULT_POWER_OFF =  8; //  단말기 Power Off
    public static final int DELIVERY_RESULT_SHADOW =  9; //  음영
    public static final int DELIVERY_RESULT_EXCEED_SENDABLE_CNT =  10; //  전송건수 초과
    public static final int DELIVERY_RESULT_EXCEED_SEND_SPEED =  11; //  전송속도 초과
    public static final int DELIVERY_RESULT_NBR_MOVE =  12; //  번호이동
    public static final int DELIVERY_RESULT_INVALID_NPDB =  13; //  NPDB 불일치
    public static final int DELIVERY_RESULT_HO_ERR =  14; //  호 처리 실패
    public static final int DELIVERY_RESULT_SEND_FAIL =  15; //  단말기 전송 실패
    public static final int DELIVERY_RESULT_NO_FILE_ERR =  16; //  파일이 없음
    public static final int DELIVERY_RESULT_KISA_SPAM_BLK =  17; //  키사 스팸 차단
    public static final int DELIVERY_RESULT_NONE_MSG =  18; //  전달 메시지 없음
    public static final int DELIVERY_RESULT_INPUT_DATA_ERR =  19; //  입력 데이터 오류
    public static final int DELIVERY_RESULT_EXCEED_MSG_STORE_CNT =  20; //  에시지 저장개수 초과
    public static final int DELIVERY_RESULT_INVALID_PARAM_ERR =  21; //  잘못된 파라에터
    public static final int DELIVERY_RESULT_MSG_SEND_FAIL =  29; //  메시지 전송 실패
    public static final int DELIVERY_RESULT_FAILED_MSG_REV_CHK =  31; //  메시지 수신확인 안됨
    public static final int DELIVERY_RESULT_INTERNAL_SYS_ERR =  32; //  내부 시스템 오류
    public static final int DELIVERY_RESULT_PHN_NBR_ERR =  33; //  전화번호 오류
    public static final int DELIVERY_RESULT_MSG_LEN_ERR =  35; //  메시지 길이 제한 오류
    public static final int DELIVERY_RESULT_FAILED_TO_SEND_IMAGE =  37; //  메시지에 포함된 이미지를 전송할 수 없음
    public static final int DELIVERY_RESULT_NONE_ORIGIN_CODE_ERR =  39; //  특부가사업자 재판매사코드 미입력
    public static final int DELIVERY_RESULT_FORMAT_DESTINE =  315; //  세척 미준수
    public static final int DELIVERY_RESULT_UNREGISTERED_CALLBACK =  316; //  발신번호 미등록
    public static final int DELIVERY_RESULT_DESTINE_TRAN =  317; //  발신번호 변작으로 등록된 발신번호
    public static final int DELIVERY_RESULT_NBR_STEAL_BLK_ERR =  318; //  번호도용문자 차단서비스에 가입된 번호
    public static final int DELIVERY_RESULT_DUP_SEND_ERR =  320; //  중복발송 에러
    public static final int DELIVERY_RESULT_BLK_WORD =  800; //  발송메시지에 허용 되지 않은 문구가 포함됨
    public static final int DELIVERY_RESULT_DEST_CNT_OVER =  900; //  대량전송 동보갯수 초과
    public static final int DELIVERY_RESULT_NOT_SENDABLE_TIME =  901; //  발송 가능시간이 아님
    public static final int DELIVERY_RESULT_NOT_LOGIN =  902; //  로그인 상태가 아님
    public static final int DELIVERY_RESULT_EXCEED_TPS =  903; //  발송속도 초과
    public static final int DELIVERY_RESULT_MONTHCNT_OVER =  904; //  월 제한건수 초과
    public static final int DELIVERY_RESULT_DAILYCNT_OVER =  905; //  일 제한건수 초과
    public static final int DELIVERY_RESULT_INSUFF_MONEY =  906; //  보유 금액 부족
    public static final int DELIVERY_RESULT_SYSTEM_ERR =  907; //  시스템 과부하
    public static final int DELIVERY_RESULT_OVER_FIELD_SIZE =  908; //  특정필드가 허용된 길이를 초과함
    public static final int DELIVERY_RESULT_CONTENT_CNT_ERR =  909; //  첨부파일 파일갯수 오류
    public static final int DELIVERY_RESULT_CONTENT_SIZE_ERR =  910; //  첨부파일 크기 오류
    public static final int DELIVERY_RESULT_RCV_DATA_ERR =  911; //  수신데이터 오류입니다.
    public static final int DELIVERY_RESULT_SPAM_BLK =  997; //  자체 스팸 차단
    public static final int DELIVERY_RESULT_NONE_CONTRIBUTE_INFO =  998; //  분배정보 없음
    public static final int DELIVERY_RESULT_ETC =  999; //  기타 오류
    public static final int DELIVERY_RESULT_INVALID_CALLBACK_PROFILE_KEY =  22; //  발신 프로필 키가 유효하지 않음, KKO
    public static final int DELIVERY_RESULT_NOT_FINDABLE_CALLBACK_PROFILE_KEY =  23; //  발신 프로필을 찾을 수 없음, KKO
    public static final int DELIVERY_RESULT_REMOVED_CALLBACK_PROFILE_KEY =  24; //  삭제된 발신프로필, KKO
    public static final int DELIVERY_RESULT_BLK_CALLBACK_PROFILE_KEY =  25; //  차단 상태의 발신프로필, KKO
    public static final int DELIVERY_RESULT_BLK_YELLOW_ID =  26; //  차단 상태의 옐로아이디, KKO
    public static final int DELIVERY_RESULT_CLOSED_YELLOW_ID =  27; //  닫힘 상태의 옐로아이디, KKO
    public static final int DELIVERY_RESULT_RMOVED_YELLOW_ID =  28; //  삭제 상태의 옐로아이디, KKO
    public static final int DELIVERY_RESULT_NOT_FOUND_TEMPLATE =  36; //  템플릿을 찾을 수 없음, KKO
    public static final int DELIVERY_RESULT_KKO_TEMPLATE_ERR =  30; //  템필릿 일치 확인 시 오류, KKO
    public static final int DELIVERY_RESULT_KKO_INVALID_MSG_BTN =  38; //  메시지 버튼이 템플릿과 일치하지 않음, KKO



    /* UPLOAD FILE_NAME REQUEST */
    public static final String SMTNT_UPLOAD_FILE_NAME_METHOD_HEADER = "UploadFileNameReq";
    public static final String SMTNT_UPLOAD_FILE_NAME_ACK_METHOD_HEADER = "UploadFileNameRes";

    /* UPLOAD FILE REQUEST */
    public static final String SMTNT_UPLOAD_FILE_METHOD_HEADER = "UploadFileReq";
    public static final String SMTNT_UPLOAD_FILE_ACK_METHOD_HEADER = "UploadFileRes";
    public static final String SMTNT_FINISH_UPLOAD_FILE_METHOD_HEADER = "UploadFileFinishReq";
    public static final String SMTNT_FINISH_UPLOAD_FILE_ACK_METHOD_HEADER = "UploadFileFinishRes";


    /* REPORT */
    public static final String SMTNT_REPORT_METHOD_HEADER = "ReportReq";

    /* REPORT_ACK */
    public static final String SMTNT_REPORT_ACK_METHOD_HEADER = "ReportRes";





    // SmtntMultipartData's Status type
    public static final String REQ_UPLOAD_FILE_NAME = "REQ_UPLOAD_FILE_NAME";
    public static final String WAIT_RES_UPLOAD_FILE_NAME = "WAIT_RES_UPLOAD_FILE_NAME";
    public static final String RCV_RES_UPLOAD_FILE_NAME = "RCV_RES_UPLOAD_FILE_NAME";
    public static final String REQ_UPLOAD_FILE_TYPE = "REQ_UPLOAD_FILE";
    public static final String WAIT_RES_UPLOAD_FILE = "WAIT_RES_UPLOAD_FILE_NAME";
    public static final String RCV_RES_UPLOAD_FILE = "RCV_RES_UPLOAD_FILE_NAME";
    public static final String REQ_UPLOAD_FINISH_TYPE = "REQ_FINISH_UPLOAD_FILE";
    public static final String WAIT_UPLOAD_FINISH_TYPE = "WAIT_FINISH_UPLOAD_FILE";
    public static final String RCV_RES_UPLOAD_FINISH_TYPE = "RCV_FINISH_UPLOAD_FILE";


}
