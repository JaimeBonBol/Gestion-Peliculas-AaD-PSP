package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.ActorRequestDTO;
import com.dam2.Practica1.DTO.ActorResponseDTO;
import com.dam2.Practica1.service.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actores")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public List<ActorResponseDTO> listarActores() {
        return actorService.listarActores();
    }

    @GetMapping("/{id}")
    public ActorResponseDTO buscarActorPorId(@PathVariable Long id) {
        return actorService.buscarActorPorId(id);
    }

    @PostMapping
    public ActorResponseDTO agregarActor(@RequestBody @Valid ActorRequestDTO actorRequestDTO) {
        return actorService.agregarActor(actorRequestDTO);
    }

    @PutMapping("/{id}")
    public ActorResponseDTO actualizarActor(@PathVariable Long id, @RequestBody @Valid ActorRequestDTO actorRequestDTO) {
        return actorService.actualizarActor(id, actorRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarActor(@PathVariable Long id) {
        actorService.eliminarActor(id);
    }
}
