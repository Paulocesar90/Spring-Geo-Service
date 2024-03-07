package com.teste.springgeoservice.controller;



import com.teste.springgeoservice.service.CoordenadasService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@RestController
public class CoordenadasController {

    @Value("${google.api.key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final CoordenadasService coordenadasService;

    @Autowired
    public CoordenadasController(CoordenadasService coordenadasService) {
        this.coordenadasService = coordenadasService;
    }

    @GetMapping("/coordinates")
    public ResponseEntity<String> getCoordinates(@RequestParam Double latitude, @RequestParam Double longitude) {
        try {
            Request request = new Request.Builder()
                    .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?latlng=" + latitude + "%2C" + longitude + "&language=en")
                    .get()
                    .addHeader("X-RapidAPI-Key", apiKey)
                    .addHeader("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String jsonResponse = response.body().string();

                // Adicione a data e hora atual
                LocalDateTime dataHoraAtual = LocalDateTime.now();

                // Salvar no banco usando o serviço
                coordenadasService.salvarCoordenadas(latitude, longitude, jsonResponse, dataHoraAtual);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

                return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
        }
    }
}
