package com.proyecto.market.Model.Interface;

import com.proyecto.market.Model.Vendedor;

public interface AdministrarMensajes {

    public void enviarMensaje(Vendedor emisor, Vendedor receptor, String mensaje);
}
