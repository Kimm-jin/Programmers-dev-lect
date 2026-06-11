package report_08_회원관리_interface;

public class NormalMember implements Member {
    private String name, email, phone;

    public NormalMember(String name, String email, String phone) {
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public String getGrade() {
        return "일반";
    }

    @Override
    public String getBenefit() {
        return "기본 서비스";
    }

    @Override
    public void update(String name, String email, String phone) {
        this.name=name;
        this.email=email;
        this.phone=phone;
    }
}
