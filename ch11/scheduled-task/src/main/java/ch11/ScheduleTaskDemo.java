package ch11;

import ch11.config.AppConfig;
import ch11.services.CarService;
import ch11.services.CarServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduleTaskDemo {

    final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    public static void main(String... args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

/*
        CarService carService = ctx.getBean("carService", CarService.class);

        while (!carService.isDone()) {
            logger.info("스케줄된 잡이 끝나기를 기다림 ...");
            Thread.sleep(250);
        }
*/
        System.in.read();
        ctx.close();
    }
}
