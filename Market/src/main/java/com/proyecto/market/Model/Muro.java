package com.proyecto.market.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Muro implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String mensaje;
    private ArrayList<Comentario> comentarios;
    private int cantidadDeLikes;


    public Muro() {}
    public Muro(String mensaje, ArrayList<Comentario> comentarios, int cantidadDeLikes, String id) {
        this.mensaje = mensaje;
        this.comentarios = new ArrayList<Comentario>();
        this.cantidadDeLikes = cantidadDeLikes;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
