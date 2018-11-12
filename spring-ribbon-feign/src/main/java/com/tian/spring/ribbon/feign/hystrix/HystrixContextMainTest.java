package com.tian.spring.ribbon.feign.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: HystrixMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/10上午9:48
 */
public class HystrixContextMainTest extends HystrixCommand<String> {


    private String name;

    public HystrixContextMainTest(String name) {
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("group-name")
        ).andCommandKey(HystrixCommandKey.Factory.asKey("command-key"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(100)
                ).andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withRequestCacheEnabled(true)
                        .withRequestLogEnabled(true)
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionTimeoutInMilliseconds(30000) //30 秒
                ));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("run  Exception");
//        return "run about :" + System.currentTimeMillis();
        throw new Exception("failure");
    }

    @Override
    protected String getFallback() {
        System.out.println("getFallback");
        return "fallback ==========>" + System.currentTimeMillis();
    }


    @Override
    protected String getCacheKey() {
        System.out.println("getCacheKey : " + name);
        return this.name + ":key";
    }

    public static void main(String[] args) throws IOException {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {

            HystrixContextMainTest test = null;

            for (int i = 0; i < 5; i++) {
                test = new HystrixContextMainTest("test");
                String execute = test.execute();
                // 第一次请求，不应该从缓存中获取
                //没有执行excute()，isResponseFromCache()永远返回是true
                System.out.println("isResponseFromCache====>" + test.isResponseFromCache());

                System.out.println(execute);
            }
            HystrixContextMainTest test1 = new HystrixContextMainTest("test");

        } finally {
            context.shutdown();
        }
    }


}
