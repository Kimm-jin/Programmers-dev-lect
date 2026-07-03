package org.example.springboot.report_35_외부api호출.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Header {
    private String resultCode;
    private String resultMsg;
}
