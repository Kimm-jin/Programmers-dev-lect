package org.example.feignclient.service;

import lombok.RequiredArgsConstructor;
import org.example.feignclient.client.ExampleClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EaxmpleService {

    // final을 쓰면 반드시 주입을 받아야 한다. 강제 주입
    private final ExampleClient exampleClient;

    public String getDataById(Long id){
        return exampleClient.getData(id);
    }
}
