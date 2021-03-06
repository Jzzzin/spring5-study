package ch3.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("gigi")
@Lazy
public class TrickyTarget {
    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    public TrickyTarget() {
        System.out.println("Target.constructor()");
    }

    public TrickyTarget(Foo fooOne) {
        System.out.println("Target(Foo) 호출");
    }

    public TrickyTarget(Foo fooOne, Bar bar) {
        System.out.println("Target(Foo, Bar) 호출");
    }

    // comment @Qualifier annotation to cause NoUniqueBeanDefinitionException being thrown at runtime
    @Autowired
    @Qualifier("fooImplOne")
    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("프로퍼티 fooOne 설정");
    }

    // comment @Qualifier annotation to cause NoUniqueBeanDefinitionException being thrown at runtime
    // and make sure for @Primary in FooImpl to be commented as well
    @Autowired
    @Qualifier("fooImplTwo")
    public void setFooTwo(Foo foo) {
        this.fooTwo = foo;
        System.out.println("프로퍼티 fooTwo 설정");
    }

    @Autowired
    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("프로퍼티 bar 설정");
    }

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-05.xml");
        ctx.refresh();

        TrickyTarget t = ctx.getBean(TrickyTarget.class);

        ctx.close();
    }
}
