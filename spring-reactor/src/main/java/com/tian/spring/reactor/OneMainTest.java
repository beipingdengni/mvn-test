package com.tian.spring.reactor;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OneMainTest {

    public static void main(String[] args) {

//
//        Mono.fromCallable(System::currentTimeMillis)
//                .flatMap(time -> Mono.first(123123, 123123))
//                .timeout(Duration.ofSeconds(3), errorHandler::fallback)
//                .doOnSuccess(r -> serviceM.incrementSuccess())
//                .subscribe(System.out::println);


        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10);

        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("========= " + LocalDateTime.now()), 0, 500, TimeUnit.MILLISECONDS);
        }

    }
}
