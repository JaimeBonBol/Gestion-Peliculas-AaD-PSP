package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.domain.Categoria;
import com.dam2.Practica1.domain.Plataforma;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDTO dto) {
        if (dto == null) return null;

        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());

        return categoria;
    }

    public void updateEntity(Categoria categoria, CategoriaRequestDTO dto) {
        if (categoria == null || dto == null) return;

        if (dto.getNombre() != null) categoria.setNombre(dto.getNombre());
    }

    public CategoriaResponseDTO toDto(Categoria categoria) {
        if (categoria == null) return null;
        CategoriaResponseDTO dto = new CategoriaResponseDTO();

        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());

        return dto;
    }

}
