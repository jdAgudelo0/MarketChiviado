package com.proyecto.market;

import java.time.LocalDate;

public class Venta {

    private Vendedor vendedor;
    private Date fechaPublicacion;
    private LocalDate fechaVenta;

    public Venta(Vendedor vendedor, Date fechaPublicacion, Localate fechaVenta) {
        this.vendedor = vendedor;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaVenta = fechaVenta;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
