package ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "ch12")
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        assert (ctx != null);
        logger.info("Application Started...");
        System.in.read();
        ctx.close();
    }
}
