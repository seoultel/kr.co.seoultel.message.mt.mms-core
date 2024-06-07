package kr.co.seoultel.message.mt.mms.core.common.exceptions.message.fallback;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.FormatException;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.OriginCodeFormatException;
import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;
import lombok.NonNull;

public class FallbackOriginCodeFormatException extends OriginCodeFormatException {
    public FallbackOriginCodeFormatException(@NonNull MessageDelivery messageDelivery, String originCode, DeliveryType deliveryType) {
        super(messageDelivery, originCode, deliveryType);
    }
}

