package org.example.springboot.report_35_외부api호출.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Body {
    private Items items;
    private int pageNo;
    private int numOfRows;
    private int totalCount;
}
/*
{"response":{
        "header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},
        "body":{
                "items":{"item":[
                        {"baseDate":"20241008","baseTime":"1400","category":"T1H","nx":60,"ny":127,"obsrValue":"18.5"}
                        ]},
        "pageNo":1,"numOfRows":10,"totalCount":8
        }}}
*/
