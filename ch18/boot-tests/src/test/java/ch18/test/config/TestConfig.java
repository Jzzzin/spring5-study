package ch18.test.config;

import ch18.FluxGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    FluxGenerator generator() {
        return new FluxGenerator();
    }
}
