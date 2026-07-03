package org.example.feignapi.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataRequest {
    private String name;
    private int value;
}