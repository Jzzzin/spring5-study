package ch3.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class AnnotatedBeanNaming {

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotated.xml");
        ctx.refresh();


        Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);

        beans.entrySet().stream().forEach(b ->
                System.out.println(
                    "id: " + b.getKey() + "\n 별칭: "
                            + Arrays.toString(ctx.getAliases(b.getKey())) + "\n"
        ));

        ctx.close();
    }
}
