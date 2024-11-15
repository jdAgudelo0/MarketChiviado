package com.proyecto.market.Controller;

import com.proyecto.market.Model.Administrador;
import com.proyecto.market.Model.Vendedor;

public class LogginController {

    ModelFactory modelFactory;

    public LogginController() {
        this.modelFactory = modelFactory.getInstance();
    }


    public int loggin(String username, String password) {
        return modelFactory.loggin(username, password);
    }

    public Vendedor getVendedor() {
        return modelFactory.getVendedor();
    }

    public Administrador getAdministradorAutenticado() {
        return modelFactory.getAdministrador();
    }


}