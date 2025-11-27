package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.DirectorRequestDTO;
import com.dam2.Practica1.DTO.DirectorResponseDTO;
import com.dam2.Practica1.domain.Director;
import com.dam2.Practica1.mapper.DirectorMapper;
import com.dam2.Practica1.repository.DirectorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public List<DirectorResponseDTO> listarDirectores() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toDto)
                .toList();
    }

    public DirectorResponseDTO buscarDirectorPorId(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Director no encontrado con id: " + id));

        return directorMapper.toDto(director);
    }

    @Transactional
    public DirectorResponseDTO agregarDirector(DirectorRequestDTO dto) {
        Director director = directorMapper.toEntity(dto);
        Director saved = directorRepository.save(director);
        return directorMapper.toDto(saved);
    }

    @Transactional
    public DirectorResponseDTO actualizarDirector(Long id, DirectorRequestDTO dto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Director no encontrado con id: " + id));

        directorMapper.updateEntity(director, dto);
        directorRepository.save(director);

        return directorMapper.toDto(director);
    }

    @Transactional
    public void eliminarDirector(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Director no encontrado con id: " + id));

        directorRepository.delete(director);
    }
}
