package com.proyecto.market;

import com.proyecto.market.Interface.AdministrarMensajes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Chat implements AdministrarMensajes {

    private Vendedor receptor;
    private Vendedor emisor;
    private ArrayList<Comentario> mensajes;
    private LocalDate fechaEnvio;

    public Chat(Vendedor receptor, Vendedor emisor) {
        this.receptor = receptor;
        this.emisor = emisor;
        this.mensajes = new ArrayList<Comentario>();
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

    public ArrayList<Comentario> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Comentario> mensajes) {
        this.mensajes = mensajes;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;

    }

    @Override
    public void enviarMensaje( String mensaje) {
        mensajes.add(new Comentario(mensaje));

    }
}
