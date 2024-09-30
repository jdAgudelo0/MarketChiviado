package com.proyecto.market.Exceptions;

/*Excepción lanzada cuando un venderdor intenta comentar
    *en una publicación de otro vendedor que no hace parte de su lista de contactos
 */

public class ComentarioNoPermitido extends Exception {

    public ComentarioNoPermitido(String mensaje){
        super(mensaje);
    }

    public ComentarioNoPermitido(String mensaje, Throwable causa){
        super(mensaje, causa);
    }

}
