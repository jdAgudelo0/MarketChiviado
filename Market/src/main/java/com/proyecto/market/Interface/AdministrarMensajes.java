package com.proyecto.market.Interface;

import com.proyecto.market.Comentario;
import com.proyecto.market.Vendedor;

public interface AdministrarMensajes {

    public void enviarMensaje(Vendedor emisor, Vendedor receptor, String mensaje);
}
