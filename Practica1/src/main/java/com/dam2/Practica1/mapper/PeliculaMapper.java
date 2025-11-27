package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.PeliculaRequestDTO;
import com.dam2.Practica1.DTO.PeliculaResponseDTO;
import com.dam2.Practica1.domain.*;
import com.dam2.Practica1.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PeliculaMapper {

    public Pelicula toEntity(PeliculaRequestDTO dto) {
        if (dto == null) return null;

        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo(dto.getTitulo());
        pelicula.setDuracion(dto.getDuracion());
        pelicula.setFechaEstreno(dto.getFechaEstreno());
        pelicula.setSinopsis(dto.getSinopsis());
        pelicula.setValoracion(dto.getValoracion());

        return pelicula;
    }

    public void updateEntity(Pelicula pelicula, PeliculaRequestDTO dto) {
        if (dto == null || pelicula == null) return;

        if (dto.getTitulo() != null)
            pelicula.setTitulo(dto.getTitulo());

        if (dto.getDuracion() != null)
            pelicula.setDuracion(dto.getDuracion());

        if (dto.getFechaEstreno() != null)
            pelicula.setFechaEstreno(dto.getFechaEstreno());

        if (dto.getSinopsis() != null)
            pelicula.setSinopsis(dto.getSinopsis());

        if (dto.getValoracion() != null)
            pelicula.setValoracion(dto.getValoracion());
    }

    public PeliculaResponseDTO toDto(Pelicula pelicula) {
        if (pelicula == null) return null;

        PeliculaResponseDTO peliculaResponseDTO = new PeliculaResponseDTO();

        peliculaResponseDTO.setId(pelicula.getId());
        peliculaResponseDTO.setTitulo(pelicula.getTitulo());
        peliculaResponseDTO.setDuracion(pelicula.getDuracion());
        peliculaResponseDTO.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaResponseDTO.setSinopsis(pelicula.getSinopsis());
        peliculaResponseDTO.setValoracion(pelicula.getValoracion());

        return peliculaResponseDTO;
    }

}
