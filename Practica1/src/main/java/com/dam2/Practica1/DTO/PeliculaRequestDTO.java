package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaRequestDTO {

    @NotBlank(message = "El título es obligatorio, no puede estar vacío ni ser nulo")  // No nulo + no vacío
    private String titulo;

    @NotNull(message = "La duración es obligatoria")
    private Integer duracion;

    @NotNull(message = "La fecha de estreno es obligatoria")
    private LocalDate fechaEstreno;

    @NotBlank(message = "La sinopsis es obligatoria")
    private String sinopsis;

    @NotNull(message = "La valoración es obligatoria")
    private Integer valoracion;

    /*
    private Long idFichaTecnica;

    private Long idDirector;

    private List<Long> idsActores;

    private List<Long> idsCategorias;

    private List<Long> idsIdiomas;

    private List<Long> idsPlataformas;

    private List<Long> idsFunciones;

    private List<Long> idsCriticas;
     */

}
