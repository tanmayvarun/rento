package com.rento.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String IST_ZONE = "Asia/Calcutta";
    public static final ZoneId IST_ZONE_ID = ZoneId.of(IST_ZONE);
    public static final int SECONDS_IN_DAY = 86400;

    public static int weekOfYear() {
        ZonedDateTime now = ZonedDateTime.now(IST_ZONE_ID);
        return now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public static Date currentTime() {
        return new Date(System.currentTimeMillis());
    }

    private static Date addSecondsToCurrentTime(long seconds, boolean add) {
        int multiplier = 1;
        if (!add)
            multiplier = -1;
        long newTime = System.currentTimeMillis() + multiplier * seconds * 1000;
        return new Date(newTime);
    }

    public static Date addSecondsToCurrentTime(long seconds) {
        return addSecondsToCurrentTime(seconds, true);
    }

    public static Date subtractSecondsFromCurrentTime(long seconds) {
        return addSecondsToCurrentTime(seconds, false);
    }

    public static Date addDaysToCurrentTime(int days) {
        long seconds = days * SECONDS_IN_DAY;
        return addSecondsToCurrentTime(seconds, true);
    }

    public static Date subtractDaysFromCurrentTime(int days) {
        long seconds = days * SECONDS_IN_DAY;
        return addSecondsToCurrentTime(seconds, false);
    }

    public static String formatLocalDate(DateTimeFormatter dateTimeFormatter, LocalDate localDate) {
        return dateTimeFormatter.format(localDate);
    }

    public static String formatLocalDateTime(DateTimeFormatter dateTimeFormatter, LocalDateTime localDateTime) {
        return dateTimeFormatter.format(localDateTime);
    }

    public static String formatZonedDateTime(DateTimeFormatter dateTimeFormatter, ZonedDateTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    public static String formatDate(DateTimeFormatter dateTimeFormatter, Date date) {
        return dateTimeFormatter.format(fromDate(date));
    }

    public static LocalDate fromDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(IST_ZONE_ID));
    }
}
