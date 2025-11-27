package com.dam2.Practica1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlataformaRequestDTO {

    private String nombre;

    private String url;

    private List<Long> idsPeliculas; // IDs de películas existentes

}
