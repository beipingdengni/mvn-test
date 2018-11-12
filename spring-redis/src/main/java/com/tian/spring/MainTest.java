package com.tian.spring;

import com.google.common.collect.Sets;
import redis.clients.jedis.*;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午5:49
 */
public class MainTest {


    public static void main(String[] args) {


//        JedisPoolConfig config=new JedisPoolConfig();
//        config.setMaxTotal(10);
//        config.setMaxTotal(10);
//        config.setMinIdle(10);

        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();

        jedis.set("key_two", "value1");

        System.out.println(jedis.setex("key_one", 1000, "haha"));
        // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
        boolean keyExist = jedis.exists("key");
        if (keyExist) {
            jedis.del("key");
        }
        // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
        System.out.println(jedis.set("key", "value", "NX", "EX", 1000));
        System.out.println(jedis.set("key_1", "value", "NX", "EX", 10));
        // 扫描100个key
        ScanResult<String> scan = jedis.scan("100");
        scan.getResult().forEach(e -> System.out.println(e + ":" + jedis.get(e) + ":" + jedis.ttl(e)));
        // key=存将设置失败 0,key=不存在设置为成功 1
        System.out.println(jedis.setnx("key", "wewe"));
        System.out.println(jedis.setnx("key_2", "wewe_2"));
        System.out.println(jedis.get("key"));
        System.out.println(jedis.get("key_2"));
        System.out.println(jedis.expire("key_2", 5));


    }
}
