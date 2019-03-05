package com.tian.spring.rabbitmq.disruptar;


/**
 * @author tianbeiping
 * @Title: LongEvent
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/5下午5:16
 */

public class LongEvent {
    public long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}