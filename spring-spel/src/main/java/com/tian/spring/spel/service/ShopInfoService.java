package com.tian.spring.spel.service;

import com.tian.spring.spel.common.DataSource;
import com.tian.spring.spel.model.ShopInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShopInfoService {


    @DataSource(argParam = "#brandId")
    public void getBrandId(long brandId) {

        System.out.println("=============" + brandId + "==================");
    }

    @DataSource(argParam = "#info.brandId")
    public void getBrandIdInfo(ShopInfo info) {

        System.out.println("=============" + info.toString() + "==================");
    }

    @DataSource(argParam = "#info[val]")
    public void getBrandIdMap(Map<String, String> info) {

        System.out.println("=============" + info.toString() + "==================");
    }

}
