package report_23_SOLID_5원칙;

public class Isp {

    interface Printer{void print();}
    interface Scanner{void scan();}
    interface Faxer{void fax();}
    class SimplerPrinter implements Printer{

        @Override
        public void print() {
            System.out.println("인쇄");
        }
    }

    class SmartMachine implements Printer,Scanner{

        @Override
        public void print() {
            System.out.println("신형 : 인쇄");
        }

        @Override
        public void scan() {
            System.out.println("신형 : 스캔");
        }
    }
}
