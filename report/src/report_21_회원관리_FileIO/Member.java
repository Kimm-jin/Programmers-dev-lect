package report_21_회원관리_FileIO;

public interface Member {
    String getGrade();
    String getName();
    String getEmail();
    String getPhone();
    String getBenefit();

    void update(String name,String email,String phone);

    default String toFileString(){
        return getGrade()+","+getName()+","+getEmail()+","+getPhone();
    }

    default void printInfo(){
        System.out.println("[" + getGrade() + "] " + getName() + " / " + getEmail()
                + " / " + getPhone() + " (혜택: " + getBenefit() + ")");
    }
}
