package com.proyecto.market.excepciones;

//**Excepcion para evitar que los precios sean negativos o 0**//
public class ProductoValorNegativo extends Exception{

    public ProductoValorNegativo(String mensaje){
        super(mensaje);
    }
    public ProductoValorNegativo(String mensaje, Throwable causa){
        super(mensaje, causa);
    }

}
