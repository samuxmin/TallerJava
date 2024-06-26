package org.tallerjava.moduloMediosDePago.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name="ClienteTelepeajePago")
public class ClienteTelepeaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    @OneToOne(cascade = CascadeType.ALL)
    private CuentaPOSTPaga cuentaPostPaga;
    @OneToOne
    private CuentaPREPaga cuentaPrePaga;


    public ClienteTelepeaje( CuentaPOSTPaga cuentaPostPaga,CuentaPREPaga cuentaPrePaga) {
  
        this.cuentaPostPaga = cuentaPostPaga;
        this.cuentaPrePaga = cuentaPrePaga;
    }
    public ClienteTelepeaje(){}





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
