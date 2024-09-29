package com.proyecto.market;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Estadisticas {

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
