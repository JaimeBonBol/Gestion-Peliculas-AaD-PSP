package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.DirectorRequestDTO;
import com.dam2.Practica1.DTO.DirectorResponseDTO;
import com.dam2.Practica1.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public List<DirectorResponseDTO> listarDirectores() {
        return directorService.listarDirectores();
    }

    @GetMapping("/{id}")
    public DirectorResponseDTO buscarDirectorPorId(@PathVariable Long id) {
        return directorService.buscarDirectorPorId(id);
    }

    @PostMapping
    public DirectorResponseDTO agregarDirector(@RequestBody @Valid DirectorRequestDTO directorRequestDTO) {
        return directorService.agregarDirector(directorRequestDTO);
    }

    @PutMapping("/{id}")
    public DirectorResponseDTO actualizarDirector(@PathVariable Long id, @RequestBody @Valid DirectorRequestDTO directorRequestDTO) {
        return directorService.actualizarDirector(id, directorRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarDirector(@PathVariable Long id) {
        directorService.eliminarDirector(id);
    }
}
