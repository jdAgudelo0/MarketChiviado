package com.proyecto.market.Controller;
import com.proyecto.market.Exceptions.ComentarioException;
import com.proyecto.market.Exceptions.ProductoException;
import com.proyecto.market.Model.Comentario;
import com.proyecto.market.Model.Producto;
import com.proyecto.market.Model.Vendedor;


import java.util.ArrayList;

public class ProductoController {

    ModelFactory modelFactory;

    Vendedor vendedor;

    public ProductoController() {
        this.modelFactory = modelFactory.getInstance();
        vendedor = modelFactory.getVendedor();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public int crearProducto(Producto producto) throws ProductoException {
        if (modelFactory.addProducto(producto) == 1) {
            return 1;
        }else {
            return 0;
        }

    }

    public int eliminarProducto(Producto producto) throws ProductoException {
        if (modelFactory.deleteProducto(producto) == 1) {
            return 1;
        }else {
            return 0;
        }
    }

    public int modificarProducto(Producto producto, String codigo) throws ProductoException {
        if (modelFactory.updateProducto(producto, codigo)) {
            return 1;
        }
        else {
            return 0;}}


    public ArrayList<Producto> mostrarProductos(){
        return modelFactory.obtenerProductos();
    }

    public ArrayList<Producto> mostrarProductosVendedor(){
        return modelFactory.obtenerProductosVendedor(vendedor.getCedula());
    }

    public Producto buscarProducto(String codigo) throws ProductoException {
        return modelFactory.hallarProducto(codigo);
    }

    public boolean cambiarEstadoProducto(Producto producto) throws ProductoException {
        return modelFactory.cambiarEstadoProducto(producto);
    }

    public int crearComentarioProducto(Producto producto, Comentario comentario) throws ComentarioException {
        if (modelFactory.addComentarioProducto(producto,comentario) == 1) {
            return 1;
        }else {
            return 0;}}

    public int eliminarComentarioProducto(Producto producto,Comentario comentario) throws ComentarioException {
        if (modelFactory.deleteComentarioProducto(producto,comentario) == 1) {
            return 1;
        }else {
            return 0;}}

    public ArrayList<Comentario> mostrarComentarios(Producto producto) throws ComentarioException {
        return modelFactory.obtenerComentarioProductos(producto);
    }

}


