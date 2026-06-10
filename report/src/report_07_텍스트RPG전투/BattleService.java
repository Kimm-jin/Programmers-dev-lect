package report_07_텍스트RPG전투;

public interface BattleService {
    public void showStatus(); // 체력 확인
    public boolean isAlive(); // 0보다 큰지
    public void takeDamage(int dmg); // HP깍기 0이하면 0으로
    public void attack(Character target); // 상대에게 자기 공격력만큼 피해를 준다.
}
