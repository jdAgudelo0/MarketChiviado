package com.proyecto.market;

import java.util.ArrayList;

//Atributos de nuestro vendedor
public class Vendedor {
    private String nombre;
    private String apellido;
    private String cedula;
    private ArrayList<Vendedor> vendedoresAliados;
    private ArrayList<Vendedor> contactos;
    private Muro muro;

    public Vendedor(String nombre, String apellido, String cedula, ArrayList<Vendedor> vendedoresAliados, Muro muro){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.vendedoresAliados = new ArrayList<Vendedor>();
        this.muro = muro;
        this.contactos = new ArrayList<Vendedor>();
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

    public void setMuro(Muro muro) {
        this.muro = muro;
    }
}
