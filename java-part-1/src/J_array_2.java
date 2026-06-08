public class J_array_2 {
    public static void exam1(){
        int scores[][] = new int[3][2];
        int val=0;
        for (int i = 0; i < scores.length ; i++) {
            for (int j = 0; j <scores[i].length ; j++) {
                val+=10;
                scores[i][j]=val;
            }
        }
        for (int i = 0; i < scores.length ; i++) {
            for (int j = 0; j <scores[i].length ; j++) {
                System.out.printf("%d ",scores[i][j]);
            }
            System.out.printf("\n");
        }
    }

    public static void exam2(){
        int[][] scores = {{1,2},{3,4}};
        for (int i = 0; i < scores.length ; i++) {
            for (int j = 0; j <scores[i].length ; j++) {
                System.out.printf("%d ",scores[i][j]);
            }
            System.out.printf("\n");
        }
    }

    //* 가변 배열
    // 2차원 이상의 다차원 배열을 생성할 때 전체 배열 차수 중 마지막 차수의 길이를 지정하지 않고,
    // 추후에 각기 다른 길이의 배열을 생성함으로써
    public static void exam3(){
        int scores[][] = new int[2][];

        scores[0] = new int[]{1,2}; // 초기화된 0행의 열을 메모리할당과 값을 대입하는 과정
        scores[1] = new int[3]; // 1행에 3만큼의 메모리를 할당
        //1행 값 대입
        scores[1][0] = 3;
        scores[1][1] = 4;
        scores[1][2] = 5;
    }

    public static void exam4(){

    }


    public static void main(String[] args) {
        exam1();
    }
}
