package ch18;

import ch18.web.SingerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

// ReactiveApplication, Rx2ReactiveApplication 을 모두 주석 처리한 다음 실행한다
@SpringBootApplication
public class SingerApplication {

    private static Logger logger = LoggerFactory.getLogger(SingerApplication.class);

    @Autowired
    SingerHandler singerHandler;

    public RouterFunction<ServerResponse> routingFunction() {
        return route(GET("/test"), serverRequest -> ok().body(fromObject("works!")))
                .andRoute(GET("/singers"), singerHandler.list)
//                .andRoute(GET("/singers"), singerHandler::list)
                .andRoute(GET("/singers/{id}"), singerHandler::show)
                .andRoute(POST("/singers"), singerHandler.save)
//                .andRoute(POST("/singers"), singerHandler::save)
                .filter((request, next) -> {
                    logger.info("Before handler invocation: " + request.path());
                    return next.handle(request);
                });
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws Exception {
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routingFunction());
        ServletRegistrationBean registrationBean = new ServletRegistrationBean<>
                (new ServletHttpHandlerAdapter(httpHandler), "/");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        return registrationBean;
    }

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx =
//                SpringApplication.run(SingerApplication.class, args);
                new SpringApplicationBuilder(SingerApplication.class)
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
}
