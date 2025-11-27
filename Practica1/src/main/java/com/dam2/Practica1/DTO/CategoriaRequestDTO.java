package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {

    @NotBlank(message = "Nombre de la categoría obligatorio")
    private String nombre;

}
