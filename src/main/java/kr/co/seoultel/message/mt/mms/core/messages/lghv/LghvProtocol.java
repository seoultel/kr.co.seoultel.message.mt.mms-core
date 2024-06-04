package kr.co.seoultel.message.mt.mms.core.messages.lghv;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LghvProtocol {

    public static final String LGHV = "LGHV";

    /*
     * ******************************************
     * *                HEADER                  *
     * ******************************************
     */
    public static final int MSG_TYPE_LENGTH = 1;

    public static final String BIND_MSG_TYPE = "b";
    public static final String BIND_ACK_MSG_TYPE = "B";
    public static final String DELIVERY_MSG_TYPE = "d";
    public static final String DELIVERY_ACK_MSG_TYPE = "D";
    public static final String REPORT_MSG_TYPE = "r";
    public static final String REPORT_ACK_MSG_TYPE = "R";
    public static final String LINK_MSG_TYPE = "l";
    public static final String LINK_ACK_MSG_TYPE = "L";


    public static final String TAIL = new String(new byte[]{(byte) 0x00, (byte) 0xfe, (byte) 0xff});

    public static final int MSG_LEN_LENGTH = 4;
    public static final int TAIL_LENGTH = 3;
    public static final int HEADER_LENGTH = 8;


    /*
     * ******************************************
     * *                  BODY                  *
     * ******************************************
     */


    /*
     * ******************************************
     * *                  BIND                  *
     * ******************************************
     */
    public static final int ENCODE_LENGTH = 1;
    public static final String BIND_DEFAULT_ENCODE = "0";
    public static final String BIND_AES_ENCODE = "1";


    public static final int LINE_TYPE_LENGTH = 1;
    public static final String SEND_LINE_TYPE = "S";
    public static final String RECV_LINE_TYPE = "R";


    public static final int USER_ID_LENGTH = 20;
    public static final int AGENT_ID_LENGTH = 6;
    public static final int USER_PWD_LENGTH = 50;

    public static final int REPORT_LENGTH = 1;
    public static final String ALL_REPORT = "A";               // 전체 통지
    public static final String REQUIRED_VALUE_REPORT = "M";    // 필수 값만 통지


    public static final int INFO_LENGTH = 21;
    public static final String INFO_VALUE = "V1.1";


    public static final int BIND_BODY_LENGTH = 100;


    /*
     * ******************************************
     * *                BIND_ACK                *
     * ******************************************
     */

    public static final int RESULT_LENGTH = 5;
    public static final String SUCCESS_RESULT_VALUE = "0";
    public static final String FAIL_RESULT_VALUE = "기타";
    public static final int TPS_SMS_LENGTH = 5;
    public static final int TPS_LMS_LENGTH = 5;
    public static final int TPS_MMS_LENGTH = 5;
    public static final int TPS_KKO_LENGTH = 5;
    public static final int TPS_KKF_LENGTH = 5;
    public static final int BIND_ACK_TEMP1_LENGTH = 5;
    public static final int BIND_ACK_TEMP2_LENGTH = 5;

    public static final int BIND_ACK_BODY_LENGTH = 40;




    /*
     * ******************************************
     * *                DELIVERY                *
     * ******************************************
     */
    public static final int LMS_UNCHANGE_BODY_LEN = 280;
    public static final int MMS_UNCHANGE_BODY_LEN = 281;




    public static final String DELIVERY_NO_ENCODE_TYPE = "0"; // 암호화하지 않음.
    public static final String DELIVERY_ENCODE_TYPE_1 = "1"; // 수신번호, 회신번호 암호화.
    public static final String DELIVERY_SUBJECT_ENCODE_TYPE = "2"; // 수신번호, 회신번호 및 제목 암호화.
    public static final String DELIVERY_CONTENT_ENCODE_TYPE = "9"; // 수신번호, 회신번호, 제목 및 메세지 내용 암호화


    public static final int DELIVERY_TYPE_LENGTH = 4;

    public static final String SMS_DELIVERY_TYPE = "SMS";
    public static final String LMS_DELIVERY_TYPE = "LMS";
    public static final String MMS_DELIVERY_TYPE = "MMS";

    public static final String KKO_DELIVERY_TYPE = "KKO";
    public static final String KKF_DELIVERY_TYPE = "KKF";


    public static final int MSG_ID_LENGTH = 20;

    public static final int GUBUN_LENGTH = 1;
    public static final String DOMESTIC_GUBUN = "0";


    public static final int CONTENTS_TYPE_LENGTH = 4;
    public static final String TXT_CONTENTS_TYPE = "TXT";


    public static final int DESTADDR_LENGTH = 50;
    public static final int CALLBACK_LENGTH = 40;
    public static final int RESELLER_LENGTH = 10;
    public static final int DELIVERY_SUBJECT_LENGTH = 150;
    public static final int AD_FLAG_LENGTH = 1;
    public static final int RESERVED_LENGTH = 28;
    public static final int KSUBJECT_LENGTH = 21;
    public static final int DATACNT_LENGTH = 1;

    public static final int DELIVERY_TEMP1_LENGTH = 1;
    public static final int DATATYPE_LENGTH = 1;
    public static final String TEXT_DATATYPE = "T";
    public static final String IMAGE_DATATYPE = "i";
    public static final String KKO_BTN_DATATYPE = "z";
    public static final String ETC_DATATYPE = "e";
    public static final int FILE_NAME_LENGTH = 20;
    public static final int FILE_KEY_LENGTH = 12;
    public static final int DATA_SIZE_LENGTH = 8;
    public static final int FILE_SIZE_LENGTH = 8;
    public static final int TEXT_LENGTH = 2000;

    public static final int DELIVERY_MEDIA_FILE_UNCHANGE_BODY_LEN = DELIVERY_TEMP1_LENGTH + DATATYPE_LENGTH + FILE_NAME_LENGTH + FILE_KEY_LENGTH + DATA_SIZE_LENGTH + FILE_SIZE_LENGTH;
    /*
     * ******************************************
     * *              DELIVERY_ACK              *
     * ******************************************
     */
    public static final int MSG_MSEC_LENGTH = 7;
    public static final int DELIVERY_ACK_RESERVED_LENGTH = 6;
    public static final int DELIVERY_ACK_BODY_LEN = 92;

    public static final String DELIVERY_ACK_RESULT_E_OK = "0";                  // 접수성공
    public static final String DELIVERY_ACK_RESULT_E_SYS_ERR = "1";             // 서버문제로 인한 접수 실패
    public static final String DELIVERY_ACK_RESULT_E_FORMAT_OR_CONTENT_ERR = "3";          // 메시지 포맷 에러 / 컨텐츠 사이즈 및 개수 초과
    public static final String DELIVERY_ACK_RESULT_E_INVALID_PHONE = "7";       // 잘못된 전화번호
    public static final String DELIVERY_ACK_RESULT_E_INSUFFICIENT = "12";        // 건수 부족
    public static final String DELIVERY_ACK_RESULT_E_TPS_ERROR = "15";           // 지정TPS 초과, DELIVER_ACK의 MSG_MSEC을 무시하고 10건 이상 보내는 경우 BIND가 끊어짐
    public static final String DELIVERY_ACK_RESULT_E_MMS_FILE_ERROR = "16";      // MMS 파일 전송오류
    public static final String DELIVERY_ACK_RESULT_E_DUP_KEY_OR_PHONE = "17";             // 중복된 키/수신번호 접수 차단
    public static final String DELIVERY_ACK_RESULT_E_NO_AUTH = "28";             // 사전 미등록 발신번호사용
    public static final String DELIVERY_ACK_RESULT_E_FORMAT_DESTINE = "29";      // 전화번호세칙 미준수 발신번호
    public static final String DELIVERY_ACK_RESULT_E_DESTINE_TRAN = "30";        // 발신번호변작 등록 발신번호
    public static final String DELIVERY_ACK_RESULT_E_DESTINE_BLOCK = "31";       // 번호도용문자서비스 가입 발신번호 사용




    /*
     * ******************************************
     * *                 REPORT                 *
     * ******************************************
     */

    public static final int REPORT_BODY_LEN = 54;

    public static final int RTN_TYPE_LENGTH = 4;
    public static final int DELIVERY_TIME_LENGTH = 15;
    public static final int NET_ID_LENGTH = 6;

    public static final String REPORT_RESULT_E_SENT = "6";          // 전송 성공
    public static final String REPORT_RESULT_E_INVALIDDST = "7";    // 결번, 비가입자, 서비스정지
    public static final String REPORT_RESULT_E_POWEROFF = "8";      // 단말실패(Power-off, Busy, 착신거절 등)
    public static final String REPORT_RESULT_E_HIDDEN = "9";        // 음영
    public static final String REPORT_RESULT_E_TERMFILL = "10";     // 단말기, 메시지 FULL
    public static final String REPORT_RESULT_E_ETC_11 = "11";       // 기타에러(이통사)
    public static final String REPORT_RESULT_E_OVERFLOW = "12";     // 계정 제한콜수 초과(잔여콜 부족)
    public static final String REPORT_RESULT_E_PORTED_OUT = "13";   // 번호이동
    public static final String REPORT_RESULT_E_ETC_14 = "14";       // 기타에러(무선망)
    public static final String REPORT_RESULT_E_TIMEOUT = "11";      // 망 결과응답시간 초과
    public static final String REPORT_RESULT_E_DUP_DEST = "17";     // 5초내에 동일한수신번호 4개이상거부
    public static final String REPORT_RESULT_E_UNKNOWN = "20";      // UNKNOWN (default)
    public static final String REPORT_RESULT_E_NO_DESTIN_21 = "21"; // 착신번호 에러(자리수)
    public static final String REPORT_RESULT_E_NO_DESTIN_22 = "22"; // 착신번호 에러(국번)
    public static final String REPORT_RESULT_E_DELETEADMIN = "25";  // 운영자에 의한 삭제
    public static final String REPORT_RESULT_E_NOSUPPORTDEVICE = "26"; // 미지원단말기
    public static final String REPORT_RESULT_E_NO_AUTH = "28";         // 사전 미등록 발신번호사용
    public static final String REPORT_RESULT_E_FORMAT_DESTINE = "29";  // 전화번호 세칙 미준수 발신번호사용
    public static final String REPORT_RESULT_E_DESTINE_TRAN = "30";    // 발신번호 변작으로 등록된 발신번호 사용
    public static final String REPORT_RESULT_E_DESTINE_BLOCK = "31";   // 번호도용문자차단 서비스에 가입된 발신번호사용
    public static final String REPORT_RESULT_E_REJECT_DESTINE = "32";  // 수신거부 등록 실패(080수신거부)
    public static final String REPORT_RESULT_E_SPAM = "40";            // 스팸필터링



    /*
     * ******************************************
     * *               REPORT_ACK               *
     * ******************************************
     */
    public static final String REPORT_ACK_RESULT = "0";
    public static final int REPORT_ACK_BODY_LEN = 32;
    public static final int REPORT_ACK_RESERVED_LENGTH = 7;



    /*
     * ******************************************
     * *                  LINK                  *
     * ******************************************
     */
    public static final int LINK_CODE_LENGTH = 1;
    public static final String LINK_TO_SEND = "S";
    public static final String LINK_TO_RECV = "R";


    /*
     * ******************************************
     * *               LINK_ACK                 *
     * ******************************************
     */

    public static final String LINK_ACK_RESULT_OK = "0";
    public static final int LINK_ACK_RESULT_LENGTH = 5;

}
