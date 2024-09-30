package com.proyecto.market.Model;
import com.proyecto.market.Model.Enum.Estado;
import java.util.ArrayList;

public class Producto {
    private String nombreProducto;
    private String codigo;
    private String imagen;
    private String categoria;
    private float precio;
    private Estado estado ;
    private ArrayList<Comentario> comentarios;
    private int likes;

    public Producto(String nombreProducto, String codigo, String imagen, String categoria, float precio, Estado estado) {

        this.nombreProducto= nombreProducto;
        this.codigo = codigo;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.comentarios = new ArrayList<Comentario>();
        this.likes = 0;
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
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
}
