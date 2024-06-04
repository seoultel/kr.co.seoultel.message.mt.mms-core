package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.Getter;

@Getter
public class UmsMsgIdFormatException extends FormatException {
    public UmsMsgIdFormatException(MessageDelivery messageDelivery) {
        super(String.format("[FORMAT EXCEPTION IT IS NOT UMS-MSG-ID FORMAT, UMS-MSG-ID : %s", messageDelivery.getUmsMsgId()),
                Constants.UMS_MSG_ID_VALIDATION_FAILED_MNO_RESULT);
        this.messageDelivery = messageDelivery;
    }
}
