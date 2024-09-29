package com.proyecto.market.excepciones;


//**Excepcion lanzada cuando un vendor intente superar el limite de vendedores aliados del sistemea**//
public class LimiteVendedoresAlcanzado extends Exception {

    public LimiteVendedoresAlcanzado(String mensaje ) {
        super(mensaje);
    }

    public LimiteVendedoresAlcanzado(String mensaje, Throwable causa ) {
        super(mensaje, causa);
    }
}
