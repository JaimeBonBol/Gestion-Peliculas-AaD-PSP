package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.SalaRequestDTO;
import com.dam2.Practica1.DTO.SalaResponseDTO;
import com.dam2.Practica1.domain.Categoria;
import com.dam2.Practica1.domain.Sala;
import com.dam2.Practica1.mapper.SalaMapper;
import com.dam2.Practica1.repository.SalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;

    /**
     * CRUD
     */

    public List<SalaResponseDTO> listarSalas() {
        return salaRepository.findAll()
                .stream()
                .map(salaMapper::toDto).toList();
    }

    public SalaResponseDTO buscarSalaPorId(Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada con id: " + id));

        return salaMapper.toDto(sala);
    }

    @Transactional
    public SalaResponseDTO agregarSala(SalaRequestDTO salaRequestDTO) {
        Sala sala = salaMapper.toEntity(salaRequestDTO);

        salaRepository.save(sala);

        return salaMapper.toDto(sala);
    }

    @Transactional
    public SalaResponseDTO actualizarSala(Long id, SalaRequestDTO salaRequestDTO) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada con id: " + id));

        salaMapper.updateEntity(sala, salaRequestDTO);

        salaRepository.save(sala);

        return salaMapper.toDto(sala);
    }

    @Transactional
    public void eliminarSala(Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada con id: " + id));

        salaRepository.delete(sala);
    }


}
