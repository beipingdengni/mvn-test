package com.tian.spring.common.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author tianbeiping
 * @Title: DatetimeUtil
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/27下午2:17
 */
public class DatetimeUtil {

    private static final DateTimeFormatter fmt_yyyyMMddHHmmssSSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter fmt_yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter fmt_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter fmt_yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter fmt_HHmmss = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter fmt_HHmm = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter fmt_yyyyMMddHHmmss_noSplit = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter fmt_yyyyMMddHHmm_noSplit = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final DateTimeFormatter fmt_yyyyMMdd_noSplit = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter fmt_HHmmss_noSplit = DateTimeFormatter.ofPattern("HHmmss");
    private static final DateTimeFormatter fmt_HHmm_noSplit = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter fmt_YYYYMMDDHHMM = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

    public static final LocalDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDateTime();

    //TimeZone.getAvailableIDs()

    public static String getNowSss() {
        return fmt_yyyyMMddHHmmssSSS.format(dateTime);
    }

    public static String getNow() {
        return dateTime.format(fmt_yyyyMMddHHmmss);
    }

    public static String getNowToMm() {
        return fmt_yyyyMMddHHmm.format(dateTime);
    }

    public static String getNowToDd() {
        return fmt_yyyyMMdd.format(dateTime);
    }

    public static String getTime() {
        return fmt_HHmmss.format(dateTime);
    }

    public static Date parse(String dateTime) {
        LocalDateTime parse = LocalDateTime.parse(dateTime, fmt_yyyyMMddHHmmss);
        return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parse(String dateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime parse = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date LocalDateToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime DateToLocalDate(Date dateTime) {
        Instant instant = dateTime.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }


}
