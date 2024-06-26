package org.tallerjava.moduloPeaje.infraestructura.messaging;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.aplicacion.PeajeService;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = "java:app/jms/PeajeQueue"),
        @ActivationConfigProperty(
                propertyName = "maxSession",
                propertyValue = "1")
    }
)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class NuevoPeajeConsumer implements MessageListener {
    private static final Logger log = Logger.getLogger(NuevoPeajeConsumer.class);

    @Inject
    private PeajeService peajeService;

    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);
            log.infof("Nuevo peaje recibido: %s, body: %s", message.toString(), body);

            PeajeRealizadoMessage peaje = PeajeRealizadoMessage.buildFromJson(body);
            Thread.sleep(1000); // Simulamos un proceso lento (1 segundo
            peajeService.procesarVehiculoNacional(peaje.tag(),peaje.matricula());

        } catch (JMSException | InterruptedException e) {
            log.error("Error al consumir el mensaje de peaje", e);
        }
    }
}
