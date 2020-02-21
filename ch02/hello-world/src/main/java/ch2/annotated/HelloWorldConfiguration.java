package ch2.annotated;

import ch2.decoupled.HelloWorldMessageProvider;
import ch2.decoupled.MessageProvider;
import ch2.decoupled.MessageRenderer;
import ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
    // <bean id="provider" class=".."/> 구성과 동등함
    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    // <bean id="renderer" class=".."/> 구성과 동등함
    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
