package rxobserver;

import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApplicationTest {

    @Test
    public void ObserverWithSubscriber() {
        Observable<String> observable = ObserverFactory.create();
        Subscriber<String> subscriber = SubscriberFactory.create();

        observable.subscribe(subscriber);
    }

    @Test
    public void lambdaObserverWithSubscriber() {
        Observable.create(
                sub -> {
                    sub.onNext("Hello, reactive world!");
                    sub.onCompleted();
                }
        ).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Done!")
        );
    }

    @Test
    public void observables() {
        Observable.just("1", "2", "3", "4").subscribe(SubscriberFactory.create());
        Observable.from(new String[] {"A", "B", "C"}).subscribe(SubscriberFactory.create());

        Observable.from(Collections.emptyList()).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("done")
        );
    }

    @Test
    public void callableAndFuture() {
        Observable<String> hello = Observable.fromCallable(() -> "Hello ");
        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> world = Observable.from(future);

        Observable.concat(hello, world, Observable.just("!\n"))
                .forEach(System.out::print);

        Observable.concat(hello, world, Observable.just("!!\n")).subscribe(System.out::print);
    }
}