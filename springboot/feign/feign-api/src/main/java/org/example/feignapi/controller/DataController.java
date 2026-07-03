package org.example.feignapi.controller;

import jakarta.annotation.PostConstruct;
import org.example.feignapi.dao.DataRequest;
import org.example.feignapi.dao.DataResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private Map<Long, DataResponse> dataStore = new HashMap<>();
    private Long idCounter = 1L;

    @PostConstruct
    public void initDataStore() {
        dataStore.put(idCounter++, new DataResponse(1L, "Item 1", 100));
        dataStore.put(idCounter++, new DataResponse(2L, "Item 2", 200));
        dataStore.put(idCounter++, new DataResponse(3L, "Item 3", 300));
        dataStore.put(idCounter++, new DataResponse(4L, "Item 4", 400));
        dataStore.put(idCounter++, new DataResponse(5L, "Item 5", 500));

    }

    // api/data/id
    @GetMapping("/{id}")
    public DataResponse getDataById(@PathVariable Long id) {
        DataResponse dataResponse = dataStore.get(id);

        if (dataResponse == null) {
            throw new RuntimeException("Data not found " + id);
        }

        return dataResponse;
    }

    @PostMapping
    public DataResponse createData(@RequestBody DataRequest dataRequest) {
//        휴먼에러 나기 쉬움
//        DataResponse dataResponse = new DataResponse(idCounter++, dataRequest.getName(), dataRequest.getValue());
        // 순서를 바꿔도 상관없음
        DataResponse build = DataResponse.builder()
                .name(dataRequest.getName())
                .value(dataRequest.getValue())
                .id(idCounter)
                .build();

        dataStore.put(build.getId(), build);

        return build;

    }
}
