package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.NonNull;

import java.util.Arrays;

public class BlockedSpecificWordInMessage extends FormatException {

    public BlockedSpecificWordInMessage(@NonNull MessageDelivery messageDelivery, String word) {
        super(String.format("[BlockedSpecificWord] Block Message, contains spam keyword[%s]", word),
                Constants.CONTAINS_SPAM_KEYWORD_MNO_RESULT);
        this.messageDelivery = messageDelivery;
    }

    public BlockedSpecificWordInMessage(@NonNull MessageDelivery messageDelivery, String[] words) {
        super(String.format("[BlockedSpecificWord] Block Message, contains spam keyword[%s]", Arrays.toString(words)),
                Constants.CONTAINS_SPAM_KEYWORD_MNO_RESULT);
        this.messageDelivery = messageDelivery;
    }
}
