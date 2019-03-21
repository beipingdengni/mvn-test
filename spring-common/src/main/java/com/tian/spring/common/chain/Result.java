package com.tian.spring.common.chain;

/**
 * @author tianbeiping
 * @Title: Result
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午4:16
 */
public class Result {

    private Object data;

    public Result() {
    }

    public Result(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
