package com.tian.spring.common;

import com.tian.spring.common.date.DatetimeUtil;

/**
 * @author tianbeiping
 * @Title: DateMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/27下午2:01
 */
public class DateMainTest {

    public static void main(String[] args) {

        String now = DatetimeUtil.getNow();
        String nowSss = DatetimeUtil.getNowSss();
        String nowToDd = DatetimeUtil.getNowToDd();
        String nowToMm = DatetimeUtil.getNowToMm();
        String time = DatetimeUtil.getTime();

        System.out.println();

    }
}
