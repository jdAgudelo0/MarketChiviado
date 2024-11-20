package com.proyecto.market.View;

import com.proyecto.market.Controller.ChatController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class AmistadesView {

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<String> solicitudesList;

    @FXML
    private ListView<String> vendedoresAliadosList;

    @FXML
    public void initialize (){

        vendedoresAliadosList.getItems().add("Tefa");

    }

    @FXML
    public void nuevoMensaje (ActionEvent event) {

        String contacto = vendedoresAliadosList.getSelectionModel().getSelectedItem();
        if (contacto != null) {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyecto/market/Chat.fxml"));
                Parent root = loader.load();

                ChatController chatController = loader.getController();

                String currentUser = InicioViewController.getCurrentUser();
                chatController.initialize(currentUser, contacto);

                Stage stage = new Stage();
                stage.setTitle("Chat con: " + contacto);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona un aliado para abrir el chat.");
                alert.show();

            }
        }

    }



    @FXML
    void volver(ActionEvent actionEvent) {
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
