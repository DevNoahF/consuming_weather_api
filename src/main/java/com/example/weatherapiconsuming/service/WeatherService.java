package com.example.weatherapiconsuming.service;

import com.example.weatherapiconsuming.dto.WeatherRequestDTO;
import com.example.weatherapiconsuming.dto.WeatherResponseDTO;
import com.example.weatherapiconsuming.infra.exceptions.ErrorJsonApiResponseException;
import com.example.weatherapiconsuming.utility.WeatherFormatterUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherFormatterUrl weatherFormatterUrl;
    private final ObjectMapper objectMapper;

    // 'value' é o nome do "balde" no Redis
    // 'key' define como identificar cada busca (ex: pelo nome da cidade)
    @Cacheable(value = "weather", key = "#dto")
    public WeatherResponseDTO getWeatherApi(WeatherRequestDTO dto) {
        var apiResponse = weatherFormatterUrl.formatUrl(dto);
        try {
            return objectMapper.readValue(apiResponse, WeatherResponseDTO.class);
        } catch (Exception e) {
            log.error("Erro ao processar a API Response: {}", e.getMessage());
            throw new ErrorJsonApiResponseException("Erro ao processar a API Response: " + e.getMessage());
        }
    }

}
