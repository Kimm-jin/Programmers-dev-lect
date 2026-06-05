package report_02_가계부;

// 가계부가 "할 수 있는 기능"을 선언만 한다 (메서드 이름 목록)
public interface AccountBook {
    void addAccount();  //  1. 내역 추가
    void showAccount(); //  2. 내역 조회
    void deleteAll();   //  3. 전체 삭제
    void deleteItem();  //  4. 내역 삭제
}
