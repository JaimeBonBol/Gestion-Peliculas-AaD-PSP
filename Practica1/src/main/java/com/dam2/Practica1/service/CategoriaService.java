package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.PlataformaRequestDTO;
import com.dam2.Practica1.DTO.PlataformaResponseDTO;
import com.dam2.Practica1.domain.Categoria;
import com.dam2.Practica1.domain.Plataforma;
import com.dam2.Practica1.mapper.CategoriaMapper;
import com.dam2.Practica1.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    /**
     * CRUD
     */

    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDto).toList();
    }

    public CategoriaResponseDTO buscarCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));

        return categoriaMapper.toDto(categoria);
    }

    @Transactional
    public CategoriaResponseDTO agregarCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaRequestDTO);

        categoriaRepository.save(categoria);

        return categoriaMapper.toDto(categoria);
    }

    @Transactional
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));

        categoriaMapper.updateEntity(categoria, categoriaRequestDTO);

        categoriaRepository.save(categoria);

        return categoriaMapper.toDto(categoria);
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));

        categoriaRepository.delete(categoria);
    }

}
