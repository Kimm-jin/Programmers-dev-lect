package org.example.feignapi.dao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {
    private Long id;
    private String name;
    private int value;
}