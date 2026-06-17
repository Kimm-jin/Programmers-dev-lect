package report_17_세마포어게임;

public class Main {
    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(2);
        String name[] = {"전사","마법사","궁수","도적","성직자"};
        for(String n : name) new Adventurer(dungeon, n).start();
    }
}
