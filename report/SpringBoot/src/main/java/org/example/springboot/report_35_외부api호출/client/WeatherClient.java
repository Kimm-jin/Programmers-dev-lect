package org.example.springboot.report_35_외부api호출.client;


import org.example.springboot.report_35_외부api호출.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "weatherCLient",
        url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0")
public interface WeatherClient {

    @GetMapping("/getUltraSrtNcst")
    // API 요청 메시지 명세
    WeatherResponse getUltraSrtNcst(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("dataType") String dataType,
            @RequestParam("base_date") String base_date,
            @RequestParam("base_time") String base_time,
            @RequestParam("nx") int nx,
            @RequestParam("ny") int ny
    );
}
