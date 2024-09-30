package com.proyecto.market.Model;

import java.time.LocalDate;

public class Comentario {
    private String mensaje;
    private LocalDate fechaPublicacion;

    public Comentario(String mensaje) {
        this.mensaje = mensaje;
        this.fechaPublicacion = LocalDate.now();
    }

    public Comentario(){}

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
