package com.dam2.Practica1.service;

import com.dam2.Practica1.domain.Pelicula;
import com.dam2.Practica1.repository.PeliculaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

@Service
@Getter
@RequiredArgsConstructor
public class PeliculaService {
    private final List<Pelicula> peliculas = new ArrayList<>();
    private final PeliculaRepository peliculaRepository;
    private final ImportarArchivoService importarArchivoService;
    private final VotarOscarsService votarOscarsService;

    /*public PeliculaService() {
        peliculas.add(new Pelicula(1L, "Interstellar", 169, LocalDate.of(2014, 11, 7),
                "Exploradores espaciales buscan un nuevo hogar para la humanidad.", 10, null, null, null));
        peliculas.add(new Pelicula(2L, "The Dark Knight", 152, LocalDate.of(2008, 7, 18),
                "Batman enfrenta al Joker en una lucha por el alma de Gotham.", 5, null, null, null));
        peliculas.add(new Pelicula(3L, "Soul", 100, LocalDate.of(2020, 12, 25),
                "Un músico descubre el sentido de la vida más allá de la muerte.", 8, null, null, null));
    }*/

    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula buscarPorId(Long id) {
        for (Pelicula p : peliculas) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
        /*
        * return peliculas.stream()                 // convierte la lista en un flujo de datos
        .filter(p -> p.getId().equals(id)) // se queda solo con las películas cuyo id coincide
        .findFirst()                       // toma la primera coincidencia (si existe)
        .orElse(null);                     // devuelve esa película o null si no hay
        * */
    }

    public void agregar(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public String tareaLenta(String titulo) {
        try {
            System.out.println("Iniciando tarea para " + titulo + " en " + Thread.currentThread().getName());
            Thread.sleep(3000); // simula proceso lento (3 segundos)
            System.out.println("Terminando tarea para " + titulo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Procesada " + titulo;
    }

    @Async("taskExecutor")
    public CompletableFuture<String> tareaLenta2(String titulo) {
        try {
            System.out.println("Iniciando " + titulo + " en " + Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("Terminando " + titulo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture("Procesada " + titulo);
    }

    public List<Pelicula> getMejoresPeliculas() {
        List<Pelicula> mejoresPeliculas = new ArrayList<>();

        for (Pelicula p : peliculas){
            if (p.getValoracion() > 5){
                mejoresPeliculas.add(p);
            }
        }

        return mejoresPeliculas;
    }

    @Async("taskExecutor")
    public CompletableFuture<String> reproducir(String titulo) {
        long inicio = System.currentTimeMillis();

        try {
            Random random = new Random();
            int numeroAleatorio = random.nextInt(5) + 1;


            System.out.println("Iniciando " + titulo + " en " + Thread.currentThread().getName());
            Thread.sleep(numeroAleatorio * 1000);
            System.out.println("Terminado la película " + titulo);



        } catch (InterruptedException e) {
            System.out.println(e.getMessage());;
        }

        long fin = System.currentTimeMillis();
        long tiempoTotalSeg = (fin - inicio) / 1000;
        return CompletableFuture.completedFuture("Terminada " + titulo + " en " + tiempoTotalSeg + " segundos");
    }

    /**
     * Metodos necesarios para la actividad 3
     * Un método @Async NO funciona si lo llamas desde la misma clase.
     * Porque Spring solo aplica la lógica asíncrona cuando el método es llamado desde otro bean.
     */
    public void importarCarpeta(String rutaCarpeta) throws IOException {
        long inicio = System.currentTimeMillis();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        try (Stream<Path> paths = Files.list(Paths.get(rutaCarpeta))) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                String nombre = path.toString().toLowerCase();
                if (nombre.endsWith(".csv") || nombre.endsWith(".txt")) {
                    futures.add(importarArchivoService.importarCsvAsync(path));
                } else if (nombre.endsWith(".xml")) {
                    futures.add(importarArchivoService.importarXmlAsync(path));
                }
            });
        }
        // Esperar a que terminen todas las tareas asíncronas
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        long fin = System.currentTimeMillis();
        System.out.println("Importación completa en " + (fin - inicio) + " ms");
    }



    /**
     * Metodos necesarios para la actividad 4
     */
//    private HashMap<String, Integer> votacion = new HashMap<>();
//    Semaphore sem = new Semaphore(5);
//    Random random = new Random();

    public HashMap<String, Integer> votacionOscars(int jurados){
        long inicio = System.currentTimeMillis();

        // Limpiar el HashMap
        votarOscarsService.getVotacion().clear();

        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<String> titulosPeliculas = new ArrayList<>();

        for (Pelicula pelicula : peliculas){
            titulosPeliculas.add(pelicula.getTitulo());
        }

        List<CompletableFuture<Void>> tareas = new ArrayList<>();

        // Seghún el numero de jurados que le pasemos se ejecutará x veces.
        for (int i = 0; i < jurados; i++) {
            tareas.add(votarOscarsService.votar(titulosPeliculas, i + 1));
        }

        // Esperar a que terminen todos los jurados
        CompletableFuture.allOf(tareas.toArray(new CompletableFuture[0])).join();

        System.out.println("Votaciones finalizadas, resultado:");
        System.out.println(votarOscarsService.getVotacion());

        long fin = System.currentTimeMillis();
        long tiempoTotalSeg = (fin - inicio) ;

        System.out.println("Las votaciones han durado " + tiempoTotalSeg + " milisegundos, gracias por su paciencia");

        return votarOscarsService.getVotacion();

    }



}
