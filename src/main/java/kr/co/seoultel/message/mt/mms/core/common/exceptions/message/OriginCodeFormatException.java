package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.NonNull;

public class OriginCodeFormatException extends FormatException {
    public OriginCodeFormatException(@NonNull MessageDelivery messageDelivery, String originCode) {
        super(String.format("[ORIGIN-CODE EXCEPTION] IT IS NOT ORIGIN-CODE FORMAT, ORIGIN-CODE : [%s]", originCode),
                Constants.ORIGIN_CODE_VALIDATION_FAILED_MNO_RESULT);
        this.messageDelivery = messageDelivery;
    }
}

