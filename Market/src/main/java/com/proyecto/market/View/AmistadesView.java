package com.proyecto.market.View;

import com.proyecto.market.Controller.ChatController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AmistadesView {

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<String> solicitudesList;

    @FXML
    private ListView<String> vendedoresAliadosList;

    private ObservableList<String> aliados = FXCollections.observableArrayList();


    @FXML
    public void initialize (){
        aliados.add("Tefa");
        aliados.add("Elkin");
        aliados.add("Santa");
        vendedoresAliadosList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
        vendedoresAliadosList.setItems(aliados);
        vendedoresAliadosList.getSelectionModel().clearSelection();

    }

    @FXML
    public void nuevoMensaje (ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        String contacto = String.valueOf(vendedoresAliadosList.getSelectionModel().getSelectedItem());
        if (contacto != null) {

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

               e.printStackTrace();

            }

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona un aliado para abrir el chat.");
            alert.show();
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
            stage.setTitle("Muro");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();

        }

    }

}
