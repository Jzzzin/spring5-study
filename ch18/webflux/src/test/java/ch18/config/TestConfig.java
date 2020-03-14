package ch18.config;

import ch18.web.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
public class TestConfig {

    @Autowired
    Server server;

    @Bean
    WebTestClient testClient() {
        return WebTestClient.bindToRouterFunction(server.routingFunction())
                .configureClient()
                .baseUrl("http://localhost/singers")
                .build();
    }
}
