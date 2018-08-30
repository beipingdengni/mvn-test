package com.tian.spring.quartz.annotion;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author tianbeiping
 * @Title: CustomQuartz
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30下午3:33
 */
public class CustomSpringQuartz implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println(" 启动开始执行 doing  ===> " + System.currentTimeMillis() / 1000);

    }
}
