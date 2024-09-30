package com.proyecto.market.Model;

public class Market {
    private Vendedor vendedores;
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
}
