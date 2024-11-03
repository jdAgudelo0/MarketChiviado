package com.proyecto.market.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Estadisticas implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Venta> ventas;
    private Producto producto;

    public Estadisticas(Venta ventas) {
        this.ventas= new ArrayList<Venta>();
    }

    public void getReporteVentas(Vendedor vendedor, Date fechaPublicacion, LocalDate fechaVenta){

    }

    public void getReporteLikesProducto(Producto producto){

    }


}
