package com.dam2.Practica1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Data  // ✅ Lombok genera getters, setters, toString, equals, hashCode
@AllArgsConstructor      // ✅ genera constructor con todos los campos
@NoArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    private Integer duracion;              // minutos

    @Column(name = "fecha_estreno")
    private LocalDate fechaEstreno;

    private String sinopsis;

    private Integer valoracion;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @JsonManagedReference
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "peliculas_actores",                             // nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "pelicula_id"),        // FK de esta entidad
            inverseJoinColumns = @JoinColumn(name = "actor_id")     // FK de la otra entidad
    )
    @JsonIgnore
    private List<Actor> actores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "peliculas_categorias",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "peliculas_idiomas",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private List<Idioma> idiomas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "peliculas_plataformas",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id")
    )
    private List<Plataforma> plataformas = new ArrayList<>();

    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones = new ArrayList<>();

    @OneToMany(mappedBy = "pelicula")
    private List<Critica> criticas = new ArrayList<>();



//    public void addActor(Actor actor){
//        actores.add(actor);
//        actor.getPeliculas().add(this);
//    }

    // HELPERS ManyToMany
    public void addActor(Actor actor){
        if (!actores.contains(actor)){
            actores.add(actor);
        }
        if (!actor.getPeliculas().contains(this)){
            actor.getPeliculas().add(this);
        }
    }

    public void addCategoria(Categoria categoria){
        if (!categorias.contains(categoria)){
            categorias.add(categoria);
        }
        if (!categoria.getPeliculas().contains(this)){
            categoria.getPeliculas().add(this);
        }
    }

    public void addIdioma(Idioma idioma){
        if (!idiomas.contains(idioma)){
            idiomas.add(idioma);
        }
        if (!idioma.getPeliculas().contains(this)){
            idioma.getPeliculas().add(this);
        }
    }

    public void addPlataforma(Plataforma plataforma){
        if (!plataformas.contains(plataforma)){
            plataformas.add(plataforma);
        }
        if (!plataforma.getPeliculas().contains(this)){
            plataforma.getPeliculas().add(this);
        }
    }
}
