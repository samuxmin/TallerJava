package org.tallerjava.moduloMediosDePago.dominio;


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
