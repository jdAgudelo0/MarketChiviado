package com.proyecto.market.Model;

import com.proyecto.market.Exceptions.ComentarioNoPermitido;
import com.proyecto.market.Exceptions.VendedorException;

import java.util.ArrayList;

public class Market {
    private ArrayList<Vendedor> vendedores;
    private static Market market;

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

    public void addVendedor(Vendedor vendedor) throws VendedorException {
        if(vendedor == null){
            throw new VendedorException("El vendedor no puede ser nulo");
        }else {
            vendedores.add(vendedor);
        }
    }

    public void removeVendedor(Vendedor vendedor) throws VendedorException {
        if(vendedor == null){
            throw  new VendedorException("El personaje no puede ser nulo");
        }else {
            vendedores.remove(vendedor);
        }
    }

    public void updateVendedor(Vendedor vendedorActualizado, String cedula) throws VendedorException {
        if(vendedorActualizado == null){
            throw new VendedorException("El personaje no puede ser nulo");
        }else{
            for(Vendedor vendedor : vendedores){
                if(vendedor.getCedula().equals(cedula)){
                    vendedores.set(vendedores.indexOf(vendedor), vendedorActualizado);
                    break;
                }
            }
        }
    }

    public ArrayList<Vendedor> obtenerVendedores() {
        return vendedores;
    }

    public boolean vendedorExist(String cedula){
        return vendedores.stream().anyMatch(v -> v.getCedula().equals(cedula));
    }

    public Vendedor hallarVendedor(String cedula){
        return vendedores.stream().filter(v -> v.getCedula().equals(cedula)).findFirst().get();
    }





}
