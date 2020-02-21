package ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

public class DestructiveBeanWithJSR250 {
    private File file;
    private String filePath;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("빈을 초기화 합니다.");

        if (filePath == null) {
            throw new IllegalArgumentException(
                    DestructiveBeanWithJSR250.class + "에 filePath 프로퍼티를 지정해야 합니다."
            );
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("파일 존재 여부: " + file.exists());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("빈을 소멸합니다.");

        if (!file.delete()) {
            System.out.println("에러: 파일 삭제에 실패했습니다.");
        }

        System.out.println("파일 존재 여부: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        DestructiveBeanWithJSR250 bean = (DestructiveBeanWithJSR250) ctx.getBean("destructiveBean");

        System.out.println("destroy() 호출 시작");
        ctx.destroy();
        System.out.println("destroy() 호출 종료");
    }
}
