package com.proyecto.market.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatViewController {

    @FXML
    private Button btnNuevoMensaje;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private ImageView userImageView;

    @FXML
    public void volver(ActionEvent actionEvent){
        //Cierra la ventana actual
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

        //Intenta abrir el inicio sesion
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/proyecto/market/muro.fxml"));
            Parent root = loader.load();
            Stage stage= new Stage();
            stage.setTitle("Inicio sesion");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();

        }
    }

}
