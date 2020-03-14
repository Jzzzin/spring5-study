package ch18;

import ch18.web.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"ch18"})
@Configuration
public class ServerConfig {

    @Bean
    public Server server() {
        return new Server();
    }
}
