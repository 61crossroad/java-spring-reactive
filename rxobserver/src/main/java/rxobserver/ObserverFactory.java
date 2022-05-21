package rxobserver;

import rx.Observable;

public class ObserverFactory {

    public static Observable<String> create() {
        return Observable.create(
                sub -> {
                    sub.onNext("Hello, reactive world!");
                    sub.onCompleted();
                }
        );
    }
}
