package com.yuriy.reactive.server;

import com.yuriy.reactive.model.PriceInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.List;

public class Server {

    public static Observable<PriceInfo> getSource(final List<String> companies) {
        return Observable.create(emitter -> emit(emitter, companies));
    }

    private static void emit(ObservableEmitter<PriceInfo> observableEmitter, List<String> companies) throws InterruptedException {
       for(int i = 0; i <3; i++) {
           companies.stream()
                   .map(PriceService::getPrice)
                   .forEach(observableEmitter::onNext);
           Thread.sleep(1000);
       }
       observableEmitter.onComplete();
    }
}
