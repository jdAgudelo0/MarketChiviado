package com.proyecto.market.Model;

import com.proyecto.market.Model.Enum.Estado;
import java.util.ArrayList;
import com.proyecto.market.Exceptions.ProductoException;

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

    public Market() {
        this.vendedores=new ArrayList<>();
        this.productos=new ArrayList<>();
    }

    public void registrarUsuario(Vendedor vendedor){
        vendedores.add(vendedor);
    }

    public void verificarDatos(){
    }

    public boolean login(String usuario, String contrasenia){
        for (Vendedor vendedor: vendedores ){
            if(vendedor.getUsuario().equals(usuario) && vendedor.getContrasenia().equals(contrasenia)){
                return true;//Credenciales validas
            }

        }
        return false;//Credenciales invalidas
    }

    public void SugerirContacto(Vendedor vendedores){
    }

    public void addProduct(Producto producto) throws ProductoException {
        if(producto==null){
            throw  new ProductoException("El personaje no puede ser nulo");
        }
        productos.add(producto);
    }

    public void removeProduct(Producto producto) throws ProductoException {
        if(producto==null){
            throw new ProductoException("El personaje no puede ser nulo");
        }if( producto.getEstado()== Estado.VENDIDO){
            productos.remove(producto);
        }
    }

    public void updateProduct(Producto productoActualizado, String codigo ) throws ProductoException {
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
