package com.dam2.Practica1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categorias")
@Data  // Lombok genera getters, setters, toString, equals, hashCode
@AllArgsConstructor      // Genera constructor con todos los campos
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private List<Pelicula> peliculas;

}
