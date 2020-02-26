package ch9;

import ch9.entities.Singer;
import ch9.services.SingerService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TxDeclarativeDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/tx-declarative-app-context.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);

        // findAll() 테스트하기
        List<Singer> singers = singerService.findAll();
        singers.forEach(s -> System.out.println(s));

        // save() 테스트하기
        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singerService.save(singer);
        System.out.println("성공적으로 Singer를 저장했습니다: " + singer);

        // countAll() 테스트하기
        System.out.println("Singer 수: " + singerService.countAll());
        ctx.close();
    }
}
