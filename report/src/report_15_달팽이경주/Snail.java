package report_15_달팽이경주;

public class Snail extends Thread {
    private String name;
    private int position;
    private static final int FINISH = 30;
    private Race race;

    public Snail(String name, Race race) {
        this.name = name; this.race=race;
    }

    @Override
    public void run() {
        super.run();
        StringBuilder sb = new StringBuilder();

        while (position < FINISH && !race.isOver()) {
            position += (int) (Math.random() * 3) + 1;
//            System.out.println(name + " 위치 : " + position);
            printProgress();
            if(position>=FINISH)race.finish(name);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

//        System.out.println(name + " 결승선 도착");

    }

    private void printProgress(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<position; i++)sb.append("■");
        System.out.println(name + ": " + sb);
    }
}
