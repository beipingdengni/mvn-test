package com.tian.spring.common;

import java.util.Objects;
import java.util.Random;

/**
 * @author tianbeiping
 * @Title: StringUtil
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午3:25
 */
public class StringUtil {


    public static boolean isBlank(Object o) {
        if (Objects.isNull(o) || "".equals(o)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Random random = new Random();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        // 3800+50+2+10
        // 3862
        while (i < 2000) {
            i++;
            int i1 = random.nextInt(100);
            sb.append("1931" + i1 + ",");
        }
        System.out.println(sb.toString());


    }

}
