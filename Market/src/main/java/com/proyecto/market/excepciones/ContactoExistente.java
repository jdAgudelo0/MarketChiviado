package com.proyecto.market.excepciones;

//**Se lanza cuando un vendor intenta agregar un vendedor que ya tiene en su lista de contactos**//

public class ContactoExistente extends Exception{

    public ContactoExistente(String mensaje){
        super(mensaje);
    }
    public ContactoExistente(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
