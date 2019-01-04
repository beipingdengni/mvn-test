package com.tian.spring.analysis.scheduled;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: TimerScheduled
 * @ProjectName mvn-test
 * @Description:
 * @date 19/1/4下午2:53
 */
public class TimerScheduled {

    public static void main(String[] args) {


        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
        }, 5L, 2L, TimeUnit.SECONDS);


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
            }
        }, 5000, 1000);


    }
}
