package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdiomaRequestDTO {

    @NotBlank(message = "El nombre del idioma es obligatorio")
    private String nombre;

}
