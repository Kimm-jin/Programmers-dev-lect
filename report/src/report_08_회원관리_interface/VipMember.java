package report_08_회원관리_interface;

public class VipMember implements Member {
    private String name, email, phone;     // ← NormalMember와 똑같음 (중복!)

    public VipMember(String name, String email, String phone) {  // ← 똑같음 (중복!)
        this.name = name; this.email = email; this.phone = phone;
    }

    @Override public String getName()  { return name; }   // ← 똑같음 (중복!)
    @Override public String getEmail() { return email; }
    @Override public String getPhone() { return phone; }

    @Override public String getGrade()   { return "VIP"; }              // 여기만 다름
    @Override public String getBenefit() { return "10% 할인 + 무료배송"; } // 여기만 다름

    @Override public void update(String name, String email, String phone) { // ← 똑같음 (중복!)
        this.name = name; this.email = email; this.phone = phone;
    }
}
