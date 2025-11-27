package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.FuncionRequestDTO;
import com.dam2.Practica1.DTO.FuncionResponseDTO;
import com.dam2.Practica1.domain.Categoria;
import com.dam2.Practica1.domain.Funcion;
import com.dam2.Practica1.mapper.FuncionMapper;
import com.dam2.Practica1.repository.FuncionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionService {

    private final FuncionRepository funcionRepository;
    private final FuncionMapper funcionMapper;

    /**
     * CRUD
     */

    public List<FuncionResponseDTO> listarFunciones() {
        return funcionRepository.findAll()
                .stream()
                .map(funcionMapper::toDto).toList();
    }

    public FuncionResponseDTO buscarFuncionPorId(Long id) {
        Funcion funcion = funcionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada con id: " + id));

        return funcionMapper.toDto(funcion);
    }

    @Transactional
    public FuncionResponseDTO agregarFuncion(FuncionRequestDTO funcionRequestDTO) {
        Funcion funcion = funcionMapper.toEntity(funcionRequestDTO);

        funcionRepository.save(funcion);

        return funcionMapper.toDto(funcion);
    }

    @Transactional
    public FuncionResponseDTO actualizarFuncion(Long id, FuncionRequestDTO funcionRequestDTO) {
        Funcion funcion = funcionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada con id: " + id));

        funcionMapper.updateEntity(funcion, funcionRequestDTO);

        funcionRepository.save(funcion);

        return funcionMapper.toDto(funcion);
    }

    @Transactional
    public void eliminarFuncion(Long id) {
        Funcion funcion = funcionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada con id: " + id));

        funcionRepository.delete(funcion);
    }

}
