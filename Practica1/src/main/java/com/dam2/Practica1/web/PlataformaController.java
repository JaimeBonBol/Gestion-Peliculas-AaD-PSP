package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.service.PlataformaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plataformas")
@RequiredArgsConstructor
public class PlataformaController {

    private final PlataformaService plataformaService;

    /**
     * CRUD
     */

    @GetMapping
    public List<PlataformaResponseDTO> listarPlataformas() {
        return plataformaService.listarPlataformas();
    }

    @GetMapping("/{id}")
    public PlataformaResponseDTO buscarPlatafromaPorId(@PathVariable Long id) {
        return plataformaService.buscarPlataformaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlataformaResponseDTO agregarPlataforma(@RequestBody @Valid PlataformaRequestDTO plataformaRequestDTO) {
        return plataformaService.agregarPlataforma(plataformaRequestDTO);
    }

    @PutMapping("/{id}")
    public PlataformaResponseDTO actualizarPlataforma(@PathVariable Long id, @RequestBody @Valid PlataformaRequestDTO plataformaRequestDTO) {
        return plataformaService.actualizarPlataforma(id, plataformaRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPlataforma(@PathVariable Long id) {
        plataformaService.eliminarPlataforma(id);
    }

}
