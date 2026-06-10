package report_06_반려동물키우기;

public class PetImpl implements PetService {
    private PetInfo pet;
    public PetImpl(PetInfo pet){this.pet=pet;}

    public void showStatus(){// 펫 상태 출력
        System.out.println("[" + pet.getName() + "] 포만감: " + pet.getFullness() + " / 행복: " + pet.getHappiness());
    }
    public void feed(){// 펫 먹이주기
        int nextFullness = pet.getFullness()+20;
        int nextHappiness = pet.getHappiness()+5;

        pet.setFullness(nextFullness);
        pet.setHappiness(nextHappiness);
        System.out.println(pet.getName() + "에게 먹이를 줬어요! 냠냠");
    }
    public void play(){//펫 놀아주기
        int nextHappiness = pet.getHappiness()+20;
        int nextFullness = pet.getFullness()-10;

        pet.setHappiness(nextHappiness);
        pet.setFullness(nextFullness);
        System.out.println(pet.getName()+"와(과) 신나게 놀았어요");
    }
    /* 펫정보를 메서드 인자로 받음
    public void showStatus(PetInfo pet){// 펫 상태 출력
        System.out.println("[" + pet.getName() + "] 포만감: " + pet.getFullness() + " / 행복: " + pet.getHappiness());
    }
    public void feed(PetInfo pet){// 펫 먹이주기
        pet.setFullness(pet.getFullness()+20);
        pet.setHappiness(pet.getHappiness()+5);
        System.out.println(pet.getName() + "에게 먹이를 줬어요! 냠냠");
    }
    public void play(PetInfo pet){//펫 놀아주기

        pet.setHappiness(pet.getHappiness()+20);
        pet.setFullness(pet.getFullness()-10);
        System.out.println(pet.getName()+"와(과) 신나게 놀았어요");
    }

     */
}
