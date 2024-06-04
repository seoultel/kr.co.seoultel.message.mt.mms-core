package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.Getter;


@Getter
public abstract class FormatException extends Exception {


    protected MessageDelivery messageDelivery;

    protected String reportMessage = Constants.FORMAT_ERROR;
    protected String mnoResult;

    public FormatException(String message) {
        super(message);
    }

    public FormatException(String message, String mnoResult) {
        super(message);
        this.mnoResult = mnoResult;
    }
}
