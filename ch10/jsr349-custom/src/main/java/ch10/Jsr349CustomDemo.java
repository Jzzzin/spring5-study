package ch10;

import ch10.config.AppConfig;
import ch10.obj.Genre;
import ch10.obj.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349CustomDemo {

    private static Logger logger = LoggerFactory.getLogger(Jsr349CustomDemo.class);

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidationService singerValidationService = ctx.getBean("singerValidationService", SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setGenre(Genre.COUNTRY);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);

        ctx.close();
    }

    private static void validateSinger(Singer singer, SingerValidationService singerValidationService) {
        Set<ConstraintViolation<Singer>> violations = singerValidationService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations) {
        logger.info("위반 건수: " + violations.size());
        violations.forEach(v ->
                logger.info("유효성 검증 에러 발생. 프로퍼티: " + v.getPropertyPath()
                        + ", 값: " + v.getInvalidValue()
                        + ", 에러 메시지: " + v.getMessage()));
    }
}
