package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.VentaException;
import com.proyecto.market.Model.Venta;

import java.util.ArrayList;

public class VentaController {

    ModelFactory modelFactory;

    public VentaController(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }


    public int crearVenta(Venta venta) throws VentaException {
        if (modelFactory.addVenta(venta) == 1) {
            return 1;
        }else {
            return 0;
        }
    }

    public int eliminarVenta(Venta venta) throws VentaException {
        if (modelFactory.deleteVenta(venta) == 1) {
            return 1;
        }else {
            return 0;
        }
    }

    public ArrayList<Venta> mostrarVentas() {
        return modelFactory.obtenerVentas();
    }

    public Venta buscarVenta(String codigo){
        return modelFactory.hallarVenta(codigo);
    }


}
