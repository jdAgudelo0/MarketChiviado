package com.proyecto.market.Model;

import com.proyecto.market.Model.Enum.Estado;

import java.util.ArrayList;

public class Market {
    private ArrayList<Vendedor> vendedores;
    private static Market market;
    private ArrayList<Producto> productos;

    //singleton para Market
    public static Market getInstance(){

        if(market == null){
            market = new Market();
        }
        return market;
    }

    public void registrarUsuario(String nombre, String apellidos, String cedula, String direccion, String usuario, String contraseña){
    }

    public void verificarDatos(){
    }

    public void login(String Usuario, String contraseña){
    }

    public void SugerirContacto(Vendedor vendedores){
    }

    public void addProduct(Producto producto){
        if(producto==null){
            throw  new ProductoException("El personaje no puede ser nulo");
        }
        productos.add(producto);
    }

    public void removeProduct(Producto producto){
        if(producto==null){
            throw new ProductoException("El personaje no puede ser nulo");
        }if( producto.getEstado()== Estado.VENDIDO){
            productos.remove(producto);
        }
    }

    public void updateProduct(Producto productoActualizado, String codigo ){
        if(productoActualizado == null){
            throw new ProductoException("El personaje no puede ser nulo");
        }for(Producto producto: productos){
            if(producto.getCodigo().equals(codigo)){
                productos.set(productos.indexOf(producto), producto);
            }break;
        }
    }

    public boolean productExist(String codigo){
        return productos.stream().anyMatch(producto -> producto.getCodigo().equals(codigo));
    }

    public Producto buscarProducto(String codigo){
        return productos.stream().filter(producto -> producto.getCodigo().equals(codigo)).findFirst().get();
    }


}
