package com.proyecto.market.Exceptions;

public class ProductoException extends Throwable {
    public ProductoException(String mensaje){
        super(mensaje);
    }

    public ProductoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
