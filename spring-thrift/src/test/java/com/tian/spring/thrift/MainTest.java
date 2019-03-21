package com.tian.spring.thrift;

import org.junit.Test;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/19上午10:51
 */
public class MainTest {


    @Test
    public void testUrl() {

        String url = "http://m.test.9now.net/c_mboss/smartcloud/index/message?type=12&name=%E5%AE%A2%E6%88%B7%E6%B5%81%E5%A4%B1%E5%91%8A%E8%AD%A6&brand_id=#mShopId#&shop_id=#shopId#";

        url = url.replaceAll("#mShopId#", 123123 + "");
        url = url.replaceAll("#shopId#", 9993123 + "");

        System.out.println(url);

    }


}
