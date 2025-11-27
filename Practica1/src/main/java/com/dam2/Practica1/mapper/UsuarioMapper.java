package com.dam2.Practica1.mapper;

import com.dam2.Practica1.DTO.UsuarioRequestDTO;
import com.dam2.Practica1.DTO.UsuarioResponseDTO;
import com.dam2.Practica1.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());

        return usuario;
    }

    public void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {
        if (usuario == null || dto == null) return;

        if (dto.getUsername() != null) usuario.setUsername(dto.getUsername());
        if (dto.getEmail() != null) usuario.setEmail(dto.getEmail());
        if (dto.getPassword() != null) usuario.setPassword(dto.getPassword());
        if (dto.getRol() != null) usuario.setRol(dto.getRol());
    }

    public UsuarioResponseDTO toDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());

        return dto;
    }
}
