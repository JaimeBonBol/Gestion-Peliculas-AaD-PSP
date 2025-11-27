package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.IdiomaRequestDTO;
import com.dam2.Practica1.DTO.IdiomaResponseDTO;
import com.dam2.Practica1.domain.Categoria;
import com.dam2.Practica1.domain.Idioma;
import org.springframework.stereotype.Component;

@Component
public class IdiomaMapper {

    public Idioma toEntity(IdiomaRequestDTO dto) {
        if (dto == null) return null;

        Idioma idioma = new Idioma();
        idioma.setNombre(dto.getNombre());

        return idioma;
    }

    public void updateEntity(Idioma idioma, IdiomaRequestDTO dto) {
        if (idioma == null || dto == null) return;

        if (dto.getNombre() != null) idioma.setNombre(dto.getNombre());
    }

    public IdiomaResponseDTO toDto(Idioma idioma) {
        if (idioma == null) return null;
        IdiomaResponseDTO dto = new IdiomaResponseDTO();

        dto.setId(idioma.getId());
        dto.setNombre(idioma.getNombre());

        return dto;
    }

}
