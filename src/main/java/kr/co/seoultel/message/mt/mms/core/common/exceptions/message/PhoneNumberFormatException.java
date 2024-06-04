package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.NonNull;

public class PhoneNumberFormatException extends FormatException {
    public PhoneNumberFormatException(@NonNull MessageDelivery messageDelivery) {
        super(String.format("[PHONE-NUBMER FORMAT EXCEPTION] (CALLBACK[%s] | RECEIVER[%s] | SENDER[%s])", messageDelivery.getCallback(), messageDelivery.getReceiver(), messageDelivery.getSender())
                , Constants.PHONE_NUMBER_VALIDATION_FAILED_MNO_RESULT);
        this.messageDelivery = messageDelivery;
    }
}
