package kr.co.seoultel.message.mt.mms.core.messages.hist;

public class HistProtocol {

    /* HEADER HEAD-TYPE AND MSG-LENG SIZE */
    public static final int HIST_HEAD_TYPE_LENGTH = 4;  //
    public static final int HIST_MSG_LENG = 10;

    public static final int HIST_HEADER_TOTAL_LENGTH = 14;  //

    /* HIST HEAD-TYPE */
    public static final String HIST_CERT_HEAD_TYPE = "0";
    public static final String HIST_CERT_ACK_HEAD_TYPE = "1";
    public static final String HIST_BIND_HEAD_TYPE = "11";
    public static final String HIST_BIND_ACK_HEAD_TYPE = "12";
    public static final String HIST_DELIVERY_HEAD_TYPE = "21";
    public static final String HIST_DELIVERY_ACK_HEAD_TYPE = "22";
    public static final String HIST_REPORT_HEAD_TYPE = "31";
    public static final String HIST_REPORT_ACK_HEAD_TYPE = "32";
    public static final String HIST_LINK_HEAD_TYPE = "41";
    public static final String HIST_LINK_ACK_HEAD_TYPE = "42";
    public static final String HIST_EVENT_HEAD_TYPE = "51";


    /* PACKET BODY SIZE */
    public static final int HIST_CERT_MSG_LENG = 149;
    public static final int HIST_CERT_ACK_MSG_LENG = 125;
    public static final int HIST_BIND_MSG_LENG = 220;
    public static final int HIST_BIND_ACK_MSG_LENG = 59;
    public static final int HIST_DELIVERY_MSG_LENG = 221;
    public static final int HIST_DELIVERY_ACK_MSG_LENG = 44;
    public static final int HIST_REPORT_MSG_LENG = 94;
    public static final int HIST_REPORT_ACK_MSG_LENG = 40;
    public static final int HIST_LINK_MSG_LENG = 0;
    public static final int HIST_LINK_ACK_MSG_LENG = 0;
    public static final int HIST_EVENT_MSG_LENG = 90;

    /* CERT */
    public static final int ID_LENGTH = 20;
    public static final int PWD_LENGTH = 110;
    public static final int VERSION_LENGTH = 15;
    public static final int KEYPOS_LENGTH = 4;


    /* CERT-ACK */
    public static final int RESULT_LENGTH = 4;
    public static final int CERT_KEY_LENGTH = 65;
    public static final int SERVER_IP_LENGTH = 40;
    public static final int DELIVERY_PORT_LENGTH = 8;
    public static final int REPORT_PORT_LENGTH = 8;

    /* BIND */
    public static final int TYPE_LENGTH = 2;
    public static final String DEFAULT_TYPE = "p";
    public static final String ENC_TYPE = "P";

    public static final int KIND_LENGTH = 4;
    public static final String DELIVERY_KIND = "DLV";
    public static final String REPORT_KIND = "REP";
    public static final String HIST_VERSION = "TV_2.0.0";


    /* BIND-ACK */
    public static final int SMS_TPS_LENGTH = 5;
    public static final int MMS_TPS_LENGTH = 5;
    public static final int KKO_TPS_LENGTH = 5;
    public static final int MESSAGE_LENGTH = 40;


    /* DELIVERY */
    public static final int MSG_TYPE_LENGTH = 2;
    public static final int CALLBACK_LENGTH = 20;
    public static final int ENCODING_LENGTH = 2;
    public static final int TEXT_LENGTH = 141;
    public static final int SENDER_CODE_LENGTH = 10; //
    public static final int MEDIA_CNT_LENGTH = 2; //
    public static final int EXT_SIZE_LENGTH = 4; //

    public static final String SMS_MSG_TYPE = "S";
    public static final String LMS_MSG_TYPE = "L";
    public static final String MMS_MSG_TYPE = "M";

    public static final int M_TYPE_LENGTH = 3; //
    public static final String M_TYPE_11 = "11";
    public static final String M_TYPE_12 = "12";
    public static final String M_TYPE_13 = "13";
    public static final String M_TYPE_21 = "21";
    public static final String M_TYPE_22 = "22";
    public static final String M_TYPE_23 = "23";
    public static final String M_TYPE_24 = "24";

