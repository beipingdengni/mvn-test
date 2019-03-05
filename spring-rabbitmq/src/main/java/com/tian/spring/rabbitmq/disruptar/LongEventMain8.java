package com.tian.spring.rabbitmq.disruptar;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
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
public class LongEventMain8 {

    public static void main(String[] args) throws Exception {

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024*1024;

        //创建单线程处理
        //Disruptor<LongEvent> disruptorSingle = new Disruptor(factory, bufferSize, ProducerType.SINGLE, new BlockingWaitStrategy(), DaemonThreadFactory.INSTANCE);
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("handleEvent---" + Thread.currentThread().getName() + ": " + event));
//        disruptor.handleEventsWith(new LongEventHandler());
//        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
//            System.out.println("Event---one--: " + event + "  :  " + sequence);
//
//        }).then((EventHandler<LongEvent>) (event, sequence, endOfBatch) -> {
//            System.out.println("Event---two--: " + event + "  :  " + sequence);
//        });

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        long start = System.currentTimeMillis();
//        for (long l = 0; true; l++) {
//        for (long l = 0; l < 50000000000l; l++) {
        for (long l = 0; l < 5000000l; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
//            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("====>:" + (end - start));


    }


}
