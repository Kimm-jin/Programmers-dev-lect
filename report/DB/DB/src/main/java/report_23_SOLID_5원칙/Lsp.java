package report_23_SOLID_5원칙;

public class Lsp {
    class Bird{
        void eat(){
            System.out.println("먹다.");
        }
    }
    class FlyingBird extends Bird{
        void fly(){
            System.out.println("날다");
        }

    }
    class Sparrow extends FlyingBird{
    }

    class Penguin extends Bird{
        void swim(){
            System.out.println("수영");
        }
    }
}
