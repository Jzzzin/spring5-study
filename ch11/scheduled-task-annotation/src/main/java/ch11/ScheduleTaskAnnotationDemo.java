package ch11;

import ch11.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduleTaskAnnotationDemo {
    public static void main(String... args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        System.in.read();

        ctx.close();
    }
}
