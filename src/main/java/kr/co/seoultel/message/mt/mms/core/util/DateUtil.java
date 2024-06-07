package kr.co.seoultel.message.mt.mms.core.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {
    private static final DateTimeFormatter mcmpLocalDateFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter fullLocalDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getDate(int plusSecond) {
        LocalDateTime future = LocalDateTime.now().plusSeconds(plusSecond);
        return future.format(mcmpLocalDateFormatter);
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
