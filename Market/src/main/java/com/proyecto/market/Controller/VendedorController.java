package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.ProductoException;
import com.proyecto.market.Exceptions.VendedorException;
import com.proyecto.market.Model.Producto;
import com.proyecto.market.Model.Vendedor;

import java.util.ArrayList;

public class VendedorController {

    ModelFactory modelFactory;

    public VendedorController() {
        modelFactory = ModelFactory.getInstance();
    }

    public int crearVendedor(Vendedor vendedor) throws VendedorException {
        if (modelFactory.addVendedor(vendedor) == 1) {
            return 1;
        }else {
            return 0;
        }
    }

    public int eliminarVendedor(Vendedor vendedor) throws VendedorException {
        if (modelFactory.deleteVendedor(vendedor) == 1) {
            return 1;
        }else {
            return 0;
        }
    }

    public int modificarVendedor(Vendedor vendedor, String cedulaVendedorAntiguo) throws VendedorException {

        if (modelFactory.updateVendedor(vendedor, cedulaVendedorAntiguo)) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public ArrayList<Vendedor> mostrarVendedores() throws VendedorException {
        return modelFactory.obtenerVendedores();
    }

    public Vendedor buscarVendedor(String cedula) throws VendedorException {
        return modelFactory.hallarVendedor(cedula);
    }

    public Producto buscarProducto(String codigo) throws ProductoException {
        return modelFactory.hallarProducto(codigo);
    }

}
