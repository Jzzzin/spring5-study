package ch3.annotated;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
@Scope("prototype")
public class Singer {
    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

    public void sing() {
        // 출력을 오염시키지 않도록 주석 처리함
//        System.out.println(lyric);
    }
}
