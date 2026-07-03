package org.example.feignclient.service;

import lombok.RequiredArgsConstructor;
import org.example.feignclient.client.ExampleClient;
import org.example.feignclient.dto.DataRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    // final을 쓰면 반드시 주입을 받아야 한다. 강제 주입
    private final ExampleClient exampleClient;

    public String getDataById(Long id){
        return exampleClient.getData(id);
    }

    public String createData(String name, int value) {
        return exampleClient.createData(
                DataRequest.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String updateData(Long id, String name, int value) {
        return exampleClient.updateData(
                id,
                DataRequest.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String deleteData(Long id) {
        return exampleClient.deleteData(id);
    }

}
