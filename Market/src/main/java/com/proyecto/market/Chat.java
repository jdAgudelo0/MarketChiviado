package com.proyecto.market;

import com.proyecto.market.Interface.AdministrarMensajes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Chat implements AdministrarMensajes {

    private Vendedor receptor;
    private Vendedor emisor;
    private ArrayList<String> mensajes;
    private LocalDate fechaEnvio;

    public Chat(Vendedor receptor, Vendedor emisor, LocalDate fechaEnvio) {
        this.receptor = receptor;
        this.emisor = emisor;
        this.fechaEnvio = LocalDate.now();
        this.mensajes= new ArrayList<String>();
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

    public ArrayList<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<String> mensajes) {
        this.mensajes = mensajes;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;

    }
    @Override
    public void enviarMensaje(Vendedor emisor, Vendedor receptor, String mensaje){
        mensajes.add(String.valueOf(new Comentario(mensaje)));
    }
}
