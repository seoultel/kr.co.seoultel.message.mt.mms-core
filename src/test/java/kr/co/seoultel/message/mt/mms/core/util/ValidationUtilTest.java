package kr.co.seoultel.message.mt.mms.core.util;


import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.core.dto.mms.Submit;
import kr.co.seoultel.message.mt.mms.core.util.ValidateUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * {@link ValidateUtil} 클래스의 테스트를 위한 유닛 테스트 클래스입니다.
 */
public class ValidationUtilTest {

    /**
     * {@link ValidateUtil#isNullOrBlankStr(String)} 메서드를 테스트합니다.
     * 문자열이 null 또는 공백인지를 올바르게 판별하는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-1 : ValidationUtil.testIsNullOrBlankStr() 메서드 테스트")
    public void testIsNullOrBlankStr() {
        assertTrue(ValidateUtil.isNullOrBlankStr(null));
        assertTrue(ValidateUtil.isNullOrBlankStr(""));
        assertTrue(ValidateUtil.isNullOrBlankStr(" "));
        assertFalse(ValidateUtil.isNullOrBlankStr("test"));
    }

    /**
     * {@link ValidateUtil#validateStrLength(String, int)} 메서드를 테스트합니다.
     * 문자열의 길이를 올바르게 검증하는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-2 : ValidationUtil.validateStrLength() 메서드 테스트")
    public void testValidateStrLength() {
        assertTrue(ValidateUtil.validateStrLength("123456", 6));
        assertFalse(ValidateUtil.validateStrLength("12345", 6));
    }

    /**
     * {@link ValidateUtil#isConsistOnlyNumericValue(String)} 메서드를 테스트합니다.
     * 문자열이 숫자로만 구성되어 있는지를 올바르게 판별하는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-3 : ValidationUtil.isConsistOnlyNumericValue() 메서드 테스트")
    public void testIsConsistOnlyNumericValue() {
        assertTrue(ValidateUtil.isConsistOnlyNumericValue("123456"));
        assertFalse(ValidateUtil.isConsistOnlyNumericValue("123abc"));
    }

    /**
     * {@link ValidateUtil#isContainsWhitespace(String)} 메서드를 테스트합니다.
     * 문자열에 공백이 포함되어 있는지를 올바르게 판별하는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-4 : ValidationUtil.isContainsWhitespace() 메서드 테스트")
    public void testIsContainsWhitespace() {
        assertTrue(ValidateUtil.isContainsWhitespace("Hello World"));
        assertFalse(ValidateUtil.isContainsWhitespace("HelloWorld"));
    }

    /**
     * {@link ValidateUtil#isDttmDateTimeFormat(String)} 메서드를 테스트합니다.
     * 문자열이 날짜 및 시간 형식인지를 올바르게 판별하는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-5 : ValidationUtil.isDttmDateTimeFormat 메서드 테스트")
    public void testIsDttmDateTimeFormat() {
        assertTrue(ValidateUtil.isDttmDateTimeFormat("20240101120000")); // "yyyyMMddHHmmss" 형식
        assertFalse(ValidateUtil.isDttmDateTimeFormat("2024-01-01 12:00:00")); // 다른 형식
    }

    /**
     * {@link ValidateUtil#validatePhoneNumber(String)} 메서드를 테스트합니다.
     * 전화번호가 유효한지를 올바르게 판별하는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-6 : ValidationUtil.validatePhoneNumber 메서드 테스트")
    public void testValidatePhoneNumber() {
        assertTrue(ValidateUtil.validatePhoneNumber("01012345678")); // 올바른 전화번호
        assertFalse(ValidateUtil.validatePhoneNumber(null)); // null 값
        assertFalse(ValidateUtil.validatePhoneNumber("")); // 빈 문자열
        assertFalse(ValidateUtil.validatePhoneNumber("123abc456")); // 숫자가 아닌 문자 포함
    }

    /**
     * {@link ValidateUtil#validateUmsMsgIdFormat(String)} 메서드를 테스트합니다.
     * UMS 메시지 ID 형식이 올바른지를 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-7 : ValidationUtil.validateUmsMsgIdFormat 메서드 테스트")
    public void testValidateUmsMsgIdFormat() {
        assertTrue(ValidateUtil.validateUmsMsgIdFormat("12345678901234567890")); // 올바른 형식
        assertFalse(ValidateUtil.validateUmsMsgIdFormat(null)); // null 값
        assertFalse(ValidateUtil.validateUmsMsgIdFormat("")); // 빈 문자열
        assertFalse(ValidateUtil.validateUmsMsgIdFormat("123 456")); // 공백 포함
        assertFalse(ValidateUtil.validateUmsMsgIdFormat("123456789012345678901")); // 20자 초과
    }

    /**
     * {@link ValidateUtil#validateOriginCode(String)} 메서드를 테스트합니다.
     * Origin Code 형식이 올바른지를 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-8 : ValidationUtil.validateOriginCode(String originCode) 메서드 테스트")
    public void testValidateOriginCode() {
        assertTrue(ValidateUtil.validateOriginCode("123456789")); // 올바른 형식
        assertFalse(ValidateUtil.validateOriginCode(null)); // null 값
        assertFalse(ValidateUtil.validateOriginCode("")); // 빈 문자열
        assertFalse(ValidateUtil.validateOriginCode("12345678A")); // 숫자 이외의 문자 포함
        assertFalse(ValidateUtil.validateOriginCode("123456")); // 길이 부족
    }

    /**
     * {@link ValidateUtil#assignDefaultSubjectIfNullOrEmpty(MessageDelivery)} 메서드를 테스트합니다.
     * 제목이 null 또는 비어 있는 경우 기본 제목이 할당되는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-9 : ValidateUtil.assignDefaultSubjectIfNullOrEmpty(MessageDelivery messageDelivery) 메서드 테스트")
    public void testAssignDefaultSubjectIfNullOrEmpty() {
        // 테스트 준비
        MessageDelivery messageDelivery = new MessageDelivery();

        // 실행
        ValidateUtil.assignDefaultSubjectIfNullOrEmpty(messageDelivery);

        // 검증
        assertEquals("제목 없음", messageDelivery.getContent().get(Submit.SUBJECT));

        // 제목이 이미 설정된 경우
        messageDelivery.getContent().put(Submit.SUBJECT, "기존 제목");
        ValidateUtil.assignDefaultSubjectIfNullOrEmpty(messageDelivery);
        assertEquals("기존 제목", messageDelivery.getContent().get(Submit.SUBJECT));
    }

    /**
     * {@link ValidateUtil#assignDefaultFallbackSubjectIfNullOrEmpty(MessageDelivery)} 메서드를 테스트합니다.
     * fallback 제목이 null 또는 비어 있는 경우 기본 제목이 할당되는지 확인합니다.
     */
    @Test
    @DisplayName("MC-VUTL-10")
    public void testAssignDefaultFallbackSubjectIfNullOrEmpty() {
        // 테스트 준비
        MessageDelivery messageDelivery = new MessageDelivery();

        // 실행
        ValidateUtil.assignDefaultFallbackSubjectIfNullOrEmpty(messageDelivery);

        // 검증
        assertEquals("제목 없음", FallbackUtil.getFallbackSubject(messageDelivery));

        // fallback 제목이 이미 설정된 경우
        FallbackUtil.setFallbackSubject(messageDelivery, "기존 제목");
        ValidateUtil.assignDefaultFallbackSubjectIfNullOrEmpty(messageDelivery);
        assertEquals("기존 제목", FallbackUtil.getFallbackSubject(messageDelivery));
    }
}
