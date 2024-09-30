package com.proyecto.market.Exceptions;
/*Excepcion lanzada cuando no encuentra un vendedor , porque no existe
 * o porque fue eliminado*/

public class VendedorNoEncontrado extends Exception {
    public VendedorNoEncontrado(String mensaje){
        super(mensaje);
    }
    public VendedorNoEncontrado(String mensaje, Throwable causa){
        super(mensaje, causa);
    }

}
