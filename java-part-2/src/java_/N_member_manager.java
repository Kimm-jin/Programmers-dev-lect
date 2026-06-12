package java_;

public class N_member_manager {
    private N_member[] members;
    private int memberCount;

    public N_member_manager(int capacity){
        members = new N_member[capacity];
        memberCount = 0;
    }

    public void add(N_member m){
        members[memberCount++]=m;
    }

    public boolean isFull(){
        return memberCount==members.length;
    }

    public boolean existEmail(String email){
        if(findByEmail(email)!=null)return true;
        return false;
    }


    public void printAll(){
        if(memberCount==0){
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        for(int i=0; i<memberCount; i++){
            members[i].printInfo();
        }
    }

    public N_member findByEmail(String email){
        for(int i=0; i<memberCount; i++){
            if(email.equals(members[i].getEmail())){
                return members[i];
            }
        }
        return null;
    }

    public N_member findByName(String name){
        for(int i=0; i<memberCount; i++){
            if(name.equals(members[i].getEmail())){
                return members[i];
            }
        }
        return null;
    }

    public boolean update(String name, String email, String phone, String byEmail){
        N_member member = findByEmail(byEmail);
        if(member==null){return false;}
        //수정 객체에게 위임한다.
        member.update(name,email,phone);
        return true;
    }

    //삭제->뒤 회원들을 한 칸씩 당긴다.
    public boolean delete(String email){
        int index = -1;
        // 조회
        for(int i=0; i<memberCount; i++){
            if (email.equals(members[i].getEmail())){
                index=i;
                break;
            }
        }

        if(index==-1)return false;
        for (int i = index; i < memberCount; i++) {
            members[i]=members[i+1];
        }
        members[memberCount-1]=null;
        memberCount--;
        return true;
    }

    // 캡슐화
    public int getMemberCount(){return memberCount;}
    public int getCapacity(){return members.length;}
}
