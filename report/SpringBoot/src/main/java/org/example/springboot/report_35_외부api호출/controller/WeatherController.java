package org.example.springboot.report_35_외부api호출.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.report_35_외부api호출.dto.Item;
import org.example.springboot.report_35_외부api호출.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public List<String> weather(){
        return weatherService.getReadableWeather(60,127);
    }

//    @GetMapping("/weather")
//    public List<Item> weatherJson(){
//        return weatherService.getCurrentWeather(60,127);
//    }
}
