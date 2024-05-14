package org.tallerjava.moduloSucive.dominio.repositorio;

public class ClienteTelepeaje {
    private TarifaPreferencial paga;
    CuentaPOSTPaga cuentaPostPaga;
    CuentaPREPaga cuentaPrePaga;


    public ClienteTelepeaje(TarifaPreferencial paga, CuentaPOSTPaga cuentaPostPaga,CuentaPREPaga cuentaPrePaga) {
        this.paga = paga;
        this.cuentaPostPaga = cuentaPostPaga;
        this.cuentaPrePaga = cuentaPrePaga;
    }
    public ClienteTelepeaje(){}

    public TarifaPreferencial getPaga() {
        return paga;
    }

    public void setPaga(TarifaPreferencial paga) {
        this.paga = paga;
    }

    public CuentaPOSTPaga getCuentaPostPaga() {
        return cuentaPostPaga;
    }

    public void setCuentaPostPaga(CuentaPOSTPaga cuentaPostPaga) {
        this.cuentaPostPaga = cuentaPostPaga;
    }

    public CuentaPREPaga getCuentaPrePaga() {
        return cuentaPrePaga;
    }

    public void setCuentaPrePaga(CuentaPREPaga cuentaPrePaga) {
        this.cuentaPrePaga = cuentaPrePaga;
    }
}
