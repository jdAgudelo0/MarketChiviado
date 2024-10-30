package com.proyecto.market.View;

import com.proyecto.market.App;
import com.proyecto.market.Controller.LogginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LogginControllerView {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    LogginController controller;

    @FXML
    void initialize() throws IOException {
        controller = new LogginController();
    }


    @FXML
    void ingresar(ActionEvent event) throws IOException {

        if (verificarCampo()){

            int opciones = controller.loggin(txtUsername.getText(),txtPassword.getText());

            switch (opciones){

                case 1: cambiarVentana("ProductoCrud.fxml",event);
                    break;

                case 2: cambiarVentana("AdminCrud.View.fxml",event);
                    break;

                case 0: mostrarMensaje("Error Al Iniciar Sesion","Error con los datos","El Usuario"+
                        "o Contraseña no han sido registrados", Alert.AlertType.ERROR);
                    break;
            }}}


    private boolean verificarCampo() {
        String mensaje = "";
        if (txtUsername.getText().isEmpty() || txtUsername == null)
            mensaje += "Ingrese el campo de UserName\n";
        if (txtPassword.getText().isEmpty() || txtPassword == null) {
            mensaje += "Ingrese el campo de Password\n";
        }
        if (mensaje==""){
            return true;
        }else {
            mostrarMensaje("Datos incorrectos","Datos invalidos",mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    public void cambiarVentana(String nombreFxml,ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(nombreFxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Obtener la referencia a la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close(); // Cerrar la ventana actual

        // Abrir la nueva ventana
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
