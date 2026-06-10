package report_07_텍스트RPG전투;

public class BattleImpl implements BattleService{
    private Character character;
    public BattleImpl(Character character){this.character=character;}
    public void showStatus(){
        System.out.println(character.getName() + " (HP: " + character.getHp() + ")");
    } // 체력 확인
    public boolean isAlive(){return character.getHp()>0;}// 0보다 큰지
    public void takeDamage(int dmg){
        character.setHp(character.getHp()-dmg);
    } // HP깍기 0이하면 0으로
    public void attack(Character target){
        // ?? 내 데미지를 넘겨줘야함
        BattleImpl targetbattle = new BattleImpl(target);
        System.out.println(character.getName() + "의 공격! " + target.getName() + "에게 " + character.getPower() + " 피해");
        targetbattle.takeDamage(character.getPower());
    } // 상대에게 자기 공격력만큼 피해를 준다.
}
