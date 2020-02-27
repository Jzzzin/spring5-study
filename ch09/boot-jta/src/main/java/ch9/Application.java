package ch9;

import ch9.entities.Singer;
import ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication(scanBasePackages = "ch9")
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.in.read();
        ctx.close();
    }

    @Autowired
    SingerService singerService;

    @Override
    public void run(String... args) throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, 9, 16)).getTime().getTime()
        ));
        singerService.save(singer);

        long count = singerService.count();
        if (count == 1) {
            logger.info("--> Singer가 성공적으로 저장됐습니다");
        } else {
            logger.error("--> Singer가 저장되지 않았습니다. 구성을 확인하기 바랍니다!!");
        }

        try {
            singerService.save(null);
        } catch (Exception ex) {
            logger.error(ex.getMessage() + "Final count:" + singerService.count());
        }
    }
}
