package report_22_회원관리_DB;

public interface Member {
    public String getName();
    public String getEmail();
    public String getPhone();
    public String getGrade();
    public String getBenefit();

    void update(String name,String email,String phone);

    default String toFileString(){
        return getGrade()+","+getName()+","+getEmail()+","+getPhone();
    }

    default void printInfo(){
        System.out.println("[" + getGrade() + "] " + getName() + " / " + getEmail()
                + " / " + getPhone() + " (혜택: " + getBenefit() + ")");
    }
}
