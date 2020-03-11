package ch18;

public class Singer {
    private String firstName;
    private String lastName;
    private String song;

    public Singer() {

    }

    public Singer(String firstName, String lastName, String song) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.song = song;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "이름: " + firstName + ", 성: " + lastName + ", 노래: " + song;
    }
}
