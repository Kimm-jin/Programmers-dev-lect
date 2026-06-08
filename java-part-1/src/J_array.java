/* 배열
// 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것
// 변수와 달리 배열은 각 저장공간이 연속적으로 배치되어 있다.
// int num, COKE, CIDER......
// &1, &4, &9 .......
// &1, &5, &9 ....... -> 배열은 연속적으로 배치되어 있다.
*/

// * 배열의 생성
// type[] 변수이름 = new type[length];

// 배열의 첫번째 주소를 알면 나머지 주소를 알 수 있다.

public class J_array {

    public static void exam1(){
        int scores[] = new int[3];

        scores[0] = 10;
        scores[1] = 20;
        scores[2] = 30;

        System.out.println(scores);
        System.out.println(scores[0]);
        System.out.println(scores[1]);
        System.out.println(scores[2]);
    }

    public static void exam2(){
        int scores[] = {0,1,2};
        System.out.println("len : " + scores.length);
    }
    public static void exam3(){
        int score[] = new int[3];
        for (int i = 0; i < score.length; i++) {
            score[i]=(i+1)*10;
        }
        for(int v : score) System.out.println(v);
    }
    public static void exam4(){
        char[] ch = {'a','b','c','d','e'};
        for(char c : ch) System.out.println(c);
    }
    public static void practice1(){ // exam4 역순 출력
        char[] ch = {'a','b','c','d','e'};
        for (int i = ch.length-1; i>=0; i--) {
            System.out.println(ch[i]);
        }
    }
    public static void exam5(){

    }
    public static void exam6(){
        int scores[]= {10,20,30};
        int num = 20;
        //scores를 인덱스별로 찍기
        for (int i = 0; i < scores.length ; i++) {
            System.out.println(scores[i]);
        }
        exam6_sub(scores, num); // call by Value
        System.out.println("==========");
        System.out.println(num);
        num = exam6_sub2(scores,num);
        System.out.println(num);

        // call by Value, call by Reference
        // call by Reference는 주소에 접근해 주소에 저장된 값을 변경 또는 참조할 수 있음.
        // call by Value는 주소에 있는 값을 호출하는것이라 값을 참조할순 있으나 변경은 할 수 없음
    }

    // int [] => new -> 참조형 &123 => call by Reference
    public static void exam6_sub(int scores[], int num){
        num = 90;
        scores[1] = 90;

    }
    public static int exam6_sub2(int scores[], int num){ // call by Reference
        scores[1] = 90;
        num = 90;
        return num;
    }
    public static void main(String[] args) {
        //exam1();
        //exam2();
        //exam3();
        //practice1();
        //exam4();
        exam6();
    }
}
