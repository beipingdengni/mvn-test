package com.tian.spring.quartz.annotion;

/**
 * @author tianbeiping
 * @Title: CustomQuartz
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30下午3:33
 */
public class CustomQuartz {

    @QuartzCron("0/1 * * * * ?")
    public void helloJob() {
        System.out.println(" 启动开始执行 doing  ===> " + System.currentTimeMillis() / 1000);
    }


}
