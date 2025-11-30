package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.IdiomaRequestDTO;
import com.dam2.Practica1.DTO.IdiomaResponseDTO;
import com.dam2.Practica1.service.IdiomaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/idiomas")
@RequiredArgsConstructor
public class IdiomaController {

    private final IdiomaService idiomaService;

    /**
     * CRUD
     */

    @GetMapping
    public List<IdiomaResponseDTO> listarIdiomas() {
        return idiomaService.listarIdiomas();
    }

    @GetMapping("/{id}")
    public IdiomaResponseDTO buscarIdiomaPorId(@PathVariable Long id) {
        return idiomaService.buscarIdiomaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdiomaResponseDTO agregarIdioma(@RequestBody @Valid IdiomaRequestDTO idiomaRequestDTO) {
        return idiomaService.agregarIdioma(idiomaRequestDTO);
    }

    @PutMapping("/{id}")
    public IdiomaResponseDTO actualizarIdioma(@PathVariable Long id, @RequestBody @Valid IdiomaRequestDTO idiomaRequestDTO) {
        return idiomaService.actualizarIdioma(id, idiomaRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarIdioma(@PathVariable Long id) {
        idiomaService.eliminarIdioma(id);
    }

}
