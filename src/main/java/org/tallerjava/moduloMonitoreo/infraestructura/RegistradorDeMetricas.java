package org.tallerjava.moduloMonitoreo.infraestructura;

import java.time.Duration;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistradorDeMetricas {
    public static final String PEAJE_COUNTER_PAGO_SUCIVE ="peajePagoSucive";
    public static final String GESTION_COUNTER_PRE_PAGO ="gestionPrePago";
    public static final String GESTION_COUNTER_POST_PAGO ="gestionPostPago";
    public static final String PEAJE_COUNTER_VEHICULO_NACIONAL ="peajeVehiculoNacional";
    public static final String PEAJE_COUNTER_VEHICULO_EXTRANJERO ="peajeVehiculoExtranjero";

    public static final String PEAJE_COUNTER_VEHICULO_NO_ENCONTRADO ="peajeVehiculoNoEncontrado";

    private InfluxConfig config;

    @PostConstruct
    public void init() {
        //configuración del repositorio de metricas (influxdb)
        config = new InfluxConfig() {
            @Override
            public String get(String s) {
                return null;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String db() {
                return "metricasTallerJava";
            }
        };


    }

    public void incrementarCounter(String nombreCounter) {
        MeterRegistry meterRegistry;
        meterRegistry = new InfluxMeterRegistry(config, Clock.SYSTEM);
        meterRegistry.counter(nombreCounter).increment();
    }
}
