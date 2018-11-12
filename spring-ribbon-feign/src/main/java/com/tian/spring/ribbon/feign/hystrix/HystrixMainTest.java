package com.tian.spring.ribbon.feign.hystrix;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: HystrixMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/10上午9:48
 */
public class HystrixMainTest {


    public static void main(String[] args) throws IOException {


        String test = new HelloHystrixCommand("test").execute();
        System.out.println(test);


        // observe()是异步非堵塞性执行，同queue
        Observable<String> hotObservable = new HelloHystrixObserverCommand("Hlx").observe();

        // single()是堵塞的
        System.out.println("hotObservable single结果：" + hotObservable.toBlocking().single());

        // 注册观察者事件
        // subscribe()是非堵塞的
        hotObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
            @Override
            public void onCompleted() {
                System.out.println("hotObservable completed");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("hotObservable onNext: " + v);
            }

        });

        // 非堵塞
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        hotObservable.subscribe(new Action1<String>() {

            // 相当于上面的onNext()
            @Override
            public void call(String v) {
                System.out.println("hotObservable call: " + v);
            }

        });

        // 主线程不直接退出，在此一直等待其他线程执行
        System.in.read();
    }

}
