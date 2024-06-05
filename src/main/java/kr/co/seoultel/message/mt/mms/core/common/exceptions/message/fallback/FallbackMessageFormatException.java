package kr.co.seoultel.message.mt.mms.core.common.exceptions.message.fallback;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.FormatException;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.MessageFormatException;
import lombok.NonNull;

public class FallbackMessageFormatException extends MessageFormatException {
    public FallbackMessageFormatException(@NonNull MessageDelivery messageDelivery, int deliveryType) {
        super(messageDelivery, deliveryType);
    }
}
