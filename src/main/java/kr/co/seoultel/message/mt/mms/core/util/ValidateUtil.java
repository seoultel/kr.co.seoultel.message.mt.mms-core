package kr.co.seoultel.message.mt.mms.core.util;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.core.dto.mms.Submit;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.*;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

public class ValidateUtil {


    /*
     * ============================================================================
     *                          COMMON VALUE VALIDATE METHOD
     * ============================================================================
     */
    public static boolean isNullOrBlankStr(String str) {
        return str == null || str.isBlank();
    }

    public static boolean validateStrLength(@NonNull String str, int length) {
        return str.length() == length;
    }

    public static boolean isConsistOnlyNumericValue(@NonNull String str) {
        return str.matches("\\d+");
    }
    public static boolean isContainsWhitespace(String str) {
        return str.contains(" ");
    }


    /*
     * ============================================================================
     *                       MESSAGE-DELIVERY VALIDATE METHOD
     * ============================================================================
     */
    public static void validateMessageDelivery(@NonNull MessageDelivery messageDelivery) throws FormatException {
        String umsMsgId = messageDelivery.getUmsMsgId();
        if (!validateUmsMsgIdFormat(umsMsgId)){
            throw new UmsMsgIdFormatException(messageDelivery);
        }

        String callback = messageDelivery.getCallback();
        String receiver = messageDelivery.getReceiver();
        String sender = messageDelivery.getSender();

        if (!isConsistOnlyNumericValue(callback) && !validatePhoneNumber(receiver) && !isConsistOnlyNumericValue(sender)) {
            throw new PhoneNumberFormatException(messageDelivery);
        }

        String originCode = (String) messageDelivery.getContent().get(Submit.ORIGIN_CODE);
        if (!validateOriginCode(originCode)) {
            throw new OriginCodeFormatException(messageDelivery, originCode);
        }

        Map<String, Object> content = messageDelivery.getContent();
        List<String> imageIds = (List<String>) content.get(Submit.MEDIA_FILES);
        String message = (String) content.get(Submit.MESSAGE);

        if (message == null && imageIds.isEmpty()) {
            throw new MessageFormatException(messageDelivery);
        }
    }

    public static void assignDefaultSubjectIfNullOrEmpty(MessageDelivery messageDelivery) {
        Map<String, Object> contentMap = messageDelivery.getContent();
        contentMap.putIfAbsent(Submit.SUBJECT, "제목 없음");
    }

    /*
     * - Validate -> String phoneNumber
     *   1. phoneNumber is null ?
     *   2. phoneNumber is "" ?
     *   3. phoneNumber.length() is 11 ?
     *   4. Does PhoneNumber consist of numeric values?
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (isNullOrBlankStr(phoneNumber)) return false;

        // 11자리 문자열이 숫자로만 이루어져 있는지 확인
        return isConsistOnlyNumericValue(phoneNumber);
    }

    /*
     * "String value" is null and contains blank ? and value.length is "int length"
     */
    public static boolean validateUmsMsgIdFormat(String umsMsgId) {
        return (!isNullOrBlankStr(umsMsgId)
                && isConsistOnlyNumericValue(umsMsgId)
                && umsMsgId.length() <= 20
                && !isContainsWhitespace(umsMsgId));
    }

    public static boolean validateOriginCode(String originCode) {
        return !isNullOrBlankStr(originCode) && isConsistOnlyNumericValue(originCode) && validateStrLength(originCode, 9);
    }
}
