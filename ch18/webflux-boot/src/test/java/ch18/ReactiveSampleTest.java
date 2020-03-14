package ch18;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ReactiveSampleTest {
    private final Logger logger = LoggerFactory.getLogger(ReactiveSampleTest.class);

    @Test
    public void testFlux() {
        List<String> elements = new ArrayList<>();
        Flux.just("hope", "sadness", "smile", "tear", "grin", "cry", "laugh")
                .log()
                .subscribe(new Subscriber<String>() {
                    private Subscription sub;
                    int onNextAmount;

                    // subscribe 호출 시 수행
                    @Override
                    public void onSubscribe(Subscription s) {
                        // 주어진 인자만큼 onNext 호출
                        s.request(Long.MAX_VALUE);
//                        s.request(1);
                        this.sub = s;
                    }

                    @Override
                    public void onNext(String s) {
                        String tmp = s.toUpperCase();
                        elements.add(tmp);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            // onSubscribe 에서 충분히 request 하지 않은 경우 onNext 에서 request 해야함
                            sub.request(2);
                        }
                    }

                    // error 발생 시 수행
                    @Override
                    public void onError(Throwable t) {
                        logger.error("Unexpected issue!", t);
                        fail("This Flux test failed!");
                    }

                    // subscribe 완료 시 수행
                    @Override
                    public void onComplete() {
                        logger.info("All done!");
                        elements.forEach(logger::info);
                        assertTrue(elements.size() == 7);
                    }
                });
    }
}
