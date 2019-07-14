package com.tian.spring.spel;

import com.google.common.collect.Maps;
import com.tian.spring.spel.model.ShopInfo;
import com.tian.spring.spel.service.ShopInfoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class MainTest {


    public static void main(String[] args) {


        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");


        ShopInfoService bean = classPathXmlApplicationContext.getBean(ShopInfoService.class);
        bean.getBrandId(42342L);
        bean.getBrandIdInfo(new ShopInfo(42342L, "shopName", 1111L, "brandName"));
        HashMap<String, String> dataMap = Maps.newHashMap();
        dataMap.put("val", "{brandid,asdad}");
        bean.getBrandIdMap(dataMap);

    }

}
