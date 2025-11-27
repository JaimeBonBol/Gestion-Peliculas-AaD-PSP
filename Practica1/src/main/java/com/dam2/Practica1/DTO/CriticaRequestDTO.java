package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriticaRequestDTO {

    @NotBlank(message = "El comentario es obligatorio")
    private String comentario;

    @NotNull(message = "La nota es obligatoria")
    @Min(value = 0, message = "La nota mínima es 0")
    @Max(value = 10, message = "La nota máxima es 10")
    private Double nota;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

}
