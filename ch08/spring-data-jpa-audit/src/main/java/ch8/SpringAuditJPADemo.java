package ch8;

import ch8.config.AuditConfig;
import ch8.entities.SingerAudit;
import ch8.services.SingerAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringAuditJPADemo {
    private static Logger logger = LoggerFactory.getLogger(SpringAuditJPADemo.class);

    public static void main(String... args) {

//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/app-context-annotation.xml");
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AuditConfig.class);

        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        List<SingerAudit> singers = singerAuditService.findAll();
        listSingers(singers);

        logger.info("새 가수 등록");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()
        ));
        singerAuditService.save(singer);

        singers = singerAuditService.findAll();
        listSingers(singers);

        singer = singerAuditService.findById(4L);
        logger.info("");
        logger.info("ID가 4인 가수: " + singer);
        logger.info("");

        logger.info("가수 정보 수정");
        singer.setFirstName("Riley B.");
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSingers(singers);

        ctx.close();
    }

    private static void listSingers(List<SingerAudit> singerAudits) {
        logger.info("");
        logger.info(" ---- 가수 목록 (상세정보 생략) :");
        singerAudits.forEach(audit -> logger.info(audit.toString()));
    }
}
