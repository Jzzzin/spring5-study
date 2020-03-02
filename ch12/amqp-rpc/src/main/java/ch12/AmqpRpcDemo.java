package ch12;

import ch12.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpRpcDemo {

/*  xml 구성
    private static Logger logger = LoggerFactory.getLogger(AmqpRpcDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/amqp-rpc-app-context.xml");
        ctx.refresh();

        WeatherService weatherService = ctx.getBean(WeatherService.class);
        logger.info("FL의 기상 예측: " + weatherService.getForecast("FL"));
        logger.info("MA의 기상 예측: " + weatherService.getForecast("MA"));
        logger.info("CA의 기상 예측: " + weatherService.getForecast("CA"));
        ctx.close();
    }
*/

    public static void main(String... args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("FL");
        rabbitTemplate.convertAndSend("MA");
        rabbitTemplate.convertAndSend("CA");
        System.in.read();
        ctx.close();
    }
}
