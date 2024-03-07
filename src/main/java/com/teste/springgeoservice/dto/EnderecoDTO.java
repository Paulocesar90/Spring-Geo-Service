package com.teste.springgeoservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EnderecoDTO {

    private String endereco;
    private String jsonResultado;
    private LocalDateTime dataHoraConsulta;
}