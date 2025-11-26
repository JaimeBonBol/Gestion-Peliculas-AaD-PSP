package com.dam2.Practica1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "plataformas")
@Data  // Lombok genera getters, setters, toString, equals, hashCode
@AllArgsConstructor      // Genera constructor con todos los campos
@NoArgsConstructor
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String url;

    @ManyToMany(mappedBy = "plataformas")
    private List<Pelicula> peliculas;

}
