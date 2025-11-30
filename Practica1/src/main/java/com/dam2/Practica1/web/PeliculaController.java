package com.dam2.Practica1.web;


import com.dam2.Practica1.DTO.PeliculaRequestDTO;
import com.dam2.Practica1.DTO.PeliculaResponseDTO;
import com.dam2.Practica1.domain.Pelicula;
import com.dam2.Practica1.service.PeliculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final PeliculaService peliculaService;

    /**
     * CRUD
     *
     */

    @GetMapping
    public List<PeliculaResponseDTO> listarPeliculas(){
        return peliculaService.listarPeliculas();
    }

    @GetMapping("/{id}")
    public PeliculaResponseDTO buscarPeliculaPorId(@PathVariable Long id){
        return peliculaService.buscarPeliculaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeliculaResponseDTO agregarPelicula(@RequestBody @Valid PeliculaRequestDTO peliculaRequestDTO){
        return peliculaService.agregarPelicula(peliculaRequestDTO);
    }

    @PutMapping("/{id}")
    public PeliculaResponseDTO actualizarPelicula(@PathVariable Long id, @RequestBody @Valid PeliculaRequestDTO peliculaRequestDTO){
        return peliculaService.actualizarPelicula(id, peliculaRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPelicula(@PathVariable Long id){
        peliculaService.eliminarPelicula(id);
    }


    @GetMapping("/procesar")
    public String procesarPeliculas() {
        long inicio = System.currentTimeMillis();
        peliculaService.tareaLenta("Interstellar");
        peliculaService.tareaLenta("The Dark Knight");
        peliculaService.tareaLenta("Soul");
        long fin = System.currentTimeMillis();
        return "Tiempo total: " + (fin - inicio) + " ms";
    }

    @GetMapping("/procesarAsync")
    public String procesarAsync() {
        long inicio = System.currentTimeMillis();

        var t1 = peliculaService.tareaLenta2("🍿 Interstellar");
        var t2 = peliculaService.tareaLenta2("🦇 The Dark Knight");
        var t3 = peliculaService.tareaLenta2("🎵 Soul");

        // Espera a que terminen todas las tareas
        CompletableFuture.allOf(t1, t2, t3).join();

        long fin = System.currentTimeMillis();
        return "Tiempo total (asíncrono): " + (fin - inicio) + " ms";
    }

    @GetMapping("/mejoresPeliculas")
    public List<Pelicula> listarMejoresPeliculas(){
        return peliculaService.getMejoresPeliculas();
    }

    /**
     * Actividad 4, ejercicio 2
     * @return
     */
    @GetMapping("/reproducirAsync")
    public String reproducirPeliculasAsync(){
        long inicio = System.currentTimeMillis();

        var t1 = peliculaService.reproducir("Interstellar");
        var t2 = peliculaService.reproducir("The Dark Knight");
        var t3 = peliculaService.reproducir("Soul");

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

        peliculaService.importarCarpeta(rutaDir);

        return ResponseEntity.status(HttpStatus.CREATED).body("Archivos importados correctamente");
    }


    /**
     * Activiad 4, ejercicio 4
     * @param jurados numero de jurados
     * @return mapa con los resultados
     */
    @GetMapping("/api/oscar/{jurados}")
    public HashMap<String, Integer> votacionesOscars(@PathVariable int jurados) throws InterruptedException {
        return peliculaService.votacionOscars(jurados);
    }

}
