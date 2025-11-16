package com.dam2.Practica1.config;

import com.dam2.Practica1.domain.Actor;
import com.dam2.Practica1.domain.Director;
import com.dam2.Practica1.domain.FichaTecnica;
import com.dam2.Practica1.domain.Pelicula;
import com.dam2.Practica1.repository.ActorRepository;
import com.dam2.Practica1.repository.DirectorRepository;
import com.dam2.Practica1.repository.FichaTecnicaRepository;
import com.dam2.Practica1.repository.PeliculaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
@Configuration
public class DataLoader {

    // Este código debe ejecutarse automáticamente justo DESPUÉS de que arranque toda la aplicación.
    @Bean
    CommandLineRunner initData(ActorRepository actorRepo,
                               DirectorRepository directorRepo,
                               FichaTecnicaRepository fichaRepo,
                               PeliculaRepository peliculaRepo) {

        return args -> {


            // =====================================================
            // 🚨 PROTECCIÓN: solo cargar datos si NO hay directores
            // =====================================================
            if (directorRepo.count() > 0) {
                System.out.println(">>> Datos ya existentes. NO se carga DataLoader.");
                return;
            }

            System.out.println(">>> CARGANDO DATOS DE PRUEBA...");

            // ======================================
            // DIRECTORES
            // ======================================
            Director nolan = new Director(null, "Christopher Nolan", new ArrayList<>());
            Director docter = new Director(null, "Pete Docter", new ArrayList<>());
            Director matt = new Director(null, "Matt Reeves", new ArrayList<>());
            Director joseph = new Director(null, "Joseph Kosinski", new ArrayList<>());
            Director gareth = new Director(null, "Gareth Edwards", new ArrayList<>());

            directorRepo.save(nolan);
            directorRepo.save(docter);
            directorRepo.save(matt);
            directorRepo.save(joseph);
            directorRepo.save(gareth);

            // ======================================
            // FICHAS TÉCNICAS
            // ======================================
            // OJO: tus fichas solo tienen (id, director, duracion, pais)
            FichaTecnica f1 = new FichaTecnica(null, "Christopher Nolan", 169, "EE.UU.");
            FichaTecnica f2 = new FichaTecnica(null, "Pete Docter", 100, "EE.UU.");
            FichaTecnica f3 = new FichaTecnica(null, "Matt Reeves", 186, "EE.UU.");
            FichaTecnica f4 = new FichaTecnica(null, "Joseph Kosinski", 176, "EE.UU.");
            FichaTecnica f5 = new FichaTecnica(null, "Gareth Edwards", 152, "EE.UU.");

            fichaRepo.save(f1);
            fichaRepo.save(f2);
            fichaRepo.save(f3);
            fichaRepo.save(f4);
            fichaRepo.save(f5);

            // ======================================
            // PELÍCULAS
            // ======================================
            Pelicula interstellar = new Pelicula(
                    null,
                    "Interstellar",
                    169,
                    LocalDate.of(2014, 11, 7),
                    "Exploradores espaciales viajan a través de un agujero de gusano...",
                    9,
                    f1,
                    nolan,
                    new ArrayList<>()  // lista de actores VACÍA
            );

            Pelicula soul = new Pelicula(
                    null,
                    "Soul",
                    100,
                    LocalDate.of(2020, 12, 25),
                    "Un músico descubre el verdadero sentido de la vida...",
                    8,
                    f2,
                    docter,
                    new ArrayList<>()
            );

            Pelicula batman = new Pelicula(
                    null,
                    "The Batman",
                    184,
                    LocalDate.of(2022, 03, 04),
                    "El caballero oscuro...",
                    7,
                    f3,
                    matt,
                    new ArrayList<>()
            );

            Pelicula formula1 = new Pelicula(
                    null,
                    "F1",
                    176,
                    LocalDate.of(2025, 06, 27),
                    "La formula 1 vista desde primera persona...",
                    6,
                    f4,
                    joseph,
                    new ArrayList<>()
            );

            Pelicula jurassicWorld = new Pelicula(
                    null,
                    "Jurassic World: Rebirth",
                    152,
                    LocalDate.of(2025, 07, 02),
                    "El renacer de los dinosaurios...",
                    9,
                    f5,
                    gareth,
                    new ArrayList<>()
            );

            peliculaRepo.save(interstellar);
            peliculaRepo.save(soul);
            peliculaRepo.save(batman);
            peliculaRepo.save(formula1);
            peliculaRepo.save(jurassicWorld);

            // ======================================
            // ACTORES
            // ======================================
            Actor matthew = new Actor(null, "Matthew McConaughey", new ArrayList<>());
            Actor hathaway = new Actor(null, "Anne Hathaway", new ArrayList<>());
            Actor foxx = new Actor(null, "Jamie Foxx", new ArrayList<>());
            Actor scarlett = new Actor(null, "Scarlett Johansson", new ArrayList<>());
            Actor brad = new Actor(null, "Brad Pitt", new ArrayList<>());
            Actor robert = new Actor(null, "Robert Pattinson", new ArrayList<>());

            actorRepo.save(matthew);
            actorRepo.save(hathaway);
            actorRepo.save(foxx);
            actorRepo.save(scarlett);
            actorRepo.save(brad);
            actorRepo.save(robert);

            // ======================================
            // RELACIÓN MANY-TO-MANY
            // (Usando tus métodos de sincronización)
            // ======================================
//            matthew.addPelicula(interstellar);
//            hathaway.addPelicula(interstellar);
//            foxx.addPelicula(soul);
//
//            /* Hace falta añadirlo también?*/
//            interstellar.addActor(matthew);
//            interstellar.addActor(hathaway);
//            soul.addActor(foxx);
//
//
//            actorRepo.save(matthew);
//            actorRepo.save(hathaway);
//            actorRepo.save(foxx);

            // RELACIÓN MANY-TO-MANY
            interstellar.addActor(matthew);
            interstellar.addActor(hathaway);
            soul.addActor(foxx);
            jurassicWorld.addActor(scarlett);
            batman.addActor(robert);
            formula1.addActor(brad);

            // Guardar el dueño: Pelicula
            peliculaRepo.save(interstellar);
            peliculaRepo.save(soul);
            peliculaRepo.save(jurassicWorld);
            peliculaRepo.save(batman);
            peliculaRepo.save(formula1);

            System.out.println(">>> DATOS DE PRUEBA INSERTADOS CORRECTAMENTE");
        };
    }
}