package rxobserver;

import rx.Subscriber;

public class SubscriberFactory {

    public static Subscriber<String> create() {
        return new Subscriber<>() {
            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println(e);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
    }
}
