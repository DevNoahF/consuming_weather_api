package com.example.weatherapiconsuming.utility;

import com.example.weatherapiconsuming.dto.WeatherRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class WeatherFormatterUrl {

    private final RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String apiKey;


    public String formatUrl(WeatherRequestDTO dto) {
        log.info("iniciando formatação da url");
        URI uri = UriComponentsBuilder
                .fromUriString("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline")
                .pathSegment(dto.city() + "," + dto.state() + "," + dto.country())
                .pathSegment(dto.initDate().toString())
                .pathSegment(dto.finalDate().toString())
                .queryParam("unitGroup", "metric")
                .queryParam("lang", "pt")
                .queryParam("key", apiKey)
                .build()
                .toUri();
        log.info("URL gerada: {}", uri);
        return restTemplate.getForObject(uri, String.class);
    }
}
