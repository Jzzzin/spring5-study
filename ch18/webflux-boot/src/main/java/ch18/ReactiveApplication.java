/*
package ch18;

import ch18.entities.Singer;
import ch18.repos.ReactiveSingerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.HashMap;

// SingerApplication 을 모두 주석 처리 한 후 실행한다.
@SpringBootApplication
@RestController
public class ReactiveApplication {

    private static Logger logger = LoggerFactory.getLogger(ReactiveApplication.class);

    @Autowired
    ReactiveSingerRepo reactiveSingerRepo;

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Singer> oneByOne() {
        Flux<Singer> singers = reactiveSingerRepo.findAll();
        Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
        return Flux.zip(singers, periodFlux).map(Tuple2::getT1);
    }

    @GetMapping(value = "/one/{id}")
    public Mono<Singer> one(@PathVariable Long id) {
        return reactiveSingerRepo.findById(id);
    }

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx =
//                SpringApplication.run(ReactiveApplication.class, args);
                new SpringApplicationBuilder(ReactiveApplication.class)
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
