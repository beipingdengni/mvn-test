package com.tian.spring.reactor;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class RxjavaOneMainTest {

    public static void main(String[] args) throws InterruptedException {


        long start = System.currentTimeMillis();

        System.out.println(start);
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("a");
            emitter.onError(new Throwable("emitter报了个错!"));
//            emitter.onNext("c");
            emitter.onComplete();
        }).doOnError(throwable -> {

            System.out.println(throwable.toString());

        }).delay(1000, TimeUnit.MILLISECONDS).retry(2).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("subscribe=>");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(" onNext  " + s + " ");
                System.out.println(System.currentTimeMillis() - start);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
                System.out.println(System.currentTimeMillis() - start);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

//        System.out.println(System.currentTimeMillis() - start);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() - start);

    }
}
