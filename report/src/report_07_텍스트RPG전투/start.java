package report_07_텍스트RPG전투;

public class start {
    public static void main(String[] args) {
        //
        Character hero = new Character("용사",100,25);
        Character monsters[]={
                new Character("슬라임"),              // 기본 생성자
                new Character("고블린", 50, 8),       // 커스텀
                new Character("드래곤", 120, 20)
        };
        BattleImpl HeroBattle = new BattleImpl(hero);
        for (Character m : monsters) {
            System.out.println("\n=== 다음 상대: ===");
            BattleImpl MonsterBattle = new BattleImpl(m);
            while(HeroBattle.isAlive() && MonsterBattle.isAlive()){
                HeroBattle.attack(m);
                MonsterBattle.showStatus();
                if(MonsterBattle.isAlive()){
                    MonsterBattle.attack(hero);
                    HeroBattle.showStatus();
                }

            }
            // (Step 4의 전투 반복을 여기서 hero vs m 으로 실행)
            if (!HeroBattle.isAlive()) {
                System.out.println("게임 오버...");
                break;
            }
            System.out.println(m.getName()+"를 처치했습니다.\n");
        }
        if(hero.getHp()!=0) System.out.println("모든 몬스터를 처치했습니다. 남은 체력 : "+hero.getHp());
    }
}
