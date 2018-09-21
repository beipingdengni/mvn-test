package com.tian.spring.common;

import java.util.Objects;

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

}
