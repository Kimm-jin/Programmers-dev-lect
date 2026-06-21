package report_21_회원관리_FileIO;

public enum PricePlan {
    Lite(10),
    Basic(20),
    Premium(30);

    //    private String desc;
    private final int capacity;
    PricePlan(int capacity){this.capacity=capacity;}
    public int getCapacity(){return capacity;}

    static public PricePlan from(int choice){
        switch (choice){
            case 1: return Lite;
            case 2: return Basic;
            case 3:  return Premium;
            default: return null;
        }
    }
}
