package com.tian.spring.rabbitmq.disruptar;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tianbeiping
 * @Title: LongEventMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/5下午5:22
 */
public class LongEventMain {

    public static void main(String[] args) throws Exception {

//        处理
//        EventFactory<LongEvent> eventFactory = new LongEventFactory();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
//        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,ringBufferSize, executor, ProducerType.SINGLE,new YieldingWaitStrategy());
//        EventHandler<LongEvent> eventHandler = new LongEventHandler();
//        disruptor.handleEventsWith(eventHandler);
//        disruptor.start();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }


}
