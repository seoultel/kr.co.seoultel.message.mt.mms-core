package kr.co.seoultel.message.mt.mms.core.util;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class DateUtil {
    private static final DateTimeFormatter mcmpLocalDateFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter fullLocalDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getDate() {
        LocalDateTime future = LocalDateTime.now();
        return future.format(mcmpLocalDateFormatter);
    }


    public static String getDate(int plusSecond) {
        LocalDateTime future = LocalDateTime.now().plusSeconds(plusSecond);
        return future.format(mcmpLocalDateFormatter);
    }

    public static String getDate(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }

    public static String getDate(int plusSeconds, String format) {
        LocalDateTime future = LocalDateTime.now().plusSeconds((long) plusSeconds);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return future.format(formatter);
    }


    /*
    * String getDate(int plusSeconds, String format) 메서드 호출 시
    * java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds 예외 발생하는 경우
    * format 에 "Z" 가 포함된 경우 사용
    * */
    public static String getZoneDateTime(int plusSeconds, String format) {
        ZonedDateTime future = ZonedDateTime.now().plusSeconds((long) plusSeconds);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
        return future.format(formatter);
    }

    public static String parseFullLocalDateToMcmpLocalDateFormat(String localDateTimeString) {
        LocalDateTime fullLocalDateTime = LocalDateTime.parse(localDateTimeString, fullLocalDateFormatter);
        return fullLocalDateTime.format(mcmpLocalDateFormatter);
    }

    public static String parseLocalDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(mcmpLocalDateFormatter);
    }

    public static Long parseLocalDateToLong(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long getCurrentTimeInMillis() {
        return Instant.now().toEpochMilli();
    }

    public static long getTimeGapUntilNextSecond() {

        long currentTime = getCurrentTimeInMillis();

        long nextSecondTime = (currentTime / 1000 + 1) * 1000;

        return nextSecondTime - currentTime;
    }

}
