package com.proyecto.market.Model;

import java.util.Date;

public class Venta {

    private Vendedor vendedor;
    private Date fechaPublicacion;
    private Date fechaVenta;

    public Venta(Vendedor vendedor, Date fechaPublicacion, Date fechaVenta) {
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

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
