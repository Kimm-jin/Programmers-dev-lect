package org.example.springboot.report_33_AOP.service;

public interface MemberService {
    String register(String item);
    String getMember(String name);
    String getFailMember(String name);
}
