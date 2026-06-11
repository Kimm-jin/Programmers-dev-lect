package report_08_회원관리_abstract;

public abstract class Member {
    protected String name, email, phone;

    public Member(String name, String email, String phone){
        this.name=name; this.email=email; this.phone=phone;
    }

    public String getName() {return this.name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public abstract String getGrade();      //  등급
    public abstract String getBenefit();    //  혜택

    // 추상 메서드를 부모의 구현 메서드가 활용한다
    public void printInfo() {
        System.out.println("[" + getGrade() + "] " + name + " / " + email
                + " / " + phone + " (혜택: " + getBenefit() + ")");
    }
    public void update(String name, String email, String phone){
        this.name=name; this.email=email; this.phone=phone;
    }
}
