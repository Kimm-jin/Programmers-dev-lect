package report_15_달팽이경주;

public class Main {
    public static void main(String[] args) {
        Race race = new Race();
        Snail s1 = new Snail("aa",race);
        Snail s2 = new Snail("bb",race);
        Snail s3 = new Snail("cc",race);
//
        s1.start();
        s2.start();
        s3.start();

    }
}
