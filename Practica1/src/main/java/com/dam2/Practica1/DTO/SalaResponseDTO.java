package com.dam2.Practica1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaResponseDTO {

    private Long id;
    private Long numeroSala;
    private Long Capacidad;
}
