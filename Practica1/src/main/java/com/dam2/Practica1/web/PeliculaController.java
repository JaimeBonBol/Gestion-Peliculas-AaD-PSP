package com.dam2.Practica1.web;


import com.dam2.Practica1.domain.Pelicula;
import com.dam2.Practica1.service.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@RequiredArgsConstructor
public class PeliculaController {
    private final PeliculaService service;

   /* @GetMapping
    public List<Pelicula> listar() {
        return service.listar();
    }*/

    @GetMapping("/{id}")
    public Pelicula buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public void agregar(@RequestBody Pelicula pelicula) {
        service.agregar(pelicula);
    }

    @GetMapping("/procesar")
    public String procesarPeliculas() {
        long inicio = System.currentTimeMillis();
        service.tareaLenta("Interstellar");
        service.tareaLenta("The Dark Knight");
        service.tareaLenta("Soul");
        long fin = System.currentTimeMillis();
        return "Tiempo total: " + (fin - inicio) + " ms";
    }

    @GetMapping("/procesarAsync")
    public String procesarAsync() {
        long inicio = System.currentTimeMillis();

        var t1 = service.tareaLenta2("🍿 Interstellar");
        var t2 = service.tareaLenta2("🦇 The Dark Knight");
        var t3 = service.tareaLenta2("🎵 Soul");

        // Espera a que terminen todas las tareas
        CompletableFuture.allOf(t1, t2, t3).join();

        long fin = System.currentTimeMillis();
        return "Tiempo total (asíncrono): " + (fin - inicio) + " ms";
    }

    @GetMapping("/mejoresPeliculas")
    public List<Pelicula> listarMejoresPeliculas(){
        return service.getMejoresPeliculas();
    }

    /**
     * Actividad 4, ejercicio 2
     * @return
     */
    @GetMapping("/reproducirAsync")
    public String reproducirPeliculasAsync(){
        long inicio = System.currentTimeMillis();

        var t1 = service.reproducir("Interstellar");
        var t2 = service.reproducir("The Dark Knight");
        var t3 = service.reproducir("Soul");

        // Espera a que terminen todas las tareas
        CompletableFuture.allOf(t1, t2, t3).join();

        long fin = System.currentTimeMillis();
        return "Tiempo total (asíncrono): " + (fin - inicio) + " ms";
    }

    /**
     * Actividad 4, ejercicio 3, se le pasa el nombre del directorio donde se encuentran los archivos por la ruta, para
     * así hacerlo dinámico, por si hay varios directorios con archivos
     */
    @PostMapping("/cargarPeliculas/{nombreDirectorio}")
    public ResponseEntity<?> cargarPeliculasDirectorio(@PathVariable String nombreDirectorio) throws IOException {
        String rutaDir = "src/main/resources/" + nombreDirectorio;

        service.importarCarpeta(rutaDir);

        return ResponseEntity.status(HttpStatus.CREATED).body("Archivos importados correctamente");
    }

    @GetMapping("/peliculas")
    public List<Pelicula> mostrarPeliculas(){
        return service.listarPeliculas();
    }


    /**
     * Activiad 4, ejercicio 4
     * @param jurados numero de jurados
     * @return mapa con los resultados
     */
    @GetMapping("/api/oscar/{jurados}")
    public HashMap<String, Integer> votacionesOscars(@PathVariable int jurados) throws InterruptedException {
        return service.votacionOscars(jurados);
    }

}
