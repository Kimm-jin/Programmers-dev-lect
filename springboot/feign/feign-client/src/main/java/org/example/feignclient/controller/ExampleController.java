package org.example.feignclient.controller;

import lombok.RequiredArgsConstructor;
import org.example.feignclient.client.ExampleClient;
import org.example.feignclient.service.EaxmpleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class ExampleController {
    private final EaxmpleService eaxmpleService;

    @GetMapping("/data/{id}")
    public String getData(@PathVariable Long id){
        return eaxmpleService.getDataById(id);
    }
}
