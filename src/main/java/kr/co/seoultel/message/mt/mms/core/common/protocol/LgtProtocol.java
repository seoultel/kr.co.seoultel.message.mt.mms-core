package kr.co.seoultel.message.mt.mms.core.common.protocol;

public class LgtProtocol {

    public static final String SUBMIT_REQ = "SubmitReq";
    public static final String SUBMIT_RES = "SubmitRsp";
    public static final String DELIVERY_REPORT_REQ = "DeliveryReportReq";
    public static final String DELIVERY_REPORT_RES = "DeliveryReportRsp";
    public static final String READ_REPLY_REQ = "ReadReplyReq";
    public static final String READ_REPLY_RES = "ReadReplyRsp";
    public static final String RS_ERROR_RES = "RSErrorRsp";
    public static final String VASP_ERROR_RES = "VaspErrorRsp";


    public static final String SUCCESS = "1000";
    public static final String PARTIAL_SUCCESS = "1100";
    public static final String CLIENT_ERROR = "2000";
    public static final String OPERATION_RESTRICTED = "2001";
    public static final String ADDRESS_ERROR = "2002";
    public static final String ADDRESS_NOT_FOUND = "2003";
    public static final String MULTI_MEDIA_CONTENT_REFUSED = "2004";
    public static final String MESSAGE_ID_NOT_FOUND = "2005";
    public static final String LINKED_ID_NOT_FOUND = "2006";
    public static final String MESSAGE_FORMAT_CORRUPT = "2007";
    public static final String SERVER_ERROR = "3000";
    public static final String NOT_POSSIBLE = "3001";
    public static final String MESSAGE_REJECTED = "3002";
    public static final String MULTIPLE_ADDRESSED_NOT_SUPPORTED = "3003";
    public static final String GENERAL_SERVICE_ERROR = "4000";
    public static final String IMPROPER_IDENTIFICATION = "4001";
    public static final String UNSUPPORTED_VERSION = "4002";
    public static final String UNSUPPORTED_OPERATION = "4003";
    public static final String VALIDATION_ERROR = "4004";
    public static final String SERVICE_ERROR = "4005";
    public static final String SERVICE_UNAVAILBALE = "4006";
    public static final String SERVICE_DENIED = "4007";
    public static final String SYSTEM_ERROR = "4030";
    public static final String KISACODE_ERROR = "4032";
    public static final String SUBS_REJECT = "6014";
    public static final String INVALID_AUTH_PASSWORD = "6024";
    public static final String EXPIRED_PASSWORD = "6025";
    public static final String MMS_DISABLE_SUBS = "6072";
    public static final String TRAFFIC_IS_OVER = "7103";
}
