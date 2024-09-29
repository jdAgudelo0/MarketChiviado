package com.proyecto.market;

import java.util.ArrayList;

public class Muro {
    private String mensaje;
    private ArrayList<Comentario> comentarios;
    private int cantidadDeLikes;

    public Muro() {}
    public Muro(String mensaje, ArrayList<Comentario> comentarios, int cantidadDeLikes) {
        this.mensaje = mensaje;
        this.comentarios = new ArrayList<Comentario>();
        this.cantidadDeLikes = cantidadDeLikes;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getCantifdadDeLikes() {
        return cantidadDeLikes;
    }
    public void setCantifdadDeLikes(int cantifdadDeLikes) {
        this.cantidadDeLikes = cantifdadDeLikes;
    }
}
