package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.CriticaRequestDTO;
import com.dam2.Practica1.DTO.CriticaResponseDTO;
import com.dam2.Practica1.service.CriticaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criticas")
@RequiredArgsConstructor
public class CriticaController {

    private final CriticaService criticaService;

    @GetMapping
    public List<CriticaResponseDTO> listarCriticas() {
        return criticaService.listarCriticas();
    }

    @GetMapping("/{id}")
    public CriticaResponseDTO buscarCriticaPorId(@PathVariable Long id) {
        return criticaService.buscarCriticaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriticaResponseDTO agregarCritica(@RequestBody @Valid CriticaRequestDTO dto) {
        return criticaService.agregarCritica(dto);
    }

    @PutMapping("/{id}")
    public CriticaResponseDTO actualizarCritica(@PathVariable Long id, @RequestBody @Valid CriticaRequestDTO dto) {
        return criticaService.actualizarCritica(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCritica(@PathVariable Long id) {
        criticaService.eliminarCritica(id);
    }
}
