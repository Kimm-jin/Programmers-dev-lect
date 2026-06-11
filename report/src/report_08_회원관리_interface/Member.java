package report_08_회원관리_interface;

public interface Member {
    String getName();
    String getEmail();
    String getPhone();
    String getGrade();      // 등급
    String getBenefit();    // 혜택
    void  update(String name, String email, String phone);

    default void printInfo(){
        System.out.println("[" + getGrade() + "] " + getName() + " / " + getEmail()
                + " / " + getPhone() + " (혜택: " + getBenefit() + ")");
    }
}
