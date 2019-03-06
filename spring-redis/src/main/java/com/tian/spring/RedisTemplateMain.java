package com.tian.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tianbeiping
 * @Title: RedisTemplateMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/6下午6:01
 */
public class RedisTemplateMain {


    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");

        StringRedisTemplate redis = context.getBean(StringRedisTemplate.class);

//        redis.delete("key");
        String key = redis.opsForValue().get("key");
        if (key == null || "".equals(key)) {
            redis.opsForValue().set("key", "test  中文");
        }
        System.out.println("=======");

    }


}
