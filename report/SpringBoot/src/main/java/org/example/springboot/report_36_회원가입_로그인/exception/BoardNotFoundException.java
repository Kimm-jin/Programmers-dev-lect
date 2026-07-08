package org.example.springboot.report_36_회원가입_로그인.exception;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(String message){
        super(message);
    }
}
