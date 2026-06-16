package report_14_회원관리_Last;

public interface Member {
    // name email phone grade benefit
    String getName();
    String getEmail();
    String getPhone();
    String getGrade();
    String getBenefit();

    void update(String name,String email,String phone);

    default void printInfo(){
        System.out.println("[" + getGrade() + "] " + getName() + " / " + getEmail()
                + " / " + getPhone() + " (혜택: " + getBenefit() + ")");
    }





}
