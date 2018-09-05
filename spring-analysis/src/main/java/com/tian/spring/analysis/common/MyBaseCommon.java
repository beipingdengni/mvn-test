package com.tian.spring.analysis.common;

import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: MyBaseCommon
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午2:02
 */
@Component
public class MyBaseCommon {

    public String testHello(String sss) {

        return this.getClass().getName() + "   ==== > " + sss;
    }

}
