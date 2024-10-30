package com.proyecto.market.Model;

import com.proyecto.market.Exceptions.VendedorException;
import com.proyecto.market.Model.Interface.AdministrarMensajes;


import java.io.Serializable;
import java.util.ArrayList;

//Atributos de nuestro vendedor
public class Vendedor implements AdministrarMensajes, Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private String cedula;
    private ArrayList<Vendedor> cedulasAliados = new ArrayList();
    private Muro muro;
    private ArrayList<Producto> productos = new ArrayList<>();

    private static final int CANTIDAD_MAXIMA_VENDEDORES_ALIADOS= 10;


    public Vendedor(String usuario, String contrasenia, String nombre, String apellido, String cedula, ArrayList<Vendedor> cedulasAliados, Muro muro, ArrayList<Producto> productos) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cedulasAliados = cedulasAliados;
        this.muro = muro;
        this.productos = productos;
    }

    public Vendedor() {}

    //getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public ArrayList<Vendedor> getCedulasAliados() {
        return cedulasAliados;
    }

    public void setCedulasAliados(ArrayList<Vendedor> cedulasAliados) {
        this.cedulasAliados = cedulasAliados;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) throws VendedorException {
        if(producto.getPrecio()<=0){
            throw new VendedorException("El producto no puede tener un valor negativo");
        }
        this.productos.add(producto);
    }

    public void eliminarProducto(Producto producto){
        this.productos.remove(producto);
    }

    public void agregarVendedor(Vendedor vendedor) throws VendedorException {
        if( cedulasAliados.size()>CANTIDAD_MAXIMA_VENDEDORES_ALIADOS){
            throw new VendedorException("El vendedor no puede tener más de"+ CANTIDAD_MAXIMA_VENDEDORES_ALIADOS + "vendedores aliados");
        }
        this.cedulasAliados.add(vendedor);
    }

    public void eliminarVendedor(Vendedor vendedor){
        this.cedulasAliados.remove(vendedor);
    }

    public Vendedor buscarVendedor(String nombre, String apellido, String cedula, ArrayList<Vendedor> vendedoresAliados){
        for( Vendedor vendedor : vendedoresAliados){
            if(vendedoresAliados.isEmpty()){
                return null;
            }else if(vendedor.getNombre().equalsIgnoreCase(nombre) && vendedor.getApellido().equalsIgnoreCase(apellido)){
                return vendedor;
            }
        }
        return null;
    }

    public void editarProducto(Producto producto){
        Producto productoViejo = buscarProducto(producto.getCodigo());
        if(productoViejo != null){
            productos.set(productos.indexOf(productoViejo), producto);
        }

    }

    public void agregarComentario(Comentario comentario, String codigo){
        Producto producto = buscarProducto(codigo);
        if(producto != null){
            producto.getComentarios().add(comentario);
        }

    }

    public void darLike(Producto producto){
        producto.setLikes(producto.getLikes() + 1);
        }

    @Override
    public void enviarMensaje(String mensaje) {

    }

    public Producto buscarProducto(String codigo){
        return productos.stream().filter(producto -> producto.getCodigo().equalsIgnoreCase(codigo)).findFirst().get();
    }
}

