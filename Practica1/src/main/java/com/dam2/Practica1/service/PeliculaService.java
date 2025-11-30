package com.dam2.Practica1.service;

import com.dam2.Practica1.DTO.PeliculaRequestDTO;
import com.dam2.Practica1.DTO.PeliculaResponseDTO;
import com.dam2.Practica1.domain.Pelicula;
import com.dam2.Practica1.mapper.PeliculaMapper;
import com.dam2.Practica1.repository.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;
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
    private final ImportarArchivoService importarArchivoService;
    private final VotarOscarsService votarOscarsService;

    private final PeliculaRepository peliculaRepository;
    private final PeliculaMapper peliculaMapper;

    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final CategoriaRepository categoriaRepository;
    private final IdiomaRepository idiomaRepository;
    private final PlataformaRepository plataformaRepository;
    private final CriticaRepository criticaRepository;
    private final FuncionRepository funcionRepository;


    /**
     * CRUD
     */

    public List<PeliculaResponseDTO> listarPeliculas(){
        List<Pelicula> peliculasBD = peliculaRepository.findAll();
        List<PeliculaResponseDTO> peliculasResponseDTO = new ArrayList<>();

        for (Pelicula pelicula : peliculasBD){
            peliculasResponseDTO.add(peliculaMapper.toDto(pelicula));
        }

        return peliculasResponseDTO;
    }


    public PeliculaResponseDTO buscarPeliculaPorId(Long id){
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id));

        return peliculaMapper.toDto(pelicula);
    }

    @Transactional
    public PeliculaResponseDTO agregarPelicula(PeliculaRequestDTO peliculaRequestDTO){
        Pelicula pelicula = peliculaMapper.toEntity(peliculaRequestDTO);

        peliculaRepository.save(pelicula);

        return peliculaMapper.toDto(pelicula);
    }


    // Actualiza la película, si no es posible se revierte (transacctional). Si no la encuentra lanza 404
    @Transactional
    public PeliculaResponseDTO actualizarPelicula(Long id, PeliculaRequestDTO peliculaRequestDTO){
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id));

        peliculaMapper.updateEntity(pelicula, peliculaRequestDTO);

        peliculaRepository.save(pelicula);

        return peliculaMapper.toDto(pelicula);
    }


    // Elimina la película y no devuelve nada, si no la encuentra lanza código de error 404
    @Transactional
    public void eliminarPelicula(Long id){
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id));

        peliculaRepository.delete(pelicula);
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
