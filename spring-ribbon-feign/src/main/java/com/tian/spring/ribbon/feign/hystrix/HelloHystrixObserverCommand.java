package com.tian.spring.ribbon.feign.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: HelloHystrixObserverCommand
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/10下午1:43
 */
public class HelloHystrixObserverCommand extends HystrixObservableCommand<String> {


    private String name;

    public HelloHystrixObserverCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("wwwww"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        // a real example would do work like a network call here
                        observer.onNext("construct onNext  two");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }

        }).subscribeOn(Schedulers.io());
    }


    @Override
    protected Observable<String> resumeWithFallback() {

//        return Observable.empty();
//        return Observable.just("完成--ok");
        return Observable.from(Arrays.asList("123123", "1231231", "1231231"));
    }
}
