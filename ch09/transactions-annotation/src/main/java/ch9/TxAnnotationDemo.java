package ch9;

import ch9.config.DataJpaConfig;
import ch9.config.ServicesConfig;
import ch9.entities.Singer;
import ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class TxAnnotationDemo {

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);

        SingerService singerService = ctx.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        singers.forEach(s -> System.out.println(s));

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singerService.save(singer);
        System.out.println("Singer가 성공적으로 저장됐습니다: " + singer);

        System.out.println("Singer 수: " + singerService.countAll());

        ctx.close();
    }
}
