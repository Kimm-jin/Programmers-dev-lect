package com.example.spring.basicboard.exception;

public class BoardAccessDeniedException extends RuntimeException {
    public BoardAccessDeniedException(String message) {
        super(message);
    }
}
