package com.tian.spring.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class MainTest {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        System.out.println(start);


        IntStream.range(1, 20).forEach(i -> {

            AtomicInteger atomicInteger = new AtomicInteger(1);

            Flux.<Integer>create(emitter -> {

                Integer i1 = atomicInteger.incrementAndGet();

                if (i1 % 2 == 0) {
                    System.out.println("== 0 ==");
                    emitter.next(i1 + 10000);
                } else {
                    System.out.println("== 1 ==");
                    emitter.error(new SelfExcaption(502, "server error" + i1));
                }
                System.out.println(Thread.currentThread().getId() + ":==emitter==:" + Thread.currentThread().getName());
//                emitter.complete();

            })
                    .doOnError(throwable -> {
                        System.out.println(Thread.currentThread().getId() + ":==doOnError==:" + Thread.currentThread().getName());
                        System.out.println(" doOnError====>  " + throwable);
                    })
                    .delayElements(Duration.ofMillis(500)).retry(2)
                    .subscribe(data -> {
                        System.out.println(System.currentTimeMillis() - start);
//                        int ss = 1 / 0;
                        System.out.println("data====> " + data);
                        System.out.println(Thread.currentThread().getId() + ":==subscribe==success==:" + Thread.currentThread().getName());
                    }, error -> {
                        System.out.println(System.currentTimeMillis() - start);
                        System.out.println("error====> " + error.toString());
                        System.out.println(Thread.currentThread().getId() + ":subscribe:error:" + Thread.currentThread().getName());
                    });


            System.out.println("=============================================================================================");


        });

        Thread.sleep(2000);

        System.out.println();


    }
}
