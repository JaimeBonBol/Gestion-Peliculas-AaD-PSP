package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.domain.Plataforma;
import com.dam2.Practica1.mapper.PlataformaMapper;
import com.dam2.Practica1.repository.PlataformaRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlataformaService {

    private final PlataformaRepository plataformaRepository;
    private final PlataformaMapper plataformaMapper;

    /**
     * CRUD
     */

    public List<PlataformaResponseDTO> listarPlataformas() {
        return plataformaRepository.findAll()
                .stream()
                .map(plataformaMapper::toDto).toList();
    }

    public PlataformaResponseDTO buscarPlataformaPorId(Long id) {
        Plataforma plataforma = plataformaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plataforma no encontrada con id: " + id));

        return plataformaMapper.toDto(plataforma);
    }

    @Transactional
    public PlataformaResponseDTO agregarPlataforma(PlataformaRequestDTO plataformaRequestDTO) {
        Plataforma plataforma = plataformaMapper.toEntity(plataformaRequestDTO);

        plataformaRepository.save(plataforma);

        return plataformaMapper.toDto(plataforma);
    }

    @Transactional
    public PlataformaResponseDTO actualizarPlataforma(Long id, PlataformaRequestDTO plataformaRequestDTO) {
        Plataforma plataforma = plataformaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plataforma no encontrada con id: " + id));

        plataformaMapper.updateEntity(plataforma, plataformaRequestDTO);

        plataformaRepository.save(plataforma);

        return plataformaMapper.toDto(plataforma);
    }

    @Transactional
    public void eliminarPlataforma(Long id) {
        Plataforma plataforma = plataformaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plataforma no encontrada con id: " + id));

        plataformaRepository.delete(plataforma);
    }

}
