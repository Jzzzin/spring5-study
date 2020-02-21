package ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 https://www.eclipse.org/aspectj/downloads.php 에서 AspectJ 컴파일러를 다운 받아서 설치해야 정상적으로 컴파일됐었는데 다시 하니까 안됨
 build 후 build/libs 에서 aspectj-aspects-1.0-SNAPSHOT.jar 실행 시키면 되긴 함
 build 하면 MessageWrapper 클래스가 생기는데 run 하면 안생기는 걸로 봐서 intellij 문제 같기도 함
  */
public class AspectJDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();
    }
}
