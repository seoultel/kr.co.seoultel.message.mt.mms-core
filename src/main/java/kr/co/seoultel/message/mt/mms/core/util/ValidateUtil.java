package kr.co.seoultel.message.mt.mms.core.util;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.core.dto.mms.Submit;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.*;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.fallback.FallbackMessageFormatException;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.fallback.FallbackOriginCodeFormatException;
import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;
import lombok.NonNull;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ValidateUtil {

    private static final DateTimeFormatter dttmDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

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

    /**
     * Validate message delivery.
     *
     * @param messageDelivery the message delivery
     * @throws FormatException the format exception
     */
    public static void validateMessageDelivery(@NonNull MessageDelivery messageDelivery) throws FormatException {
        String umsMsgId = messageDelivery.getUmsMsgId();
        if (!validateUmsMsgIdFormat(umsMsgId)){
            throw new UmsMsgIdFormatException(messageDelivery, DeliveryType.SUBMIT_ACK);
        }

        String callback = messageDelivery.getCallback();
        String receiver = messageDelivery.getReceiver();
        String sender = messageDelivery.getSender();

        if (!isConsistOnlyNumericValue(callback) && !validatePhoneNumber(receiver) && !isConsistOnlyNumericValue(sender)) {
            throw new PhoneNumberFormatException(messageDelivery, DeliveryType.SUBMIT_ACK);
        }

        String originCode = (String) messageDelivery.getContent().get(Submit.ORIGIN_CODE);
        if (!validateOriginCode(originCode)) {
            throw new OriginCodeFormatException(messageDelivery, originCode, DeliveryType.SUBMIT_ACK);
        }

        Map<String, Object> content = messageDelivery.getContent();
        List<String> imageIds = (List<String>) content.get(Submit.MEDIA_FILES);
        String message = (String) content.get(Submit.MESSAGE);

        if (message == null && imageIds.isEmpty()) {
            throw new MessageFormatException(messageDelivery, DeliveryType.SUBMIT_ACK);
        }
    }


    /**
     * Validate fallback message delivery.
     *
     * @param messageDelivery the message delivery
     * @throws FormatException the format exception
     */
    public static void validateFallbackMessageDelivery(@NonNull MessageDelivery messageDelivery) throws FormatException {
        String umsMsgId = messageDelivery.getUmsMsgId();
        if (!validateUmsMsgIdFormat(umsMsgId)){
            throw new UmsMsgIdFormatException(messageDelivery, DeliveryType.FALLBACK_SUBMIT_ACK);
        }

        String callback = messageDelivery.getCallback();
        String receiver = messageDelivery.getReceiver();
        String sender = messageDelivery.getSender();

        if (!isConsistOnlyNumericValue(callback) && !validatePhoneNumber(receiver) && !isConsistOnlyNumericValue(sender)) {
            throw new PhoneNumberFormatException(messageDelivery, DeliveryType.FALLBACK_SUBMIT_ACK);
        }

        String originCode = FallbackUtil.getFallbackOriginCode(messageDelivery);
        if (!validateOriginCode(originCode)) {
            throw new FallbackOriginCodeFormatException(messageDelivery, originCode, DeliveryType.FALLBACK_SUBMIT_ACK);
        }

        List<String> imageIds = FallbackUtil.getFallbackFileIds(messageDelivery);
        String message = FallbackUtil.getFallbackMessage(messageDelivery);
        if (message == null && imageIds.isEmpty()) {
            throw new FallbackMessageFormatException(messageDelivery, DeliveryType.FALLBACK_SUBMIT_ACK);
        }
    }
    public static boolean isDttmDateTimeFormat(String dateTimeString) {
        try {
            // parse dateTimeString to dttmFormat;
            dttmDateTimeFormatter.parse(dateTimeString);
            // if parsing is success, return true;
            return true;
        } catch (DateTimeParseException e) {
            // if parsing is failed, return false;
            return false;
        }
    }

    /**
     * Assign default subject if null or empty.
     *
     * @param messageDelivery the message delivery
     */
    public static void assignDefaultSubjectIfNullOrEmpty(MessageDelivery messageDelivery) {
        Map<String, Object> contentMap = messageDelivery.getContent();
        contentMap.putIfAbsent(Submit.SUBJECT, "제목 없음");
    }

    /**
     * Assign default fallback subject if null or empty.
     *
     * @param messageDelivery the message delivery
     */
    public static void assignDefaultFallbackSubjectIfNullOrEmpty(MessageDelivery messageDelivery) {
        String subject = FallbackUtil.getFallbackSubject(messageDelivery);
        subject = Objects.requireNonNullElse(subject, "제목 없음");

        FallbackUtil.setFallbackSubject(messageDelivery, subject);
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
