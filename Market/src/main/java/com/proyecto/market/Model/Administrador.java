package com.proyecto.market.Model;

import java.io.Serializable;


public class Administrador implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String cedula;
    private String usuario;
    private String correo;
    private String password;
    private String rutaImagen;


    public Administrador(String nombre, String apellido, String cedula, String usuario, String correo, String password, String rutaImagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.rutaImagen = rutaImagen;
    }

    public Administrador() {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}


