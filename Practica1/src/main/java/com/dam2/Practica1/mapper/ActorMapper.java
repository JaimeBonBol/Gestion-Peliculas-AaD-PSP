package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.ActorRequestDTO;
import com.dam2.Practica1.DTO.ActorResponseDTO;
import com.dam2.Practica1.domain.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {

    public Actor toEntity(ActorRequestDTO dto) {
        if (dto == null) return null;

        Actor actor = new Actor();
        actor.setNombre(dto.getNombre());
        return actor;
    }

    public void updateEntity(Actor actor, ActorRequestDTO dto) {
        if (actor == null || dto == null) return;

        if (dto.getNombre() != null) actor.setNombre(dto.getNombre());
    }

    public ActorResponseDTO toDto(Actor actor) {
        if (actor == null) return null;

        ActorResponseDTO dto = new ActorResponseDTO();
        dto.setId(actor.getId());
        dto.setNombre(actor.getNombre());
        return dto;
    }
}
