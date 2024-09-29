package com.proyecto.market.excepciones;

/*Excepcion lanzada cuando hubo un fallo a la hora de el registro*/
public class UsuarioNoRegistrado extends Exception {
    public UsuarioNoRegistrado(String mensaje){
        super(mensaje);
    }
    public UsuarioNoRegistrado(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
