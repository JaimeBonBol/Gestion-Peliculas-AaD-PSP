//package com.dam2.Practica1.config;
//
//import com.dam2.Practica1.domain.Actor;
//import com.dam2.Practica1.domain.Director;
//import com.dam2.Practica1.domain.FichaTecnica;
//import com.dam2.Practica1.domain.Pelicula;
//import com.dam2.Practica1.repository.ActorRepository;
//import com.dam2.Practica1.repository.DirectorRepository;
//import com.dam2.Practica1.repository.FichaTecnicaRepository;
//import com.dam2.Practica1.repository.PeliculaRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//@Configuration
//public class DataLoader {
//
//    // Este código debe ejecutarse automáticamente justo DESPUÉS de que arranque toda la aplicación.
//    @Bean
//    CommandLineRunner initData(ActorRepository actorRepo,
//                               DirectorRepository directorRepo,
//                               FichaTecnicaRepository fichaRepo,
//                               PeliculaRepository peliculaRepo) {
//
//        return args -> {
//
//
//            // =====================================================
//            // 🚨 PROTECCIÓN: solo cargar datos si NO hay directores
//            // =====================================================
//            if (directorRepo.count() > 0) {
//                System.out.println(">>> Datos ya existentes. NO se carga DataLoader.");
//                return;
//            }
//
//            System.out.println(">>> CARGANDO DATOS DE PRUEBA...");
//
//            // ======================================
//            // DIRECTORES
//            // ======================================
//            Director nolan = new Director(null, "Christopher Nolan", new ArrayList<>());
//            Director docter = new Director(null, "Pete Docter", new ArrayList<>());
//            Director matt = new Director(null, "Matt Reeves", new ArrayList<>());
//            Director joseph = new Director(null, "Joseph Kosinski", new ArrayList<>());
//            Director gareth = new Director(null, "Gareth Edwards", new ArrayList<>());
//
//            directorRepo.save(nolan);
//            directorRepo.save(docter);
//            directorRepo.save(matt);
//            directorRepo.save(joseph);
//            directorRepo.save(gareth);
//
//            // ======================================
//            // FICHAS TÉCNICAS
//            // ======================================
//            // OJO: tus fichas solo tienen (id, director, duracion, pais)
//            FichaTecnica f1 = new FichaTecnica(null, "Christopher Nolan", 169, "EE.UU.");
//            FichaTecnica f2 = new FichaTecnica(null, "Pete Docter", 100, "EE.UU.");
//            FichaTecnica f3 = new FichaTecnica(null, "Matt Reeves", 186, "EE.UU.");
//            FichaTecnica f4 = new FichaTecnica(null, "Joseph Kosinski", 176, "EE.UU.");
//            FichaTecnica f5 = new FichaTecnica(null, "Gareth Edwards", 152, "EE.UU.");
//
//            fichaRepo.save(f1);
//            fichaRepo.save(f2);
//            fichaRepo.save(f3);
//            fichaRepo.save(f4);
//            fichaRepo.save(f5);
//
//            // ======================================
//            // PELÍCULAS
//            // ======================================
//            Pelicula interstellar = new Pelicula();
//            interstellar.setTitulo("Interstellar");
//            interstellar.setActores(new ArrayList<>());
////                    null,
////                    "Interstellar",
////                    169,
////                    LocalDate.of(2014, 11, 7),
////                    "Exploradores espaciales viajan a través de un agujero de gusano...",
////                    9,
////                    f1,
////                    nolan,
////                    new ArrayList<>()  // lista de actores VACÍA
////            );
//
//            Pelicula soul = new Pelicula();
//            soul.setTitulo("Soul");
//            soul.setActores(new ArrayList<>());
////                    null,
////                    "Soul",
////                    100,
////                    LocalDate.of(2020, 12, 25),
////                    "Un músico descubre el verdadero sentido de la vida...",
////                    8,
////                    f2,
////                    docter,
////                    new ArrayList<>()
////            );
//
//            Pelicula batman = new Pelicula();
//            batman.setTitulo("Batman");
//            batman.setActores(new ArrayList<>());
////                    null,
////                    "The Batman",
////                    184,
////                    LocalDate.of(2022, 03, 04),
////                    "El caballero oscuro...",
////                    7,
////                    f3,
////                    matt,
////                    new ArrayList<>()
////            );
//
//            Pelicula formula1 = new Pelicula();
//            formula1.setTitulo("Formula 1");
//            formula1.setActores(new ArrayList<>());
////                    null,
////                    "F1",
////                    176,
////                    LocalDate.of(2025, 06, 27),
////                    "La formula 1 vista desde primera persona...",
////                    6,
////                    f4,
////                    joseph,
////                    new ArrayList<>()
////            );
//
//            Pelicula jurassicWorld = new Pelicula();
//            jurassicWorld.setTitulo("Jurassic World: Rebirth");
//            jurassicWorld.setActores(new ArrayList<>());
////                    null,
////                    "Jurassic World: Rebirth",
////                    152,
////                    LocalDate.of(2025, 07, 02),
////                    "El renacer de los dinosaurios...",
////                    9,
////                    f5,
////                    gareth,
////                    new ArrayList<>()
////            );
//
//            peliculaRepo.save(interstellar);
//            peliculaRepo.save(soul);
//            peliculaRepo.save(batman);
//            peliculaRepo.save(formula1);
//            peliculaRepo.save(jurassicWorld);
//
//            // ======================================
//            // ACTORES
//            // ======================================
//            Actor matthew = new Actor(null, "Matthew McConaughey", new ArrayList<>());
//            Actor hathaway = new Actor(null, "Anne Hathaway", new ArrayList<>());
//            Actor foxx = new Actor(null, "Jamie Foxx", new ArrayList<>());
//            Actor scarlett = new Actor(null, "Scarlett Johansson", new ArrayList<>());
//            Actor brad = new Actor(null, "Brad Pitt", new ArrayList<>());
//            Actor robert = new Actor(null, "Robert Pattinson", new ArrayList<>());
//
//            actorRepo.save(matthew);
//            actorRepo.save(hathaway);
//            actorRepo.save(foxx);
//            actorRepo.save(scarlett);
//            actorRepo.save(brad);
//            actorRepo.save(robert);
//
//            // ======================================
//            // RELACIÓN MANY-TO-MANY
//            // (Usando tus métodos de sincronización)
//            // ======================================
//            // COMO PROFESOR LO TIENE
//            matthew.addPelicula(interstellar);
//            hathaway.addPelicula(interstellar);
//            foxx.addPelicula(soul);
//            scarlett.addPelicula(jurassicWorld);
//            robert.addPelicula(batman);
//            brad.addPelicula(formula1);
//
//
//            peliculaRepo.save(interstellar);
//            peliculaRepo.save(soul);
//            peliculaRepo.save(jurassicWorld);
//            peliculaRepo.save(batman);
//            peliculaRepo.save(formula1);
//
//
////            // RELACIÓN MANY-TO-MANY
////            interstellar.addActor(matthew);
////            interstellar.addActor(hathaway);
////            soul.addActor(foxx);
////            jurassicWorld.addActor(scarlett);
////            batman.addActor(robert);
////            formula1.addActor(brad);
////
////            // Guardar el dueño: Pelicula
////            peliculaRepo.save(interstellar);
////            peliculaRepo.save(soul);
////            peliculaRepo.save(jurassicWorld);
////            peliculaRepo.save(batman);
////            peliculaRepo.save(formula1);
//
//            System.out.println(">>> DATOS DE PRUEBA INSERTADOS CORRECTAMENTE");
//        };
//    }
//}

