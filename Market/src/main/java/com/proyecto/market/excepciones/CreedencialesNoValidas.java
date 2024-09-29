package com.proyecto.market.excepciones;
/*Excepcion lanzada cuando un usuario intenta ingresar pero sus datos son incorrectos*/

public class CreedencialesNoValidas extends Exception {
    public CreedencialesNoValidas(String mensaje){
        super(mensaje);
    }
    public CreedencialesNoValidas(String mensaje, Throwable causa){
        super(mensaje, causa);
    }

}
