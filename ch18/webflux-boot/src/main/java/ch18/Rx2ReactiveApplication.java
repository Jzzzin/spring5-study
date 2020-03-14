/*
package ch18;

import ch18.entities.Singer;
import ch18.repos.Rx2SingerRepo;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

// SingerApplication, ReactiveApplication 을 모두 주석 처리 한 후 실행한다.
@SpringBootApplication
@RestController
public class Rx2ReactiveApplication {

    private static Logger logger = LoggerFactory.getLogger(Rx2ReactiveApplication.class);

    @Autowired
    Rx2SingerRepo rx2SingerRepo;

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Singer> all() {
        Flowable<Singer> singers = rx2SingerRepo.findAll();
        Flowable<Long> periodFlowable = Flowable.interval(2, TimeUnit.SECONDS);
        return singers.zipWith(periodFlowable, (singer, aLong) -> {
            Thread.sleep(aLong);
            return singer;
        });
    }

    @GetMapping(value = "/one/{id}")
    public Single<Singer> one(@PathVariable Long id) {
        return rx2SingerRepo.findById(id);
    }

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Rx2ReactiveApplication.class)
                .properties(
                        new HashMap<String, Object>() {{
                            put("server.port", "8080");
                            put("spring.jpa.hibernate.ddl-auto", "create-drop");
                        }}
                ).run(args);
        assert (ctx != null);
        logger.info("애플리케이션이 시작됨...");
        System.in.read();
        ctx.close();
    }

    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner clr(WebClient client) {
        return args -> {
            client.get().uri("/all").accept(MediaType.TEXT_EVENT_STREAM)
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Singer.class))
                    .subscribe(System.out::println);
        };
    }
}
*/
