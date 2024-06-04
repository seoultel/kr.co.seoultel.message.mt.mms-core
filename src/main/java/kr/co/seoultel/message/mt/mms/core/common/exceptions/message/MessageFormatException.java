package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.NonNull;


// TODO : 이거 굳이 처리할 필요없을 수도 있음.
public class MessageFormatException extends FormatException {

    public MessageFormatException(@NonNull MessageDelivery messageDelivery) {
        super(String.format("[SUBMIT-ERR] STASH MESSAGE[%s] FORMAT ERR", messageDelivery.getUmsMsgId()),
                Constants.MESSAGE_IMAGE_IS_EMPTY_MNO_RESULT);
        this.messageDelivery = messageDelivery;
    }
}
