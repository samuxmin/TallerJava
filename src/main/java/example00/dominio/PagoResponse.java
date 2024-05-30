package example00.dominio;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagoResponse", propOrder = { "exito", "mensaje", "idPago" })
public class PagoResponse {
    @XmlElement(required = true)
    private boolean exito;

    @XmlElement(required = true)
    private String mensaje;

    private Integer idPago;

    // Constructor
    public PagoResponse() {
    }

    public PagoResponse(boolean exito, String mensaje, Integer idPago) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.idPago = idPago;
    }

    // Getters y setters
    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }
}