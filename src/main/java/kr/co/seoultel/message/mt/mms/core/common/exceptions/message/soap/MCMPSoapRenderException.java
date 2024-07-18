package kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap;

import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.FormatException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class MCMPSoapRenderException extends Exception {

    protected final Exception origin;
    protected final String mnoMessage = Constants.SOAP_ERROR;
    protected final String mnoResult = Constants.FAILED_TO_CREATE_SOAP_MNO_RESULT;

    public MCMPSoapRenderException(String message, Exception origin) {
        super(message);
        this.origin = origin;
    }

}
