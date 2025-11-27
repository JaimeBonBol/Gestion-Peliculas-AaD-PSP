package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.FuncionRequestDTO;
import com.dam2.Practica1.DTO.FuncionResponseDTO;
import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.domain.Funcion;
import com.dam2.Practica1.domain.Plataforma;
import org.springframework.stereotype.Component;

@Component
public class FuncionMapper {

    public Funcion toEntity(FuncionRequestDTO dto) {
        if (dto == null) return null;

        Funcion funcion = new Funcion();
        funcion.setFecha(dto.getFecha());
        funcion.setHora(dto.getHora());
        funcion.setPrecio(dto.getPrecio());
        funcion.setFormato(dto.getFormato());

        return funcion;
    }

    public void updateEntity(Funcion funcion, FuncionRequestDTO dto) {
        if (funcion == null || dto == null) return;

        if (dto.getFecha() != null) funcion.setFecha(dto.getFecha());
        if (dto.getHora() != null) funcion.setHora(dto.getHora());
        if (dto.getPrecio() != null) funcion.setPrecio(dto.getPrecio());
        if (dto.getFormato() != null) funcion.setFormato(dto.getFormato());
    }

    public FuncionResponseDTO toDto(Funcion funcion) {
        if (funcion == null) return null;
        FuncionResponseDTO dto = new FuncionResponseDTO();

        dto.setId(funcion.getId());
        dto.setFecha(funcion.getFecha());
        dto.setHora(funcion.getHora());
        dto.setPrecio(funcion.getPrecio());
        dto.setFormato(funcion.getFormato());

        return dto;
    }

}
