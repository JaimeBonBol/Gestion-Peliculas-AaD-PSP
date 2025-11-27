package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.DirectorRequestDTO;
import com.dam2.Practica1.DTO.DirectorResponseDTO;
import com.dam2.Practica1.domain.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {

    public Director toEntity(DirectorRequestDTO dto) {
        if (dto == null) return null;

        Director director = new Director();
        director.setNombre(dto.getNombre());
        return director;
    }

    public void updateEntity(Director director, DirectorRequestDTO dto) {
        if (director == null || dto == null) return;

        if (dto.getNombre() != null) director.setNombre(dto.getNombre());
    }

    public DirectorResponseDTO toDto(Director director) {
        if (director == null) return null;

        DirectorResponseDTO dto = new DirectorResponseDTO();
        dto.setId(director.getId());
        dto.setNombre(director.getNombre());
        return dto;
    }
}
