package com.proyecto.market.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegistroViewController {

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbRegistro;

    @FXML
    private TextField txtApellidos;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;
    @FXML
    public void crearCuenta(ActionEvent actionEvent) {
        if(validarInformacion()){
            market.
        }

    }


    //Metodo auxiliar para la validacion de la informacion del formulario.
    public boolean validarInformacion(){
        if(txtNombre==null||txtNombre.getText().isEmpty()||txtApellidos==null||txtApellidos.getText().isEmpty()
        ||txtUsuario==null||txtUsuario.getText().isEmpty()||txtContrasenia.getText().length()<7){
            return false;
        }return true;
    }
}
