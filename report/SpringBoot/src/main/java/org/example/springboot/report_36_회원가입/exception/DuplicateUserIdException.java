package org.example.springboot.report_36_회원가입.exception;

public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException(String message){super(message);}
}