package com.dam2.Practica1.config;

import com.dam2.Practica1.domain.*;
import com.dam2.Practica1.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PeliculaRepository peliculaRepository;
    private final ActorRepository actorRepository;
    private final CategoriaRepository categoriaRepository;
    private final DirectorRepository directorRepository;
    private final IdiomaRepository idiomaRepository;
    private final PlataformaRepository plataformaRepository;
    private final FichaTecnicaRepository fichaTecnicaRepository;
    private final FuncionRepository funcionRepository;
    private final CriticaRepository criticaRepository;

    @Override
    public void run(String... args) throws Exception {

        // -------------------
        // DIRECTORES
        // -------------------
        Director nolan = new Director();
        nolan.setNombre("Christopher Nolan");
        directorRepository.save(nolan);

        Director docter = new Director();
        docter.setNombre("Pete Docter");
        directorRepository.save(docter);

        // -------------------
        // ACTORES
        // -------------------
        Actor brad = new Actor();
        brad.setNombre("Brad Pitt");
        actorRepository.save(brad);

        Actor robert = new Actor();
        robert.setNombre("Robert Pattinson");
        actorRepository.save(robert);

        Actor leonardo = new Actor();
        leonardo.setNombre("Leonardo DiCaprio");
        actorRepository.save(leonardo);

        // -------------------
        // CATEGORÍAS
        // -------------------
        Categoria accion = new Categoria();
        accion.setNombre("Acción");
        categoriaRepository.save(accion);

        Categoria aventura = new Categoria();
        aventura.setNombre("Aventura");
        categoriaRepository.save(aventura);

        Categoria animacion = new Categoria();
        animacion.setNombre("Animación");
        categoriaRepository.save(animacion);

        // -------------------
        // IDIOMAS
        // -------------------
        Idioma espanol = new Idioma();
        espanol.setNombre("Español");
        idiomaRepository.save(espanol);

        Idioma ingles = new Idioma();
        ingles.setNombre("Inglés");
        idiomaRepository.save(ingles);

        // -------------------
        // PLATAFORMAS
        // -------------------
        Plataforma netflix = new Plataforma();
        netflix.setNombre("Netflix");
        plataformaRepository.save(netflix);

        Plataforma hbo = new Plataforma();
        hbo.setNombre("HBO Max");
        plataformaRepository.save(hbo);

        // -------------------
        // FICHAS TÉCNICAS
        // -------------------
        FichaTecnica ficha1 = new FichaTecnica();
        ficha1.setDirector("Christopher Nolan");
        ficha1.setDuracion(148);
        ficha1.setPais("EE.UU.");
        fichaTecnicaRepository.save(ficha1);

        FichaTecnica ficha2 = new FichaTecnica();
        ficha2.setDirector("Pete Docter");
        ficha2.setDuracion(100);
        ficha2.setPais("EE.UU.");
        fichaTecnicaRepository.save(ficha2);

        FichaTecnica ficha3 = new FichaTecnica();
        ficha3.setDirector("Jose Perez");
        ficha3.setDuracion(200);
        ficha3.setPais("ESP.");
        fichaTecnicaRepository.save(ficha3);


        // -------------------
        // FUNCIONES
        // -------------------
        Funcion func1 = new Funcion();
        func1.setFecha(LocalDate.now());
        funcionRepository.save(func1);

        Funcion func2 = new Funcion();
        func2.setFecha(LocalDate.now());
        funcionRepository.save(func2);

        // -------------------
        // CRÍTICAS
        // -------------------
        Critica critica1 = new Critica();
        critica1.setComentario("Excelente película, impresionante visualmente.");
        critica1.setNota(9.0);

        criticaRepository.save(critica1);

        Critica critica2 = new Critica();
        critica2.setComentario("Buena historia pero algo confusa.");
        critica2.setNota(7.5);
        criticaRepository.save(critica2);

        // -------------------
        // PELÍCULAS
        // -------------------
        Pelicula inception = new Pelicula();
        inception.setTitulo("Inception");
        inception.setDuracion(148);
        inception.setFechaEstreno(LocalDate.of(2010, 7, 16));
        inception.setSinopsis("Un ladrón especializado en robar secretos a través de los sueños.");
        inception.setValoracion(9);
        inception.setFichaTecnica(ficha1);
        inception.setDirector(nolan);

        // Many-to-Many con helpers
        inception.addActor(brad);
        inception.addActor(robert);
        inception.addCategoria(accion);
        inception.addCategoria(aventura);
        inception.addIdioma(espanol);
        inception.addIdioma(ingles);
        inception.addPlataforma(netflix);

        // One-to-Many
        inception.getFunciones().add(func1);
        func1.setPelicula(inception);

        inception.getCriticas().add(critica1);
        critica1.setPelicula(inception);

        peliculaRepository.save(inception);

        Pelicula soul = new Pelicula();
        soul.setTitulo("Soul");
        soul.setDuracion(100);
        soul.setFechaEstreno(LocalDate.of(2020, 12, 25));
        soul.setSinopsis("Un músico descubre el sentido de la vida más allá de la muerte.");
        soul.setValoracion(8);
        soul.setFichaTecnica(ficha2);
        soul.setDirector(docter);

        // Relaciones Many-to-Many con helpers
        soul.addActor(leonardo);
        soul.addCategoria(animacion);
        soul.addIdioma(ingles);
        soul.addPlataforma(hbo);

        // One-to-Many
        soul.getFunciones().add(func2);
        func2.setPelicula(soul);

        soul.getCriticas().add(critica2);
        critica2.setPelicula(soul);

        peliculaRepository.save(soul);


        System.out.println("✅ Datos de prueba COMPLETOS cargados correctamente");
    }
}
