package com.dam2.Practica1.web;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.FuncionRequestDTO;
import com.dam2.Practica1.DTO.FuncionResponseDTO;
import com.dam2.Practica1.service.FuncionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@RequiredArgsConstructor
public class FuncionController {

    private final FuncionService funcionService;

    /**
     * CRUD
     */

    @GetMapping
    public List<FuncionResponseDTO> listarFunciones() {
        return funcionService.listarFunciones();
    }

    @GetMapping("/{id}")
    public FuncionResponseDTO buscarFuncionPorId(@PathVariable Long id) {
        return funcionService.buscarFuncionPorId(id);
    }

    @PostMapping
    public FuncionResponseDTO agregarFuncion(@RequestBody @Valid FuncionRequestDTO funcionRequestDTO) {
        return funcionService.agregarFuncion(funcionRequestDTO);
    }

    @PutMapping("/{id}")
    public FuncionResponseDTO actualizarFuncion(@PathVariable Long id, @RequestBody @Valid FuncionRequestDTO funcionRequestDTO) {
        return funcionService.actualizarFuncion(id, funcionRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarFuncion(@PathVariable Long id) {
        funcionService.eliminarFuncion(id);
    }

}
