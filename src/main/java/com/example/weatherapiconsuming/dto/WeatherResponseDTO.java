package com.example.weatherapiconsuming.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
// ele ignora dados que eu nao declarei
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponseDTO(@JsonProperty("address") String address,
                                 @JsonProperty("timezone") String timezone,
                                 @JsonProperty("days") List<WeatherDaysListResponseDTO> days) implements Serializable {
}
