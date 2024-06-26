package org.tallerjava.moduloPeaje.infraestructura.messaging;

import org.jboss.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@ApplicationScoped
public class EnviarPeajeQueueUtil {
    Logger log = Logger.getLogger(EnviarPeajeQueueUtil.class);

    @Inject
    private JMSContext jmsContext;

    @Resource (lookup = "java:jboss/exported/jms/queue/servicioPago")
    private Queue queuePeajesRealizados;

    public void enviarMensaje(String mensaje) {
        log.infof("Inicio envio mensaje: %s", mensaje);

        jmsContext.createProducer().send(queuePeajesRealizados, mensaje);
        log.infof("Fin envio mensaje: %s", mensaje);
    }
}
