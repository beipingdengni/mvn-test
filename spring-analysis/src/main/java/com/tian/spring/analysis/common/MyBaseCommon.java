package com.tian.spring.analysis.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: MyBaseCommon
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午2:02
 */
@Component
public class MyBaseCommon implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String testHello(String sss) {

        return this.getClass().getName() + "   ==== > " + sss;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info(" MyBaseCommon implements InitializingBean   ==========>   afterPropertiesSet ");

    }
}
