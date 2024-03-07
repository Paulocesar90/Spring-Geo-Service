package com.teste.springgeoservice.service;

import com.teste.springgeoservice.entity.CoordenadasEntity;
import com.teste.springgeoservice.repository.CoordenadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Serviço para operações relacionadas a coordenadas.
 */
@Service
public class CoordenadasService {

    private final CoordenadasRepository coordenadasRepository;

    /**
     * Construtor do serviço de coordenadas.
     *
     * @param coordenadasRepository Repositório para operações relacionadas a coordenadas.
     */
    @Autowired
    public CoordenadasService(CoordenadasRepository coordenadasRepository) {
        this.coordenadasRepository = coordenadasRepository;
    }


    /**
     * Salva coordenadas no banco de dados.
     *
     * @param latitude          Latitude a ser salva.
     * @param longitude         Longitude a ser salva.
     * @param jsonResultado     Resultado JSON associado às coordenadas.
     * @param dataHoraConsulta  Data e hora da consulta das coordenadas.
     * @return                  O ID da entidade salva.
     */
    @Transactional
    public Long salvarCoordenadas(Double latitude, Double longitude, String jsonResultado, LocalDateTime dataHoraConsulta) {
        CoordenadasEntity coordenadasEntity = new CoordenadasEntity();
        coordenadasEntity.setLatitude(latitude);
        coordenadasEntity.setLongitude(longitude);
        coordenadasEntity.setJsonResultado(jsonResultado);
        coordenadasEntity.setDataHoraConsulta(dataHoraConsulta);

        coordenadasRepository.save(coordenadasEntity);
        return coordenadasEntity.getId(); // ou algo que faça sentido para sua aplicação
    }
}