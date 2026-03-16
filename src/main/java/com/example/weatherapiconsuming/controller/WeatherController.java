package com.example.weatherapiconsuming.controller;

import com.example.weatherapiconsuming.dto.WeatherRequestDTO;
import com.example.weatherapiconsuming.dto.WeatherResponseDTO;
import com.example.weatherapiconsuming.infra.exceptions.ErrorJsonApiResponseException;
import com.example.weatherapiconsuming.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController()
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {
    private final WeatherService weatherService;

    @Operation(summary = "Get weather data from api weather")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK -  returned weather data"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - please check your API key")
    })


    @PostMapping
    public ResponseEntity<WeatherResponseDTO> getData(
            @Parameter(description = "City, state, country, and date range(initial date and finalDate)")
            @RequestBody WeatherRequestDTO dto) {
        try {
            return ResponseEntity.ok(weatherService.getWeatherApi(dto));
        } catch (ErrorJsonApiResponseException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<String> getHealth(){
        return  ResponseEntity.status(HttpStatus.OK).build();
    }


}
