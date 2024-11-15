package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.ProductoException;
import com.proyecto.market.Model.Producto;
import com.proyecto.market.Model.Vendedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ProductoTrueController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button crearProducto;

    @FXML
    void seleccionarImagen(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }


    ModelFactory modelFactory;

    Vendedor vendedor;

    public ProductoTrueController() {
        this.modelFactory = ModelFactory.getInstance();
        vendedor = modelFactory.getVendedor();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @FXML
    public int crearProducto(Producto producto) throws ProductoException {
        if (modelFactory.addProducto(producto) == 1) {
            return 1;
        }else {
            return 0;
        }

    }



}
