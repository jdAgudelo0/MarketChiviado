package com.proyecto.market.Exceptions;

/*Excepcion lanzada cuando no encuentra un producto, porque no existe
* o porque fue eliminado*/

public class ProductoNoEncontrado extends  Exception{

    public ProductoNoEncontrado(String mensaje){
        super(mensaje);
    }

    public ProductoNoEncontrado(String mensaje, Throwable causa){
        super(mensaje, causa);
    }

}
