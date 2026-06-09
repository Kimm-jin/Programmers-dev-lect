package report_05_숫자업다운게임;





public interface Updown {
    public void initGame();//  1. 정답 정하기
    public void inpurtGuess();//  2. 숫자 입력
    public void HintMessage();//  3. 힌트 주기
    public void isCorrect();//  4. 반복
    public void Count();//  5. 시도 횟수

}
