package report_07_텍스트RPG전투;

public class Character {
    private String name;
    private int hp;
    private int power;

    public Character(String name){
        this.name=name;
        this.hp=30;
        this.power=5;
    }
    public Character(String name, int hp, int power){
        this.name=name;
        this.hp=hp;
        this.power=power;
    }

    public String getName(){return this.name;}
    public void setHp(int hp){this.hp=(hp>0)?hp:0;}
    public int getHp(){return this.hp;}
    public void setPower(int power){this.power=power;}
    public int getPower(){return this.power;}
}
