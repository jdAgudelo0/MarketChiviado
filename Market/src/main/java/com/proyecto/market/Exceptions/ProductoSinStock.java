package com.proyecto.market.Exceptions;

/* Excepcion lanzada cuando un vendedor intenta negociar o comprar un producto,
    pero ya no hay stock de ese producto*/

public class ProductoSinStock extends Exception {
    public ProductoSinStock(String mensaje){
        super(mensaje);
    }

    public ProductoSinStock(String mensaje, Throwable causa){
        super(mensaje,causa);
    }
}
