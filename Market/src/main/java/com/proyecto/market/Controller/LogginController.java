package com.proyecto.market.Controller;

public class LogginController {

    ModelFactory modelFactory;

    public LogginController() {
        this.modelFactory = modelFactory.getInstance();
    }


    public int loggin(String username, String password) {
        return modelFactory.loggin(username, password);
    }


}
