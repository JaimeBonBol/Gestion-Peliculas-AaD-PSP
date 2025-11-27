package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.DTO.SalaRequestDTO;
import com.dam2.Practica1.DTO.SalaResponseDTO;
import com.dam2.Practica1.domain.Plataforma;
import com.dam2.Practica1.domain.Sala;
import org.springframework.stereotype.Component;

@Component
public class SalaMapper {

    public Sala toEntity(SalaRequestDTO dto) {
        if (dto == null) return null;

        Sala sala = new Sala();
        sala.setNumeroSala(dto.getNumeroSala());
        sala.setCapacidad(dto.getCapacidad());

        return sala;
    }

    public void updateEntity(Sala sala, SalaRequestDTO dto) {
        if (sala == null || dto == null) return;

        if (dto.getNumeroSala() != null) sala.setNumeroSala(dto.getNumeroSala());
        if (dto.getCapacidad() != null) sala.setCapacidad(dto.getCapacidad());
    }

    public SalaResponseDTO toDto(Sala sala) {
        if (sala == null) return null;
        SalaResponseDTO dto = new SalaResponseDTO();

        dto.setId(sala.getId());
        dto.setNumeroSala(sala.getNumeroSala());
        dto.setCapacidad(sala.getCapacidad());

        return dto;
    }

}
