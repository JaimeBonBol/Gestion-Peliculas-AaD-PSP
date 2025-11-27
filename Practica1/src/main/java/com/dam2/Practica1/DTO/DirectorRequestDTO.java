package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorRequestDTO {

    @NotBlank(message = "El nombre del director es obligatorio")
    private String nombre;
}
