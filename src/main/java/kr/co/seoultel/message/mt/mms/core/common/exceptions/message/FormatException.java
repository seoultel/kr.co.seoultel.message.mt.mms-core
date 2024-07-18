package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;
import lombok.Getter;


@Getter
public abstract class FormatException extends Exception {


    protected MessageDelivery messageDelivery;

    protected String mnoResult;
    protected DeliveryType deliveryType;

    protected String reportMessage = Constants.FORMAT_ERROR;

    public FormatException(String message) {
        super(message);
    }


    public FormatException(String message, MessageDelivery messageDelivery, DeliveryType deliveryType) {
        super(message);

        this.messageDelivery = messageDelivery;
        this.deliveryType = deliveryType;
    }

    public FormatException(String message, MessageDelivery messageDelivery, String mnoResult, DeliveryType deliveryType) {
        super(message);

        this.messageDelivery = messageDelivery;
        this.mnoResult = mnoResult;
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "FormatException{" +
                ", mnoResult='" + mnoResult + '\'' +
                ", deliveryType=" + deliveryType +
                ", reportMessage='" + reportMessage + '\'' +
                '}';
    }
}
