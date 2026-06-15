public enum B_day_2 {
    COKE("콜라",500),
    SUNDAY("Holiday"),
    MONDAY("Workday"),
    TUSEDAY("Workdat");

    // 열거형에 부연 설명을 넣고 싶을 때
    // 싱글톤
    // 필드
    private String desc;
    private int price;
    // 생성자
    B_day_2(String desc){this.desc=desc;}
    // COKE
    B_day_2(String desc,int price){this.desc=desc;this.price=price;}
    //메서드
    public String getDesc(){return desc;}



}
