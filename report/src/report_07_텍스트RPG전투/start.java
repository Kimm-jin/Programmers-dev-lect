package report_07_텍스트RPG전투;

public class start {
    public static void main(String[] args) {
        Character hero = new Character("용사", 100, 25);
        BattleImpl heroBattle = new BattleImpl(hero);

        Character[] monsters = {
                new Character("슬라임", 30, 5),
                new Character("고블린", 50, 8),
                new Character("드래곤", 120, 20)
        };

        for (Character monster : monsters) {
            BattleImpl monsterBattle = new BattleImpl(monster);
            System.out.println("\n=== 전투 시작: " + monster.getName() + " 등장! ===");

            // 턴제 전투 로직
            while (heroBattle.isAlive() && monsterBattle.isAlive()) {
                heroBattle.attack(monster); // 용사가 몬스터 공격
                if (monsterBattle.isAlive()) {
                    monsterBattle.attack(hero); // 몬스터가 용사 공격
                    System.out.println(heroBattle.showStauts());
                }
            }

            if (!heroBattle.isAlive()) {
                System.out.println("게임 오버...");
                break;
            } else {
                System.out.println(monster.getName() + " 처치 성공!");
            }
        }
    }
}
