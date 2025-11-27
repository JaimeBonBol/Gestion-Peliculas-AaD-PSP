package com.dam2.Practica1.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaRequestDTO {

    @NotNull(message = "El número de sala es obligatorio")
    private Long numeroSala;

    @NotNull(message = "La capacidad es obligatoria insertarla")
    private Long capacidad;

}
