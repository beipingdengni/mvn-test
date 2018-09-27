package com.tian.spring.common.date;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

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

    private static final LocalDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDateTime();

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


}
