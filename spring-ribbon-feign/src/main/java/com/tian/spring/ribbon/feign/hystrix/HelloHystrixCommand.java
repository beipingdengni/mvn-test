package com.tian.spring.ribbon.feign.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategyDefault;

/**
 * @author tianbeiping
 * @Title: HelloHystrixCommand
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/10上午9:54
 */
public class HelloHystrixCommand extends HystrixCommand<String> {

    private String name;

    protected HelloHystrixCommand(String name) {
        // simple
//        super(HystrixCommandGroupKey.Factory.asKey("hello-one"));

        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("hello-group")
                ).andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadPool-key"))
                        .andThreadPoolPropertiesDefaults(
                                // 配置线程池
                                HystrixThreadPoolProperties.Setter()
                                        .withCoreSize(100) // 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                                        .withMaximumSize(10000)
                        )
                        .andCommandPropertiesDefaults(
                                // 配置熔断器
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerEnabled(true)
                                        .withExecutionTimeoutEnabled(true)
                                        .withExecutionTimeoutInMilliseconds(30000) // 30 秒时间
                                        .withCircuitBreakerRequestVolumeThreshold(3)
                                        .withCircuitBreakerErrorThresholdPercentage(80)
//                                        .withCircuitBreakerForceOpen(true)	// 置为true时，所有请求都将被拒绝，直接到fallback
//                		                  .withCircuitBreakerForceClosed(true)	// 置为true时，将忽略错误
                        )
        );

        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello " + name;
    }

    @Override
    protected String getFallback() {
        return "finish -- 成功";
    }
}
