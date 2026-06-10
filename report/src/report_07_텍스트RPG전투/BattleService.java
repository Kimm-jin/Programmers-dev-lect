package report_07_텍스트RPG전투;

public interface BattleService {
    public void showStauts();
    public boolean isAlive();   // HP가 0보다 큰지 반환.
    public void takeDamage(int dmg);    //  HP를 0이하로 안내려가게
    public void attack(Character target);   //  상대에게 자기 공격력만큼 피해를 준다.
}
