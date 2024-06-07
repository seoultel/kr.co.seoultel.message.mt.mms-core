package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;
import lombok.Getter;
import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.ToString;

@Getter
public class UmsMsgIdFormatException extends FormatException {
    public UmsMsgIdFormatException(MessageDelivery messageDelivery, DeliveryType deliveryType) {
        super(String.format("[FORMAT EXCEPTION IT IS NOT UMS-MSG-ID FORMAT, UMS-MSG-ID : %s", messageDelivery.getUmsMsgId()),
                messageDelivery, Constants.UMS_MSG_ID_VALIDATION_FAILED_MNO_RESULT, deliveryType);
    }

    @Override
    public String toString() {
        return "UmsMsgIdFormatException{" +
                ", mnoResult='" + mnoResult + '\'' +
                ", deliveryType=" + deliveryType +
                ", reportMessage='" + reportMessage + '\'' +
                '}';
    }
}
