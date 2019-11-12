package com.tian.spring.reactor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class RxjavaMainTest {

    public static void main(String[] args) {

        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("a");
            emitter.onNext("b");
            emitter.onNext("c");
            emitter.onComplete();
        });

        Observer observer1 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe=>");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s + " ");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };

        observable.subscribe(observer1);
//        observable.delay(500, TimeUnit.MILLISECONDS).retry(2).subscribe(observer1);

    }
}
