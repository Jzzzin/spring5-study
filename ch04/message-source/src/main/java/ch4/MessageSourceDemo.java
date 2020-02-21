package ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;


public class MessageSourceDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale korean = new Locale("ko", "KR");

        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, korean));

        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, korean));

        ctx.close();
    }
}
