package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.FuncionRequestDTO;
import com.dam2.Practica1.DTO.FuncionResponseDTO;
import com.dam2.Practica1.DTO.SalaRequestDTO;
import com.dam2.Practica1.DTO.SalaResponseDTO;
import com.dam2.Practica1.domain.Sala;
import com.dam2.Practica1.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    /**
     * CRUD
     */

    @GetMapping
    public List<SalaResponseDTO> listarSalas() {
        return salaService.listarSalas();
    }

    @GetMapping("/{id}")
    public SalaResponseDTO buscarSalaPorId(@PathVariable Long id) {
        return salaService.buscarSalaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalaResponseDTO agregarSala(@RequestBody @Valid SalaRequestDTO salaRequestDTO) {
        return salaService.agregarSala(salaRequestDTO);
    }

    @PutMapping("/{id}")
    public SalaResponseDTO actualizarSala(@PathVariable Long id, @RequestBody @Valid SalaRequestDTO salaRequestDTO) {
        return salaService.actualizarSala(id, salaRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSala(@PathVariable Long id) {
        salaService.eliminarSala(id);
    }

}
