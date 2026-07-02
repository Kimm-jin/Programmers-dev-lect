package org.example.springboot.report_33_AOP.service;

public class MemberServiceImpl implements MemberService{
    @Override
    public String register(String item) {
        sleep(80);
        return "회원가입 완료 : " + item;
    }

    @Override
    public String getMember(String name) {
        sleep(80);
        return "getMember : " + name;
    }


    @Override
    public String getFailMember(String name) {
        sleep(50);
        throw new IllegalArgumentException("회원 조회 실패: " + name);
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException ignored) {}
    }

}
