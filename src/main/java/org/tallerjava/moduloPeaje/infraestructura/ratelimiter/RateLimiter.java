package org.tallerjava.moduloPeaje.infraestructura.ratelimiter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;


@ApplicationScoped
public class RateLimiter {
    private Bucket bucket;
    private boolean activo;

    @PostConstruct
    public void inicializar() {
        activo = false;
        Bandwidth bucketConf = Bandwidth.builder()
                .capacity(20)
                .refillGreedy(120, Duration.ofSeconds(30))
                .build();
        bucket = Bucket.builder().addLimit(bucketConf).build();
    }

    public boolean consumir() {
        boolean result = bucket.tryConsume(1);
        System.out.println("Tokens restantes: " + bucket.getAvailableTokens());
        return result;
    }

    public void activarRateLimiter(boolean estado) {
        System.out.println("RateLimiter estado: " + estado);
        this.activo = estado;
    }

    public boolean isActivo() {
        return this.activo;
    }
}