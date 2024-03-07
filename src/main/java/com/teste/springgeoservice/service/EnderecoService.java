package com.teste.springgeoservice.service;




import com.teste.springgeoservice.dto.EnderecoDTO;
import com.teste.springgeoservice.entity.EnderecoEntity;


import com.teste.springgeoservice.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Serviço para operações relacionadas a endereços.
 */
@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;


    /**
     * Salva informações do endereço no banco de dados.
     *
     * @param enderecoDTO Informações do endereço a serem salvas.
     * @return            O ID da entidade salva.
     */
    @Transactional
    public Long salvarEndereco(EnderecoDTO enderecoDTO) {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setEndereco(enderecoDTO.getEndereco());
        enderecoEntity.setJsonResultado(enderecoDTO.getJsonResultado());
        enderecoEntity.setDataHoraConsulta(enderecoDTO.getDataHoraConsulta());

        enderecoRepository.save(enderecoEntity);
        return enderecoEntity.getId();
    }
}
