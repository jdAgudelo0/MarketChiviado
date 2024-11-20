package com.proyecto.market.Controller;

import com.proyecto.market.Utils.ArchivoUtil;
import com.proyecto.market.View.AmistadesView;
import com.proyecto.market.View.InicioViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChatController {

    @FXML
    private TextArea chatArea;

    @FXML
    private ImageView logoChiviado;

    @FXML
    private TextField mensajeField = new TextField();

    private static String recipiente;
    private String userActual;
    private ObjectOutputStream out;


    public static String getRecipiente(){
        return recipiente;
    }

    @FXML
    public void initialize(String recipiente){
        logoChiviado.getImage();
        chatArea.getText();
        recipiente = AmistadesView.getContacto();
        cargarMensajesDelArchivo();
    }


    @FXML
    private void enviarMensaje(ActionEvent event) {


        userActual = InicioViewController.getCurrentUser();

        String mensaje = mensajeField.getText();
        String fecha = String.valueOf(ArchivoUtil.cargarFechaSistema());
        if(!mensaje.isEmpty()){
            chatArea.appendText(userActual +": " + mensaje + " (" + fecha + ")\n");
            guardarMensajeEnArchivo(userActual + ": " + mensaje + " (" + fecha + ")\n");

            mensajeField.clear();

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "El mensaje no puede estar vacio " );
            alert.show();
        }

    }

    private void cargarMensajesDelArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Chat.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Agregar cada línea leída al área de chat
                chatArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void guardarMensajeEnArchivo(String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Chat.txt", true))) {
            // Guardar el mensaje en el archivo
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
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
