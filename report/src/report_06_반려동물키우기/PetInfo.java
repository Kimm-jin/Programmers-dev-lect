package report_06_반려동물키우기;

public class PetInfo {
    private String name;
    private int fullness;
    private int happiness;

    public PetInfo(String name) {
        this.name = name;
        this.fullness = 50;
        this.happiness = 50;
    }

    public String getName(){
        return name;
    }
    public int getFullness(){
        return fullness;
    }
    public void setFullness(int fullness){
        if (fullness > 100) this.fullness = 100;
        else if (fullness < 0) this.fullness = 0;
        else this.fullness = fullness;
    }

    public int getHappiness() {
        return happiness;
    }
    public void setHappiness(int happiness){
        if (happiness > 100) this.happiness = 100;
        else if (happiness < 0) this.happiness = 0;
        else this.happiness = happiness;
    }
}

