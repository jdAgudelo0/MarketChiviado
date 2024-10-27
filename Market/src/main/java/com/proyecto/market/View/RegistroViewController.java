package com.proyecto.market.View;

import com.proyecto.market.Model.Market;
import com.proyecto.market.Model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroViewController {

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbRegistro11;

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

    private Market market;

    @FXML
    public void initialize() {
        market = new Market(); // o cualquier lógica para obtener la instancia de Market
    }
    @FXML
    public void crearCuenta(ActionEvent actionEvent) {
        if (validarInformacion()) {
            // Registrar el usuario
            market.registrarUsuario(new Vendedor(txtNombre.getText(), txtApellidos.getText(), txtId.getText(), txtUsuario.getText(),txtContrasenia.getText()));

            // Cargar y mostrar la nueva ventana de registro exitoso
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyecto/market/mensajeExitoso.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Registro Exitoso");
                stage.setScene(new Scene(root, 628, 400));
                stage.show();

                // Cerrar la ventana actual de registro si deseas
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Datos invalidos");
        }
    }



    //Metodo auxiliar para la validacion de la informacion del formulario.
    public boolean validarInformacion(){
        if(txtNombre.getText()==null||txtNombre.getText().isEmpty()||txtApellidos.getText()==null||txtApellidos.getText().isEmpty()
        ||txtUsuario.getText()==null||txtUsuario.getText().isEmpty()||txtContrasenia.getText().length()<7){
            return false;
        }return true;
    }
}
