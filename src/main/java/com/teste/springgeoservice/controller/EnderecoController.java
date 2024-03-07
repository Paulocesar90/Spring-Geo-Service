package com.teste.springgeoservice.controller;


import com.teste.springgeoservice.dto.EnderecoDTO;
import com.teste.springgeoservice.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class EnderecoController {

    @Value("${google.api.key}")
    private String apiKey;

    private final OkHttpClient client;
    private final EnderecoService enderecoService;

    @GetMapping("/address")
    public ResponseEntity<String> getAddress(@RequestParam String address) {
        if (address == null || address.isEmpty()) {
            return ResponseEntity.badRequest().body("O endereço não pode estar vazio");
        }

        try {
            Request request = new Request.Builder()
                    .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=" + address + "&language=pt")
                    .get()
                    .addHeader("X-RapidAPI-Key", apiKey)
                    .addHeader("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String jsonResponse = response.body().string();

                // Adicione a data e hora da consulta à resposta antes de definir o estado result
                LocalDateTime dataHoraConsulta = LocalDateTime.now();
                enderecoService.salvarEndereco(new EnderecoDTO(address, jsonResponse, dataHoraConsulta));

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

                return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro geral");
        }
    }
}