package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.CriticaRequestDTO;
import com.dam2.Practica1.DTO.CriticaResponseDTO;
import com.dam2.Practica1.domain.Critica;
import com.dam2.Practica1.mapper.CriticaMapper;
import com.dam2.Practica1.repository.CriticaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriticaService {

    private final CriticaRepository criticaRepository;
    private final CriticaMapper criticaMapper;

    public List<CriticaResponseDTO> listarCriticas() {
        return criticaRepository.findAll()
                .stream()
                .map(criticaMapper::toDto)
                .toList();
    }

    public CriticaResponseDTO buscarCriticaPorId(Long id) {
        Critica critica = criticaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Crítica no encontrada con id: " + id));

        return criticaMapper.toDto(critica);
    }

    @Transactional
    public CriticaResponseDTO agregarCritica(CriticaRequestDTO dto) {
        Critica critica = criticaMapper.toEntity(dto);
        criticaRepository.save(critica);
        return criticaMapper.toDto(critica);
    }

    @Transactional
    public CriticaResponseDTO actualizarCritica(Long id, CriticaRequestDTO dto) {
        Critica critica = criticaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Crítica no encontrada con id: " + id));

        criticaMapper.updateEntity(critica, dto);
        criticaRepository.save(critica);

        return criticaMapper.toDto(critica);
    }

    @Transactional
    public void eliminarCritica(Long id) {
        Critica critica = criticaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Crítica no encontrada con id: " + id));

        criticaRepository.delete(critica);
    }
}
