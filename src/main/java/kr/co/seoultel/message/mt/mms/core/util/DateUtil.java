package kr.co.seoultel.message.mt.mms.core.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String getDate() {
        LocalDateTime future = LocalDateTime.now();
        return future.format(formatter);
    }

    public static String getDate(int plusSecond) {
        LocalDateTime future = LocalDateTime.now().plusSeconds(plusSecond);
        return future.format(formatter);
    }

    public static String parseLocalDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
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
