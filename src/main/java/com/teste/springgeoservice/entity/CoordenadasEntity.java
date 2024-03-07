package com.teste.springgeoservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coordenadas")
public class CoordenadasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Lob
    @Column(nullable = false, length = 4096)
    private String jsonResultado;

    @Column(nullable = false)
    private LocalDateTime dataHoraConsulta;
}
