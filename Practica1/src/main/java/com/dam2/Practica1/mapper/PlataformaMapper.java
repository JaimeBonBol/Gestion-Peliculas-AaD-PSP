package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.domain.Plataforma;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PlataformaMapper {

    public Plataforma toEntity(PlataformaRequestDTO dto) {
        if (dto == null) return null;

        Plataforma plataforma = new Plataforma();
        plataforma.setNombre(dto.getNombre());
        plataforma.setUrl(dto.getUrl());

        return plataforma;
    }

    public void updateEntity(Plataforma plataforma, PlataformaRequestDTO dto) {
        if (plataforma == null || dto == null) return;

        if (dto.getNombre() != null) plataforma.setNombre(dto.getNombre());
        if (dto.getUrl() != null) plataforma.setUrl(dto.getUrl());
    }

    public PlataformaResponseDTO toDto(Plataforma plataforma) {
        if (plataforma == null) return null;
        PlataformaResponseDTO dto = new PlataformaResponseDTO();

        dto.setId(plataforma.getId());
        dto.setNombre(plataforma.getNombre());
        dto.setUrl(plataforma.getUrl());


        return dto;
    }

}
