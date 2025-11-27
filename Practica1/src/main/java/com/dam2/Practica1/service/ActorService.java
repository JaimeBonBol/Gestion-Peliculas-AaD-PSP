package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.ActorRequestDTO;
import com.dam2.Practica1.DTO.ActorResponseDTO;
import com.dam2.Practica1.domain.Actor;
import com.dam2.Practica1.mapper.ActorMapper;
import com.dam2.Practica1.repository.ActorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public List<ActorResponseDTO> listarActores() {
        return actorRepository.findAll()
                .stream()
                .map(actorMapper::toDto)
                .toList();
    }

    public ActorResponseDTO buscarActorPorId(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor no encontrado con id: " + id));

        return actorMapper.toDto(actor);
    }

    @Transactional
    public ActorResponseDTO agregarActor(ActorRequestDTO dto) {
        Actor actor = actorMapper.toEntity(dto);
        Actor saved = actorRepository.save(actor);
        return actorMapper.toDto(saved);
    }

    @Transactional
    public ActorResponseDTO actualizarActor(Long id, ActorRequestDTO dto) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor no encontrado con id: " + id));

        actorMapper.updateEntity(actor, dto);
        actorRepository.save(actor);
        return actorMapper.toDto(actor);
    }

    @Transactional
    public void eliminarActor(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor no encontrado con id: " + id));

        actorRepository.delete(actor);
    }
}
