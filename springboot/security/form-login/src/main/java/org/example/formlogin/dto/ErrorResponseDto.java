package org.example.formlogin.dto;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@AllArgsConstructor
public class ErrorResponseDto {

    private int status;
    private String message;
}
