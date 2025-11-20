package com.dam2.Practica1.service;

import com.dam2.Practica1.domain.Pelicula;
import com.dam2.Practica1.repository.PeliculaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Getter
@RequiredArgsConstructor
public class ImportarArchivoService {
    private final PeliculaRepository peliculaRepository;

    @Async("taskExecutor")
    public CompletableFuture<Void> importarCsvAsync(Path fichero) {
        try {
            System.out.println("Procesando CSV: " + fichero + " en " + Thread.currentThread().getName());

            List<Pelicula> lista = new ArrayList<>();
            List<Pelicula> peliculasBd = peliculaRepository.findAll();

            List<String> lineas = Files.readAllLines(fichero);
            lineas.remove(0); // suponemos encabezado

            for (String linea : lineas) {
                String[] campos = linea.split(";");
                Pelicula p = new Pelicula();
                p.setTitulo(campos[0]);
                p.setDuracion(Integer.parseInt(campos[1]));
                p.setFechaEstreno(LocalDate.parse(campos[2]));
                p.setSinopsis(campos[3]);
                lista.add(p);

                /* PARA QUE NO SE IMPORTEN LAS PELICULAS QUE YA EXISTAN EN LA BASE DE DATOS
                boolean existe = false;
                for (Pelicula peli : peliculasBd) {
                    if (p.getTitulo().equals(peli.getTitulo())) {
                        existe = true;
                        break; // ya existe, no hace falta seguir buscando
                    }
                }

                if (!existe) {
                    lista.add(p); // solo se añade si no estaba en la BD
                }
                 */
            }

            peliculaRepository.saveAll(lista);

            System.out.println("Finalizado CSV: " + fichero);

        } catch (Exception e) {
            System.err.println("Error en CSV " + fichero + ": " + e.getMessage());
        }

        return CompletableFuture.completedFuture(null);
    }

    @Async("taskExecutor")
    public CompletableFuture<Void> importarXmlAsync(Path fichero) {
        try {
            System.out.println("Procesando XML: " + fichero + " en " + Thread.currentThread().getName());

            List<Pelicula> lista = new ArrayList<>();
            List<Pelicula> peliculasBd = peliculaRepository.findAll();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(fichero.toFile());
            NodeList nodos = doc.getElementsByTagName("pelicula");

            for (int i = 0; i < nodos.getLength(); i++) {
                Element e = (Element) nodos.item(i);

                Pelicula p = new Pelicula();
                p.setTitulo(e.getElementsByTagName("titulo").item(0).getTextContent());
                p.setDuracion(Integer.parseInt(e.getElementsByTagName("duracion").item(0).getTextContent()));
                p.setFechaEstreno(LocalDate.parse(e.getElementsByTagName("fechaEstreno").item(0).getTextContent()));
                p.setSinopsis(e.getElementsByTagName("sinopsis").item(0).getTextContent());
                lista.add(p);

                /* PARA QUE NO SE IMPORTEN LAS PELICULAS QUE YA EXISTAN EN LA BASE DE DATOS
                boolean existe = false;
                for (Pelicula peli : peliculasBd) {
                    if (p.getTitulo().equals(peli.getTitulo())) {
                        existe = true;
                        break; // ya existe, no añadir
                    }
                }

                if (!existe) {
                    lista.add(p); // solo añadimos si no existe
                }
                 */
            }

            peliculaRepository.saveAll(lista);

            System.out.println("Finalizado XML: " + fichero);

        } catch (Exception e) {
            System.err.println("Error en XML " + fichero + ": " + e.getMessage());
        }

        return CompletableFuture.completedFuture(null);
    }
}
