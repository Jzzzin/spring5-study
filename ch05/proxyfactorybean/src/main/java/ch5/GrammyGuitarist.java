package ch5;

import ch2.common.Guitar;

// implemets Singer 하는 경우 ProxyFactoryBean 에서 생성한 프록시를 사용하지 못하는 것 같음
public class GrammyGuitarist {
    public void sing() {
        System.out.println("sing : Gravity is working against me\n" +
                "And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
