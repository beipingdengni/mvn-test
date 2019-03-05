package com.tian.spring.rabbitmq.disruptar;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author tianbeiping
 * @Title: LongEventProducer
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/5下午5:22
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb)
    {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try
        {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }

}