    public static final String EUC_KR_ENCODING = "0";
    public static final String UTF_8_ENCODING = "1";
    public static final String BINARY_ENCODING = "2";

    public static final int M_FILE_LEN_LENGTH = 10; //

    /* DELIVERY-ACK */
    public static final int DA_ADDR_LENGTH = 20;
    public static final int SERIAL_LENGTH = 20;


    /* REPORT */
    public static final int GW_SERIAL_LENGTH = 16;
    public static final int SEND_TIME_LENGTH = 15;
    public static final int TELCO_INFO_LENGTH = 4;

    /* EVENT */
    public static final int EVENT_TYPE_LENGTH = 16;
    public static final int EVENT_DATA_LENGTH = 15;






    // RESULT CODE

    // 공통
    public static final String E_OK = "0";                                // 성공

    // BIND_ACK
    public static final String E_SYS_FAIL = "100";                        // 서버실패 (프로세스 또는 시스템 에러)
    public static final String E_SYS_BUSY = "101";                        // 일시적 용량 초과
    public static final String E_AUTH_FAIL = "102";                       // 인증실패 - 패스워드 틀림
    public static final String E_CP_SUSPEND = "103";                      // 중지된 고객, 등록 IP가 다름
    public static final String E_CP_UNREGIST = "104";                     // 미등록 고객
    public static final String E_ALREADY_CON = "105";                     // 이미 연결됨
    public static final String E_UNSUPPORT_VERSION = "106";               // 미지원 버젼
    public static final String E_SMS_DENY = "107";                        // sms 발송 권한 없음
    public static final String E_MMS_DENY = "108";                        // mms 발송 권한 없음
    public static final String E_ISMS_DENY = "109";                       // isms 발송 권한 없음
    public static final String E_NET_FAIL = "110";                        // 네트웍 에러 발생
    public static final String E_NOT_BIND = "111";                        // 바인드 되지 않음
    public static final String E_ENCRYPT_ERR = "112";                     // 암호화 에러
    public static final String E_DECRYPT_ERR = "113";                     // 복호화 에러
    public static final String E_DELIVER_DENY = "114";                    // 발송권한 없음
    public static final String E_KKO_DENY = "115";                        // 카카오 발송권한 없음

