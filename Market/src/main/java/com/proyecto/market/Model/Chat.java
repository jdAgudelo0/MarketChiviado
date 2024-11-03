package com.proyecto.market.Model;


import com.proyecto.market.Model.Interface.AdministrarMensajes;

import java.io.Serializable;
import java.time.LocalDate;

public class Chat implements AdministrarMensajes, Serializable {

    private static final long serialVersionUID = 1L;
    private Vendedor receptor;
    private Vendedor emisor;
    private String mensaje;
    private LocalDate fechaEnvio;

    public Chat(Vendedor receptor, Vendedor emisor, String mensaje, LocalDate fechaEnvio) {
        this.receptor = receptor;
        this.emisor = emisor;
        this.mensaje = mensaje;
        this.fechaEnvio = LocalDate.now();
    }

    public Chat() {}

    public Vendedor getReceptor() {
        return receptor;
    }

    public void setReceptor(Vendedor receptor) {
        this.receptor = receptor;
    }

    public Vendedor getEmisor() {
        return emisor;
    }

    public void setEmisor(Vendedor emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;

    }

    @Override
    public void enviarMensaje(String mensaje) {

    }
}
