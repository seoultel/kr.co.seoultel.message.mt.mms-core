package kr.co.seoultel.message.mt.mms.core.common.constant;

public class Constants {


    public static final String SKT = "SKT";
    public static final String KTF = "KTF";
    public static final String LGT = "LGT";


    public static final String UPLUS = "UPLUS";
    public static final String SMTNT = "SMTNT";
    public static final String LGHV = "LGHV";
    public static final String HIST = "HIST";
    public static final String UNION = "UNION";

    public final static String SYSTEM = "SYSTEM";

    public static final String MMS_PREFIX = "MMS";
    public static final String REDIS_IMAGE_PREFIX = "FileExpireData";


    public final static String EUC_KR = "euc-kr";



    /* MCMP 플랫폼에서 지원하는 이미지 확장자명 */
    public static final String JPG_EXTENSION = ".jpg";
    public static final String JPEG_EXTENSION = ".jpeg";

    /* MCMP 플랫폼에서 지원하는 이미지 최대 크기 */
    public static final int STANDARD_IMAGE_MAX_SIZE = 1024 * 100; // 100KB

    /* 시간 단위 */
    public static final long SECOND = 1000L;
    public static final long MINUTE = SECOND * 60L;
    public static final long HOUR = MINUTE* 60L;
    public static final long DAY = HOUR * 24L;
    public static final long WEEK = DAY * 7L;

    public static final long MMS_EXPIRE_TIME = DAY * 3L;


    /*
     * =============================================================================
     *
     *                CONSTANTS USE REPORT WHEN MESSAGE VALIDATION FAIL
     *
     * =============================================================================
     */
    public final static String UMS_MSG_ID_VALIDATION_FAILED_MNO_RESULT = "62021";
    public final static String PHONE_NUMBER_VALIDATION_FAILED_MNO_RESULT = "62022";
    public final static String ORIGIN_CODE_VALIDATION_FAILED_MNO_RESULT = "62023";

    public final static String MESSAGE_IMAGE_IS_EMPTY_MNO_RESULT = "62024";

    public final static String FAILED_TO_CREATE_SOAP_MNO_RESULT = "62030";


    /*
     * =============================================================================
     *
     *                   CONSTANTS USE REPORT WHEN FILE-SERVER EXCEPTION
     *
     * =============================================================================
     */
    public final static String IMAGE_CNT_OVER = "IMAGE_CNT_OVER";
    public final static String IMAGE_CNT_OVER_MNO_RESULT = "62041";
    public final static String IMAGE_IS_EXPIRED = "IMAGE_IS_EXPIRED";
    public final static String IMAGE_IS_EXPIRED_MNO_RESULT = "62042";

    public final static String IMAGE_NOT_FOUND = "NOT_ADDED_CLIENTS_IMAGE";
    public final static String IMAGE_NOT_FOUND_MNO_RESULT = "62043";

    public final static String IMAGE_SIZE_OVER = "IMG_SIZE_OVER";
    public final static String IMAGE_SIZE_OVER_MNO_RESULT = "62044";

    public final static String UNKNOWN_FILE_SERVER_ERROR = "MMS_SND_UNKNOWN_FILE_ERR";
    public final static String UNKNOWN_FILE_SERVER_ERROR_MNO_RESULT = "62049";




    public final static String FORMAT_ERROR = "FORMAT_ERROR";
    public final static String UNKNOWN_ERROR = "UNKNOWN_ERROR";
}
