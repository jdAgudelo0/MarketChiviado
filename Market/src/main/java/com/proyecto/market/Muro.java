package com.proyecto.market;

import java.util.ArrayList;

public class Muro {
    private String mensaje;
    private ArrayList<Comentario> comentarios;
    private int cantifdadDeLikes;

    public Muro() {}
    public Muro(String mensaje, ArrayList<Comentario> comentarios, int cantifdadDeLikes) {
        this.mensaje = mensaje;
        this.comentarios = new ArrayList<Comentario>();
        this.cantifdadDeLikes = cantifdadDeLikes;
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
        return cantifdadDeLikes;
    }
    public void setCantifdadDeLikes(int cantifdadDeLikes) {
        this.cantifdadDeLikes = cantifdadDeLikes;
    }
}
