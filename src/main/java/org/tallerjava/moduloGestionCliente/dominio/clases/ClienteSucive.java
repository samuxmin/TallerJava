package org.tallerjava.moduloGestionCliente.dominio.clases;

public class ClienteSucive {
    private TarifaComun paga;

    public ClienteSucive(TarifaComun paga) {
        this.paga = paga;
    }

    public ClienteSucive() {
    }

    public TarifaComun getPaga() {
        return paga;
    }
    public void setPaga(TarifaComun paga) {
        this.paga = paga;
    }
}