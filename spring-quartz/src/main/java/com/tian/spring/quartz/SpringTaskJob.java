package com.tian.spring.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: SpringTaskJob
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/29下午6:54
 */
@Component
public class SpringTaskJob {

    @Scheduled(cron = "0/1 * * * * ?")
    public void jobCronTest() {
        System.out.println(" asdd ===== > " + System.currentTimeMillis());
    }

}
