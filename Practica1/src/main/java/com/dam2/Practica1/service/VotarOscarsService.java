package com.dam2.Practica1.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

@Service
@Getter
@RequiredArgsConstructor
public class VotarOscarsService {
    private HashMap<String, Integer> votacion = new HashMap<>();
    Semaphore sem = new Semaphore(5);
    Random random = new Random();

    @Async("taskExecutor")
    public CompletableFuture<Void> votar(List<String> titulosCandidatas, int idJurado){
        try {
            System.out.println("Jurado " + idJurado + " procede a votar");

            // Limita a 5 jurados simultáneos solo para actualizar el HashMap
            sem.acquire();
            try {
                for (String titulo : titulosCandidatas){
                    int puntuacion = random.nextInt(11); // 0–10 puntos
                    System.out.println("Jurado " + idJurado + " da " + puntuacion + " puntos a " + titulo);

                    synchronized (votacion){
                        votacion.put(titulo, votacion.getOrDefault(titulo, 0) + puntuacion);
                    }
                }
            } finally {
                sem.release();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return CompletableFuture.completedFuture(null);
    }
}
