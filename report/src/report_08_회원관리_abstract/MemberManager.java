package report_08_회원관리_abstract;

import java.lang.classfile.FieldBuilder;
import java.util.Scanner;

public class MemberManager{
    private Member[] members;
    private int memberCnt;

    //회원추가
    public MemberManager(int capacity){
        members = new Member[capacity];
        memberCnt=0;
    }

    public boolean isFull(){ //정원 chk
        return memberCnt==members.length;
    }

    public boolean existsEmail(String email){ // 이메일 중복확인
        for (int i = 0; i < memberCnt; i++) {
            if(members[i].getEmail().equals(email))return true;
        }
        return false;
    }

    public void add(Member m){ // 맴버추가
        members[memberCnt] = m;
        memberCnt++;
    }

    public int getCount(){return memberCnt;}
    public int getCapacity(){return members.length;}

    // 회원조회
    public Member findByEmail(String email){
        for(int i=0; i<memberCnt; i++){
            if (members[i].getEmail().equals(email))return members[i];
        }
        return null;
    }
    public Member findByName(String name){
        for(int i=0; i<memberCnt; i++){
            if (members[i].getName().equals(name))return members[i];
        }
        return null;
    }

    public void printAll(){
        for (int i = 0; i <memberCnt ; i++) {
            members[i].printInfo();
        }
    }
    // 수정 삭제
    public boolean update(String email, String name, String newEmail, String phone){
        Member m = findByEmail((email));
        if(m==null)return false;
        m.update(name, newEmail, phone);
        return true;
    }

    public boolean delete(String email) {
        int idx = -1;
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) { idx = i; break; }
        }
        if (idx == -1) return false;

        for (int i = idx; i < memberCnt - 1; i++) {
            members[i] = members[i + 1];   // 객체 하나만 당기면 됨 (이름·메일·연락처 따로 X)
        }
        members[memberCnt - 1] = null;
        memberCnt--;
        return true;
    }


}
