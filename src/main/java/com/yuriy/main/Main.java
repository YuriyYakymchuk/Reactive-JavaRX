package com.yuriy.main;

import com.yuriy.reactive.model.PriceInfo;
import com.yuriy.reactive.server.Server;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final List<String> stocks = Arrays.asList("APPL", "GOOG", "MSFT", "INTC");

        Observable<PriceInfo> publisher = Server.getSource(stocks);
        Disposable subscribe = publisher
                .subscribe(
                        priceInfo -> System.out.println(priceInfo),
                        throwable -> System.out.println("Error"),
                        () -> System.out.println("Complete")
                );
        subscribe.dispose();

        FlowableSubscriber<String> flowableSubscriber = new FlowableSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(6);
            }

            @Override
            public void onNext(String s) {
                System.out.println("Next: " + s);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };


        Flowable<String> server = Flowable.fromArray("APPL", "GOOG", "MSFT", "INTC");

        server.subscribe(flowableSubscriber);
        server.subscribe(flowableSubscriber);

        /*Disposable subscribe1 = Flowable.range(1, 100)
                .observeOn(Schedulers.computation())
                .subscribe(integer -> compute(integer), Throwable::printStackTrace);
*/
        System.out.print(stocks);
    }

    private static void compute(Integer integer) {
        System.out.println(integer);
    }
}

