package report_05_숫자업다운게임;

//  1. 정답 정하기
//  2. 숫자 입력
//  3. 힌트 주기
//  4. 반복
//  5. 시도 횟수
public class start {
    public static void main(String[] args) {
        updownImpl ud = new updownImpl();
        ud.initGame();
        ud.isCorrect();
        ud.Count();
    }
}
/*
O    Random으로 1~100 정답 생성
O    Scanner로 숫자 입력
O    if-else로 UP / DOWN / 정답 판정
O    while + break로 맞힐 때까지 반복
O    시도 횟수 출력
O    테스트용 정답 출력 삭제
 */