package report_15_달팽이경주;

public class Race {
    private boolean gameOver;

    public synchronized boolean isOver(){return gameOver;}

    public synchronized void finish(String name){
        if(!gameOver){
            gameOver=true;
            System.out.printf("\n | 우승 : %s |\n",name);
        }
    }

}
