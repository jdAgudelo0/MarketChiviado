package com.proyecto.market.excepciones;

//**Se lanza cuando un vendor intenta agregar un vendedor que ya tiene en su lista de contactos**//

public class ExcepcionContantoExistente extends Exception{

    public ExcepcionContantoExistente(String mensaje){
        super(mensaje);
    }
    public ExcepcionContantoExistente(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
