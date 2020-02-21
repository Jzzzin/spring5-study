package ch4;

import ch2.decoupled.MessageRenderer;
import ch4.mixed.AppConfigFive;
import ch4.multiple.AppConfigThree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigExampleTwo {
    public static void main(String... args) {
        ApplicationContext ctx = new
                //AnnotationConfigApplicationContext(AppConfigTwo.class);
                //AnnotationConfigApplicationContext(AppConfigThree.class);
                AnnotationConfigApplicationContext(AppConfigFive.class);

        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);

        renderer.render();
    }
}
