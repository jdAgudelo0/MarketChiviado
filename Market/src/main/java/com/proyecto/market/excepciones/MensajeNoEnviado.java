package com.proyecto.market.excepciones;
/*  Excepcion para cuando un vendedor intenta enviar un mensaje a otro vendedore
    *que no esta en su lista de contactos
*/
public class MensajeNoEnviado extends Exception{

    public MensajeNoEnviado(String mensaje){
        super(mensaje);
    }

    public MensajeNoEnviado(String mensaje,Throwable causa){
        super(mensaje, causa);
    }
}
