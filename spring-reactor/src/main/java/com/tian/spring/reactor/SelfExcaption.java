package com.tian.spring.reactor;

public class SelfExcaption extends Exception {

    public Integer code;
    public String msg;

    public SelfExcaption() {
    }

    public SelfExcaption(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SelfExcaption{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
