package com.proyecto.market.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CompraController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agregarCarrito;

    @FXML
    void agregarProducto(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert agregarCarrito != null : "fx:id=\"agregarCarrito\" was not injected: check your FXML file 'Compra.fxml'.";

    }

}


