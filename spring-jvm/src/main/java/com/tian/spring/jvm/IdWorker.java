package com.tian.spring.jvm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tianbeiping
 * @Title: IdWorker
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午4:53
 */
public class IdWorker {

    private long workerId;
    private long datacenterId;
    private long sequence = 0;
    /**
     * 2018/9/29日，从此时开始计算，可以用到2089年
     */
    private long twepoch = 1538211907857L;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    // 得到0000000000000000000000000000000000000000000000000000111111111111
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;


    public IdWorker(long workerId, long datacenterId) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }


    /**
     * 雪花算法
     *
     * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        //时间回拨，抛出异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << 22L) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private ReentrantLock reentrantLock;
    private AtomicLong atomicLong;
    private String dateTime;

    public IdWorker(ReentrantLock reentrantLock, AtomicLong atomicLong, String dateTime) {
        this.reentrantLock = reentrantLock;
        this.atomicLong = atomicLong;
        this.dateTime = dateTime;

    }

    public Long getId() {
        long id = 0L;
        try {
            reentrantLock.lock();
            String localDateTime = dateTime;
            long andIncrement = atomicLong.getAndIncrement();
            String s = String.valueOf(andIncrement);
            int length = s.length();
            if (length < 10) {
                int i = 10 - length;
                for (int j = 0; j < i; j++) {
                    localDateTime = localDateTime + "0";
                }
            }
            localDateTime = localDateTime + s;
            return Long.valueOf(localDateTime).longValue();

        } finally {
            reentrantLock.unlock();
        }
    }


    /**
     * 当前ms已经满了
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Map<Object, Object> map = new ConcurrentHashMap<>();

//        ExecutorService service = Executors.newFixedThreadPool(10);
//        ReentrantLock reentrantLock = new ReentrantLock();
//        AtomicLong atomicLong = new AtomicLong(1);
//        String dateTime = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        for (int i = 0; i < 10; i++) {
//            service.execute(new TestGenId(map, reentrantLock, atomicLong, dateTime));
//        }
//        if (!service.isShutdown()) {
//            System.out.println("end");
//            service.shutdown();
//        } else {
//            System.out.println("continue");
//        }


        /**
         * 雪花算法
         */

        IdWorker worker = new IdWorker(1, 1);
        for (int i = 0; i < 1<<3; i++) {
            long l = worker.nextId();
            Long o = (Long) map.putIfAbsent(l, l);
            System.out.println(l + ":" + o);
        }
        System.out.println("==========================");


    }

    public static class TestGenId implements Runnable {

        private IdWorker idWorker;
        private Map<Object, Object> map;

        public TestGenId(Map<Object, Object> map, ReentrantLock reentrantLock, AtomicLong atomicLong, String dateTime) {
            idWorker = new IdWorker(reentrantLock, atomicLong, dateTime);
            this.map = map;
        }

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
//                long l = idWorker.nextId();
                long l = idWorker.getId();
                Long o = (Long) map.putIfAbsent(l, l);
                System.out.println(l + ":" + o);
            }
            System.out.println("==========================");
        }
    }

}


