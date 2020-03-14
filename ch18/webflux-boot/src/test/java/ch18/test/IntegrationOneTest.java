package ch18.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationOneTest {
    private final Logger logger = LoggerFactory.getLogger(IntegrationOneTest.class);

    @Test
    public void test1One() throws InterruptedException {
        Flux<String> numbers = Flux.just("1", "2", "3");
        Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
        // doOnNext 는 subscribe 하지 않으면 수행되지 않는다
        Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info).subscribe();
        // interval 이 실행되는 시간동안 쓰레드를 유지해야 된다
        Thread.sleep(6000);
    }

    @Test
    public void test2One() throws InterruptedException {
        Flux<String> numbers = Flux.just("11", "22", "33");
        Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
        // doOnNext 는 subscribe 하지 않으면 수행되지 않는다
        Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info).subscribe();
        // interval 이 실행되는 시간동안 쓰레드를 유지해야 된다
        Thread.sleep(6000);
    }
}
