package com.proyecto.market.Model;
import com.proyecto.market.Model.Enum.Categoria;
import com.proyecto.market.Model.Enum.Estado;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreProducto;
    private String codigo;
    private String imagen;
    private Categoria categoria;
    private float precio;
    private Estado estado ;
    private ArrayList<Comentario> comentarios = new ArrayList<>();
    private Vendedor vendedor;
    private int likes;

    public Producto(String nombreProducto, String codigo, String imagen, Categoria categoria, float precio, Estado estado, ArrayList<Comentario> comentarios, Vendedor vendedor, int likes) {
        this.nombreProducto = nombreProducto;
        this.codigo = codigo;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.comentarios = comentarios;
        this.vendedor = vendedor;
        this.likes = likes;
    }

    public Producto() {}


    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
