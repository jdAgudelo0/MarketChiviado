package com.proyecto.market;

import com.proyecto.market.Enum.Estado;
import com.proyecto.market.excepciones.LimiteVendedoresAlcanzado;
import com.proyecto.market.excepciones.ProductoNoEncontrado;
import com.proyecto.market.excepciones.ProductoValorNegativo;
import com.proyecto.market.excepciones.VendedorNoEncontrado;

import java.util.ArrayList;

//Atributos de nuestro vendedor
public class Vendedor {
    private String nombre;
    private String apellido;
    private String cedula;
    private ArrayList<Vendedor> vendedoresAliados;
    private ArrayList<Vendedor> contactos;
    private Muro muro;
    private static final int CANTIDAD_MAXIMA_VENDEDORES_ALIADOS= 10;

    private ArrayList<Producto> productos;

    private ArrayList<Chat> chats;

    public Vendedor(String nombre, String apellido, String cedula, ArrayList<Vendedor> vendedoresAliados, Muro muro){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.vendedoresAliados = new ArrayList<Vendedor>();
        this.muro = muro;
        this.contactos = new ArrayList<Vendedor>();
        this.chats = new ArrayList<Chat>();
        this.productos = new ArrayList<Producto>();
    }
    public Vendedor() {}

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public Muro getMuro(){
        return muro;
    }

    public ArrayList<Vendedor> getVendedoresAliados() {
        return vendedoresAliados;
    }

    public ArrayList<Vendedor> getContactos() {
        return contactos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setContactos(ArrayList<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public void setVendedoresAliados(ArrayList<Vendedor> vendedoresAliados) {
        this.vendedoresAliados = vendedoresAliados;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public void agregarProducto(String nombreProducto, String codigo, String imagen, String categoria, float precio, Estado estado) throws ProductoValorNegativo {
        if(precio<=0){
            throw new ProductoValorNegativo("El producto no puede tener un valor negativo");
        }
        this.productos.add(new Producto(nombre, codigo, imagen, categoria, precio, estado));
    }

    public void eliminarProducto(Producto producto) throws ProductoNoEncontrado{
        if (productos.contains(producto)){
        this.productos.remove(producto);}
        throw new ProductoNoEncontrado("Este producto no esta dentro de tu stock");
    }

    public void agregarVendedor(Vendedor vendedor) throws LimiteVendedoresAlcanzado {
        if( vendedoresAliados.size()==CANTIDAD_MAXIMA_VENDEDORES_ALIADOS){
            throw new LimiteVendedoresAlcanzado("El vendedor no puede tener más de"+ CANTIDAD_MAXIMA_VENDEDORES_ALIADOS + "vendedores aliados");
        }
        this.vendedoresAliados.add(vendedor);
    }

    public void eliminarVendedor(Vendedor vendedor) throws VendedorNoEncontrado{
        if (vendedoresAliados.contains(vendedor)){
            this.productos.remove(vendedor);}
        throw new VendedorNoEncontrado("Este vendedor no esta dentro de tu lista");
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

    public void EnviarMensaje(Chat chat, Vendedor receptor,String mensaje){
        chat.enviarMensaje(this,receptor, mensaje);
    }

    public void editarProducto(Producto producto){

    }

    public void agregarComentario(Comentario comentario){

    }

    public void darLike(Producto producto){

    }
}
