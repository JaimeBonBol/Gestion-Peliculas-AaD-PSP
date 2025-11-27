package com.dam2.Practica1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriticaResponseDTO {

    private Long id;
    private String comentario;
    private Double nota;
    private LocalDate fecha;

}
