package report_07_텍스트RPG전투;

public class BattleImpl implements BattleService {
    private Character character;
    public BattleImpl(Character character){this.character=character;}

    public void showStauts(){
        System.out.println(character.getName() + " (HP: " + character.getHp() + ")");
    }
    public boolean isAlive(){
        return character.getHp()>0;
    }   // HP가 0보다 큰지 반환.
    public void takeDamage(int dmg){
        int nextHP = character.getHp() - dmg;
        if(nextHP<0)nextHP=0;
        character.setHp(nextHP);
    }    //  HP를 0이하로 안내려가게
    public void attack(Character target){ // 내가 대상에게 나의공격력 만큼
        System.out.println(character.getName() + "의 공격! " + target.getName() + "에게 " + character.getPower() + " 피해");
        takeDamage(character.getPower());
    }   //  상대에게 자기 공격력만큼 피해를 준다.
}
