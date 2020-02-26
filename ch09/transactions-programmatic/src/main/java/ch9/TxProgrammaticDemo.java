package ch9;

import ch9.config.DataJpaConfig;
import ch9.config.ServiceConfig;
import ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfig.class, DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        System.out.println("Singer 수: " + singerService.countAll());

        ctx.close();
    }
}
