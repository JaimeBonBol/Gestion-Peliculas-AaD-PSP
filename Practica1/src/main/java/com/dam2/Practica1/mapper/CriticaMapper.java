package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.CriticaRequestDTO;
import com.dam2.Practica1.DTO.CriticaResponseDTO;
import com.dam2.Practica1.domain.Critica;
import org.springframework.stereotype.Component;

@Component
public class CriticaMapper {

    public Critica toEntity(CriticaRequestDTO dto) {
        if (dto == null) return null;

        Critica critica = new Critica();
        critica.setComentario(dto.getComentario());
        critica.setNota(dto.getNota());
        critica.setFecha(dto.getFecha());

        return critica;
    }

    public void updateEntity(Critica critica, CriticaRequestDTO dto) {
        if (critica == null || dto == null) return;

        if (dto.getComentario() != null) critica.setComentario(dto.getComentario());
        if (dto.getNota() != null) critica.setNota(dto.getNota());
        if (dto.getFecha() != null) critica.setFecha(dto.getFecha());
    }

    public CriticaResponseDTO toDto(Critica critica) {
        if (critica == null) return null;

        return new CriticaResponseDTO(
                critica.getId(),
                critica.getComentario(),
                critica.getNota(),
                critica.getFecha()
        );
    }
}
