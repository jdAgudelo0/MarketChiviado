package com.proyecto.market.View;

import com.proyecto.market.Controller.VendedorController;
import com.proyecto.market.Exceptions.VendedorException;
import com.proyecto.market.Model.Vendedor;
import com.proyecto.market.Utils.TextFormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.io.IOException;

public class VendedorViewController {

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    VendedorController vendedorController;

    @FXML
    void initialize() throws IOException {
        vendedorController = new VendedorController();
        txtCedula.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }


    public Vendedor crearVendedor(){
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(txtNombre.getText());
        vendedor.setApellido(txtApellido.getText());
        vendedor.setCedula(txtCedula.getText());
        vendedor.setUsuario(txtUsername.getText());
        vendedor.setContrasenia(txtPassword.getText());
        return vendedor;
    }

    @FXML
    void crearUsuario(ActionEvent event) throws VendedorException {
        if (verificarCampos(crearVendedor())){
            if(vendedorController.crearVendedor(crearVendedor()) == 1){
                mostrarMensaje("Notificacion","Vendedor Creado", "EL Vendedor se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
        }else{
                mostrarMensaje("Noticicacion", "Vendedor no creado","El Vendedor no se ah creado", Alert.AlertType.ERROR);
            }
        }
    }


    private void limpiarCampos(){

        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }


    private boolean verificarCampos(Vendedor vendedor){
        String mensaje = "";

        if (vendedor.getNombre() == null || txtNombre.getText().equals(""))
            mensaje += "El nombre es invalido \n";

        if (vendedor.getApellido() == null || txtApellido.getText().equals(""))
            mensaje += "El apellido es invalido \n";

        if (vendedor.getCedula() == null || txtCedula.getText().equals(""))
            mensaje += "La cedula es invalido \n";

        if (vendedor.getUsuario() == null || txtUsername.getText().equals(""))
            mensaje += "El username es invalido \n";

        if (vendedor.getContrasenia() == null || txtPassword.getText().equals(""))
            mensaje += "El password es invalido \n";

        if (mensaje.equals("")) {

            return true;

        } else {
            mostrarMensaje("Notificacion","Datos Invalidos",mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }




    private void mostrarMensaje (String titulo, String header, String contenido, Alert.AlertType alertType){

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();

    }

}