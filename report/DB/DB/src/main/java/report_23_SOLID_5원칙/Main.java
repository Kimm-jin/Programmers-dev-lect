package report_23_SOLID_5원칙;

public class Main {
    public static void main(String[] args) {

        // SRP
        System.out.println("===== SRP : 단일 책임 =====");
        Srp srp = new Srp();
        Srp.Journal journal= srp.new Journal();
        journal.add("- 오늘은 자바를 배웠다");
        journal.add("- SOLID는 어렵지만 재밌다");
        Srp.JournalSaver journalSaver = srp.new JournalSaver();
        journalSaver.print(journal);

        // OCP
        System.out.println("===== OCP : 개방-폐쇄 =====");
        Ocp ocp = new Ocp();
        Ocp.DiscountPolicy basic = ocp.new BasicDiscount();
        Ocp.DiscountPolicy gold = ocp.new GoldDiscount();
        Ocp.DiscountPolicy vip = ocp.new VipDiscount();

        int price = 10000;
        System.out.println("Basic: " + basic.discount(price));
        System.out.println("Gold: " + gold.discount(price));
        System.out.println("Vip: " + vip.discount(price)+"\n");


        // LSP
        System.out.println("===== LSP: 리스코프 치환 =====");
        Lsp lsp = new Lsp();
        Lsp.Bird[] birds = {
                lsp.new Sparrow(),
                lsp.new Penguin()
        };
        for (Lsp.Bird bird : birds) {
            bird.eat();
        }
        Lsp.FlyingBird flyingBird = lsp.new Sparrow();
        flyingBird.fly();

        Lsp.Penguin penguin = lsp.new Penguin();
        penguin.swim();
        System.out.println();


        // ISP
        System.out.println("===== ISP: 인터페이스 분리 =====");
        Isp isp = new Isp();
        Isp.Printer simplePrinter = isp.new SimplerPrinter();
        simplePrinter.print();

        Isp.SmartMachine smartScanner = isp.new SmartMachine();
        smartScanner.scan();
        smartScanner.print();
        System.out.println();

        Isp.AllInOneMachine allInOneMachine = isp.new AllInOneMachine();
        allInOneMachine.fax();
        allInOneMachine.print();
        allInOneMachine.scan();


        // DIP
        System.out.println("===== DIP: 의존관계 역전 =====");
        Dip dip = new Dip();
        Dip.NotificationService email =
                dip.new NotificationService(dip.new EmailSender());
        email.notify(" 주문이 완료되었습니다");

        Dip.NotificationService sms =
                dip.new NotificationService(dip.new SmsSender());
        sms.notify(" 주문이 완료되었습니다.");

        Dip.MockSender mockSender = dip.new MockSender();

        Dip.NotificationService service =
                dip.new NotificationService(mockSender);

        service.notify("주문이 완료되었습니다");
        service.notify("배송이 시작되었습니다");

        System.out.println(mockSender.getSentMsg());
    }
}
