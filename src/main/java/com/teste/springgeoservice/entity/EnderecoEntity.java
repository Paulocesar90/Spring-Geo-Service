package com.teste.springgeoservice.entity;


import lombok.Data;



import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String endereco;

    @Lob
    @Column(nullable = false, length = 4096)
    private String jsonResultado;

    @Column(nullable = false)
    private LocalDateTime dataHoraConsulta;
}
