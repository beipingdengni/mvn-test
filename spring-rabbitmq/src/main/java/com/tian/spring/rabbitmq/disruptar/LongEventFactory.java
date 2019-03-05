package com.tian.spring.rabbitmq.disruptar;

import com.lmax.disruptor.EventFactory;

/**
 * @author tianbeiping
 * @Title: LongEventFactory
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/5下午5:17
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
