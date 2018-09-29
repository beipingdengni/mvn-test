package com.tian.spring.resteasy.demo;

/**
 * @author tianbeiping
 * @Title: UserSvcImpl
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午5:43
 */
public class UserSvcImpl implements UserSvc {
    @Override
    public String get() {
        return "ok";
    }
}
