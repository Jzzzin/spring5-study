package ch10;

import ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServDemo {

    private static Logger logger = LoggerFactory.getLogger(ConvFormatServDemo.class);

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("가수 정보: " + john);
        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        logger.info("가수의 생일: " + conversionService.convert(john.getBirthDate(), String.class));

        ctx.close();
    }
}
