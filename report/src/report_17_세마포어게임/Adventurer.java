package report_17_세마포어게임;

public class Adventurer extends Thread{
    private Dungeon dungeon;
    private String name;
    public Adventurer(Dungeon dungeon, String name){this.dungeon=dungeon;this.name=name;}

    @Override
    public void run() {
        super.run();
        dungeon.enter(name);
    }
}
