package com.tian.spring.rabbitmq.disruptar;


import com.lmax.disruptor.EventHandler;

/**
 * @author tianbeiping
 * @Title: LongEventHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/5下午5:18
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("LongEventHandler------" + Thread.currentThread().getName() + ": " + event);
    }
}
