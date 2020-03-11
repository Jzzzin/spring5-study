package ch18;

import ch18.config.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FileWatcherDemo {

    private static Logger logger = LoggerFactory.getLogger(FileWatcherDemo.class);

    public static void main(String... args) throws Exception {
        /* xml 구성
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/integration-config.xml");
        */
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(IntegrationConfig.class);
        assert (ctx != null);
        logger.info("애플리케이션이 시작됨...");
        System.in.read();
        ctx.close();
    }
}
