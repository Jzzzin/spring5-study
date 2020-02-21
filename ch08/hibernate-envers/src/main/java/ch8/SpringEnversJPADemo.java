package ch8;

import ch8.config.EnversConfig;
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

public class SpringEnversJPADemo {
    private static Logger logger = LoggerFactory.getLogger(SpringEnversJPADemo.class);

    public static void main(String... args) {
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/app-context-annotation.xml");
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EnversConfig.class);

        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        logger.info("새 가수 추가");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()
        ));
        singerAuditService.save(singer);
        listSingers(singerAuditService.findAll());

        logger.info("가수정보 수정");
        singer.setFirstName("Riley B.");
        singerAuditService.save(singer);
        listSingers(singerAuditService.findAll());

        SingerAudit oldSinger = singerAuditService.findAuditByRevision(4L, 1);
        logger.info("");
        logger.info("ID가 4이고 개정번호 1인 이전 가수정보: " + oldSinger);
        logger.info("");
        oldSinger = singerAuditService.findAuditByRevision(4L, 2);
        logger.info("");
        logger.info("ID가 4이고 개정번호 2인 이전 가수정보: " + oldSinger);
        logger.info("");

        ctx.close();
    }

    private static void listSingers(List<SingerAudit> singers) {
        logger.info("");
        logger.info(" ---- 가수 목록:");
        singers.forEach(s -> logger.info(s.toString()));
    }
}
