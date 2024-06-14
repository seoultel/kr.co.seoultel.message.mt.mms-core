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
}
