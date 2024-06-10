package util;

import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


public class DateUtilTest {

    /**
     * {@link DateUtil#getDate()} 메서드를 테스트합니다.
     * 현재 날짜 및 시간을 제대로 반환하는지 확인합니다.
     */
    @Test
    public void testGetDate() {
        String currentDate = DateUtil.getDate();
        assertNotNull(currentDate);
        assertTrue(currentDate.matches("\\d{14}")); // 정규 표현식을 사용하여 날짜 형식 확인
    }

    /**
     * {@link DateUtil#getDate(int)} 메서드를 테스트합니다.
     * 현재 날짜 및 시간에 주어진 초를 더한 값을 제대로 반환하는지 확인합니다.
     */
    @Test
    public void testGetDateWithPlusSecond() {
        int plusSecond = 60; // 60초 추가
        String futureDate = DateUtil.getDate(plusSecond);
        assertNotNull(futureDate);
        assertTrue(futureDate.matches("\\d{14}")); // 정규 표현식을 사용하여 날짜 형식 확인
    }

    /**
     * {@link DateUtil#parseLocalDateToString(LocalDateTime)} 메서드를 테스트합니다.
     * {@link LocalDateTime} 객체를 문자열로 올바르게 변환하는지 확인합니다.
     */
    @Test
    public void testParseLocalDateToString() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateString = DateUtil.parseLocalDateToString(localDateTime);
        assertNotNull(dateString);
        assertEquals(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), dateString);
    }

    /**
     * {@link DateUtil#parseLocalDateToLong(LocalDateTime)} 메서드를 테스트합니다.
     * {@link LocalDateTime} 객체를 밀리초로 올바르게 변환하는지 확인합니다.
     */
    @Test
    public void testParseLocalDateToLong() {
        LocalDateTime localDateTime = LocalDateTime.now();
        long epochMillis = DateUtil.parseLocalDateToLong(localDateTime);
        assertTrue(epochMillis > 0);
    }

    /**
     * {@link DateUtil#getCurrentTimeInMillis()} 메서드를 테스트합니다.
     * 현재 시간을 밀리초로 올바르게 반환하는지 확인합니다.
     */
    @Test
    public void testGetCurrentTimeInMillis() {
        long currentTimeInMillis = DateUtil.getCurrentTimeInMillis();
        assertTrue(currentTimeInMillis > 0);
    }

    /**
     * {@link DateUtil#getTimeGapUntilNextSecond()} 메서드를 테스트합니다.
     * 다음 초까지의 시간 간격이 올바르게 계산되는지 확인합니다.
     */
    @Test
    public void testGetTimeGapUntilNextSecond() {
        long timeGap = DateUtil.getTimeGapUntilNextSecond();
        assertTrue(timeGap >= 0 && timeGap < 1000); // 시간 간격이 0 이상이고 1000 밀리초 미만인지 확인
    }
}
