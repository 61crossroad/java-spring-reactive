package org.example.rxthermometer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscription;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TemperatureController {

    private final TemperatureSensor temperatureSensor;

    @GetMapping("/temperature-stream")
    public SseEmitter events() {
        RxSseEmitter emitter = new RxSseEmitter();

        Subscription subscription = temperatureSensor.temperatureStream()
                .subscribe(emitter.getSubscriber());

        log.info(subscription.toString());

        return emitter;
    }
}
