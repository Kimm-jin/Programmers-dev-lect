package org.example.springboot.report_36_회원가입_로그인.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {

    private String title;
    private String content;
    private MultipartFile file;
    private boolean fileFlag;
}

