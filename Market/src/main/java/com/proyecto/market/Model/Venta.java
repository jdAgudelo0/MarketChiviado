package com.proyecto.market.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private Vendedor vendedor;
    private ArrayList<Producto> productosVenta;
    private LocalDate fechaPublicacion;
    private LocalDate fechaVenta;
    private float total;

    public Venta(String codigo, Vendedor vendedor, ArrayList<Producto> productosVenta, LocalDate fechaPublicacion, LocalDate fechaVenta, float total) {
        this.codigo = codigo;
        this.vendedor = vendedor;
        this.productosVenta = productosVenta;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    public Venta() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Producto> getProductosVenta() {
        return productosVenta;
    }

    public void setProductosVenta(ArrayList<Producto> productosVenta) {
        this.productosVenta = productosVenta;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
