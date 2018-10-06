package com.tian.spring.ribbon.feign.config;

import com.netflix.client.ClientFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.*;
import com.tian.spring.ribbon.feign.provider.UserServiceProvider;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: RibbonCofig
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午1:49
 */
public class RibbonConfig {
    static {
        try {
            ConfigurationManager.loadPropertiesFromResources("resteasy-client.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserServiceProvider remoteService() {
        RibbonClient client = RibbonClient.builder().lbClientFactory(clientName -> {
            /**
             * rabbit 配置
             */
            IClientConfig config = ClientFactory.getNamedConfig(clientName);
            ILoadBalancer lb = ClientFactory.getNamedLoadBalancer(clientName);
            ZoneAwareLoadBalancer zb = (ZoneAwareLoadBalancer) lb;
            zb.setRule(zoneAvoidanceRule());
            return LBClient.create(lb, config);
        }).build();

        UserServiceProvider service = Feign.builder()
                .client(client)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(UserServiceProvider.class, "http://resteasy-client");

        return service;
    }

    /**
     * Ribbon负载均衡策略实现
     * 使用ZoneAvoidancePredicate和AvailabilityPredicate来判断是否选择某个server，前一个判断判定一个zone的运行性能是否可用，
     * 剔除不可用的zone（的所有server），AvailabilityPredicate用于过滤掉连接数过多的Server。
     *
     * @return
     */
    private static IRule zoneAvoidanceRule() {
        return new ZoneAvoidanceRule();
    }

    /**
     * Ribbon负载均衡策略实现
     * 随机选择一个server。
     *
     * @return
     */
    private static IRule randomRule() {
        return new RandomRule();
    }

}
