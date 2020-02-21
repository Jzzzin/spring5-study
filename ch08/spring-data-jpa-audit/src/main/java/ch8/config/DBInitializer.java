package ch8.config;

import ch8.entities.SingerAudit;
import ch8.repos.SingerAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DBInitializer {
    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    SingerAuditRepository singerRepository;

    @PostConstruct
    public void initDB() {
        logger.info("데이터베이스 초기화 시작.");

        SingerAudit singer = new SingerAudit();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, 9, 16)).getTime().getTime()
        ));

        singerRepository.save(singer);

        singer = new SingerAudit();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1945, 2, 30)).getTime().getTime()
        ));

        singerRepository.save(singer);

        singer = new SingerAudit();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1975, 3, 1)).getTime().getTime()
        ));

        singerRepository.save(singer);
        logger.info("데이터베이스 초기화 종료");
    }
}
