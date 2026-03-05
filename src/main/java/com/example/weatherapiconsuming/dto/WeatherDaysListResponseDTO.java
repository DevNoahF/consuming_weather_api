package com.example.weatherapiconsuming.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// ele ignora campos nao declarados
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherDaysListResponseDTO(@JsonProperty("tempmax") String tempMax,
                                         @JsonProperty("tempmin") String tempMin,
                                         @JsonProperty("temp") String temp,
                                         @JsonProperty("datetimeEpoch") String datetimeEpoch,
                                         @JsonProperty("datetime") String dateTime,
                                         @JsonProperty("humidity") String humidity,
                                         @JsonProperty("icon") String icon,
                                         @JsonProperty("conditions") String conditions) implements Serializable {
}
