package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlataformaRequestDTO {

    @NotBlank(message = "El nombre de la plataforma es obligatorio")
    private String nombre;

    @NotBlank(message = "La URL de la plataforma es obligatoria")
    private String url;

    //private List<Long> idsPeliculas; // IDs de películas existentes

}