    // DELIVERY_ACK / REPORT_ACK
    public static final String E_INVALID_HEAD = "200";                    // 기대하지 않은 Head 또는 헤더필드의 부적절
    public static final String E_INVALID_BODY = "201";                    // Body 필드의 부적절
    public static final String E_INVALID_FORMAT = "202";                  // 규격 오류
    public static final String E_INVALID_ADDRESS = "203";                 // 발/착신 번호 에러
    public static final String E_CONTENT_SIZE_ERR = "204";                // 컨텐츠 크기 초과
    public static final String E_CONTENT_SIZEOVER = "205";                // 컨텐츠 크기 초과
    public static final String E_CONTENT_CNT_ERR = "206";                 // 첨부파일 개수 오류
    public static final String E_UNKNOWN_CONTENT = "207";                 // 지원하지 않는 컨텐츠 존재
    public static final String E_CONTENT_ETC = "208";                     // 컨텐츠 관련 기타
    public static final String E_SERIALNUM_ERR = "209";                   // 시리얼 넘버 오류(크기 초과등);
    public static final String E_INVALID_MSGTYPE = "210";                 // 잘못된 메시지 타입
    public static final String E_INVALID_SENDERCODE = "211";              // 최초 발신자 식별코드 오류
    public static final String E_TPS_OVER = "212";                        // 초당 처리 건수 초과
    public static final String E_ENCODE_ERR = "213";                      // Encoding error
    public static final String E_DAILYCNT_OVER = "300";                   // 일발송량 초과
    public static final String E_MONTHCNT_OVER = "301";                   // 월발송량 초과
    public static final String E_BLOCKTIME_FAIL = "302";                  // 발송제한시간
    public static final String E_NOCDR = "303";                           // 잔액 부족
    public static final String E_DUPLICATION_FAIL = "304";                // 중복발송 - sn+da 중복
    public static final String E_REQUESTTIME_ERR = "305";                 // 요청시간 오류 - pc client 에서 사용
    public static final String E_CALLBACK_RULE_ERR = "306";               // 전화번호 세칙 미준수 발신번호 사용
    public static final String E_CALLBACK_UNREG_ERR = "307";              // 사전 미등록 발신번호 사용
    public static final String E_CALLBACK_TRANS_ERR = "308";              // 발신번호 변작으로 등록된 발신번호 사용
    public static final String E_CALLBACK_STEAL_ERR = "309";              // 번호도용문자차단서비스에 가입된 발신번호 사용
    public static final String E_ROUTE_FAIL = "400";                      // 라우팅 정보 없음
    public static final String E_SPAM_DA = "401";                         // 착신번호 스팸
    public static final String E_SPAM_MSG = "402";                        // 스팸 메시지
    public static final String E_MSG_SIZEOVER = "403";                    // 메시지 크기 초과
    public static final String E_REAL_SUBMIT_FIAL = "404";                // 실시간 전송 실패
    public static final String E_REPORT_EXPIRED = "405";                  // gw자체 expired : 리포트 미수신
    public static final String E_SUBMIT_EXPIRED = "406";                  // gw자체 expired : 미전송
    public static final String E_ATTACH_ACCESS_ERR = "407";               // 첨부파일 관련 에러
    public static final String E_ETC = "408";                             // 기타
    public static final String E_SPAM_OA = "409";                         // 발신번호 스팸
    public static final String E_TELCO_SYSFAIL = "500";                   // 발송업체 system fail
    public static final String E_TELCO_SPAM_DA = "501";                   // 이통사 착신번호 스팸
    public static final String E_TELCO_SPAM_MSG = "502";                  // 스팸 메시지
    public static final String E_TELCO_MSG_DENY = "503";                  // 수신거부
    public static final String E_TELCO_EXPIRED = "504";                   // 이통사 expired
    public static final String E_NO_DESTADDR = "505";                     // 착신가입자 없음
    public static final String E_PHONE_POWEROFF = "506";                  // 전원꺼짐
    public static final String E_PHONE_SHADE = "507";                     // 음영지역
    public static final String E_PHONE_MSGFULL = "508";                   // 단말 수신용량 초과
    public static final String E_PHONE_NOMMS = "509";                     // MMS를 미 지원 단말
    public static final String E_PHONE_NOREAD = "510";                    // 미 지원 단말
    public static final String E_PHONE_BUSY = "511";                      // 무응답 및 통화중
    public static final String E_PORTED = "512";                          // 번호이동
    public static final String E_NPDB_ERR = "513";                        // NPDB에러
    public static final String E_TELCO_CONTENT_ERR = "514";               // 이통사의 컨텐츠에러
    public static final String E_TELCO_CALLBACK_RULE_ERR = "515";         // 이통사 전화번호 세칙 미준수 발신번호 사용
    public static final String E_TELCO_CALLBACK_UNREG_ERR = "516";        // 이통사 사전 미등록 발신번호 사용
    public static final String E_TELCO_CALLBACK_TRANS_ERR = "517";        // 이통사 발신번호 변작으로 등록된 발신번호 사용
    public static final String E_TELCO_CALLBACK_STEAL_ERR = "518";        // 이통사 번호도용문자차단서비스에 가입된 발신번호 사용
    public static final String E_TELCO_ETC = "519";                       // 이통사 기타
    public static final String E_PHONE_HALT = "520";                      // 가입자 일시정지
    public static final String E_TELCO_SPAM_OA = "521";                   // 이통사 발신번호 스팸

    // KKO
    public static final String E_KKO_PROFILE_ERR = "600";                 // 카카오 프로필 관련 에러
    public static final String E_KKO_TEMPLATE_ERR = "601";                // 카카오 템플릿 관련 에러
    public static final String E_KAKAO_INFO_ERR = "602";                  // 카카오 버튼,강조등 부가정보 필드 에러
    public static final String E_KAKAO_INFO_ERR2 = "603";                 // 전환/대체 권한 오류
    public static final String E_KAKAO_ERR = "604";                       // 그외 카카오 기타 에러(* 상세 에러는 별도로 전달되는 "알림톡 _친구톡 에러코드"를 참조하세요.)
}
