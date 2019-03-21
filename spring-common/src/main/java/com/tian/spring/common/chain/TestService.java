package com.tian.spring.common.chain;

/**
 * @author tianbeiping
 * @Title: TestService
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午3:38
 */
public class TestService {

    public Result getUserName(String id, String age) {
        System.out.println("id=>" + id + "  ==>" + age);
        return new Result("id=>" + id + "  ==>" + age);
    }

}
