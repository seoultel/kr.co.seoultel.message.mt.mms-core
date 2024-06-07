package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;
import lombok.NonNull;

public class PhoneNumberFormatException extends FormatException {
    public PhoneNumberFormatException(@NonNull MessageDelivery messageDelivery, DeliveryType deliveryType) {
        super(String.format("[PHONE-NUBMER FORMAT EXCEPTION] (CALLBACK[%s] | RECEIVER[%s] | SENDER[%s])", messageDelivery.getCallback(), messageDelivery.getReceiver(), messageDelivery.getSender()),
                messageDelivery, Constants.PHONE_NUMBER_VALIDATION_FAILED_MNO_RESULT, deliveryType);
    }

    @Override
    public String toString() {
        return "PhoneNumberFormatException{" +
                ", mnoResult='" + mnoResult + '\'' +
                ", deliveryType=" + deliveryType +
                ", reportMessage='" + reportMessage + '\'' +
                '}';
    }
}
