package report_06_반려동물키우기;

public interface PetService {
    public void showStatus();   // 펫 상태 출력
    public void feed(); // 펫 먹이주기
    public void play(); //펫 놀아주기
    // PetInfo pet으로 메서드가 펫 정보를 받으면 여러 펫 관리도 가능
}
