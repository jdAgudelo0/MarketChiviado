package com.proyecto.market.View;

import com.proyecto.market.Controller.ChatController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.proyecto.market.Model.Vendedor;

import java.io.IOException;
import java.util.ArrayList;

public class ChatViewController {


    @FXML
    private ImageView logoImageView;

    @FXML
    private ListView<Vendedor> listaAliados;

    private Vendedor user;

    @FXML
    public void initialize (){
        listaAliados.getItems().addAll(user.getCedulasAliados());
        logoImageView.getImage();
    }

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
            stage.setTitle("Muro");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();

        }
    }

    @FXML
    public void nuevoMensaje (ActionEvent event){

        String contacto = String.valueOf(listaAliados.getSelectionModel().getSelectedItem());
        if(contacto!=null){

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            try{
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/proyecto/market/Chat.fxml"));
                Parent root = loader.load();

                ChatController chatController = loader.getController();

                String currentUser = InicioViewController.getCurrentUser();
                chatController.initialize(currentUser, contacto);

                Stage stage= new Stage();
                stage.setTitle("Chat con: " + contacto);
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e){
                e.printStackTrace();

            }
        }


    }

}
