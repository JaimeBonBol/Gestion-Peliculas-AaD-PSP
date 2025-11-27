package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.CategoriaRequestDTO;
import com.dam2.Practica1.DTO.CategoriaResponseDTO;
import com.dam2.Practica1.DTO.IdiomaRequestDTO;
import com.dam2.Practica1.DTO.IdiomaResponseDTO;
import com.dam2.Practica1.domain.Categoria;
import com.dam2.Practica1.domain.Idioma;
import com.dam2.Practica1.mapper.IdiomaMapper;
import com.dam2.Practica1.repository.IdiomaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdiomaService {

    private final IdiomaRepository idiomaRepository;
    private final IdiomaMapper idiomaMapper;

    /**
     * CRUD
     */

    public List<IdiomaResponseDTO> listarIdiomas() {
        return idiomaRepository.findAll()
                .stream()
                .map(idiomaMapper::toDto).toList();
    }

    public IdiomaResponseDTO buscarIdiomaPorId(Long id) {
        Idioma idioma = idiomaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Idioma no encontrado con id: " + id));

        return idiomaMapper.toDto(idioma);
    }

    @Transactional
    public IdiomaResponseDTO agregarIdioma(IdiomaRequestDTO idiomaRequestDTO) {
        Idioma idioma = idiomaMapper.toEntity(idiomaRequestDTO);

        idiomaRepository.save(idioma);

        return idiomaMapper.toDto(idioma);
    }

    @Transactional
    public IdiomaResponseDTO actualizarIdioma(Long id, IdiomaRequestDTO idiomaRequestDTO) {
        Idioma idioma = idiomaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Idioma no encontrado con id: " + id));

        idiomaMapper.updateEntity(idioma, idiomaRequestDTO);

        idiomaRepository.save(idioma);

        return idiomaMapper.toDto(idioma);
    }

    @Transactional
    public void eliminarIdioma(Long id) {
        Idioma idioma = idiomaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Idioma no encontrado con id: " + id));

        idiomaRepository.delete(idioma);
    }

}
