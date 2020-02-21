package ch5;

public class Documentarist {
    // 상속받은 클래스에서 접근할 수 있도록 protected 로 처리함
    protected GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
