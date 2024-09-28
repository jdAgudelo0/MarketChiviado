package com.proyecto.market;

import java.time.LocalDate;

public class Comentario {
    private String mensaje;
    private LocalDate fechaPublicacion;

    public Comentario(String mensaje, LocalDate fechaPublicacion) {
        this.mensaje = mensaje;
        this.fechaPublicacion = fechaPublicacion;
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
