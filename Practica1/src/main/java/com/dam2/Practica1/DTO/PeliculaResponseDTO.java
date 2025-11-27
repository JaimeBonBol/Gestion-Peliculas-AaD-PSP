package com.dam2.Practica1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaResponseDTO {

    private Long id;
    private String titulo;
    private Integer duracion;
    private LocalDate fechaEstreno;
    private String sinopsis;
    private Integer valoracion;

    /*
    private String idFichaTecnica;
    private String director;
    private List<String> actores;
    private List<String> categorias;
    private List<String> idiomas;
    private List<String> plataformas;
    private List<String> funciones;
    private List<String> criticas;
     */

}
