package com.teste.springgeoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordenadasDTO {
    private Double latitude;
    private Double longitude;
    private String jsonResultado;
}