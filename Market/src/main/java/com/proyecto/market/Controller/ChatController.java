package com.proyecto.market.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;

public class ChatController {

    @FXML
    private TextArea chatArea;

    @FXML
    private ImageView logoChiviado;

    @FXML
    private TextField mensajeField = new TextField();

    private String recipiente;
    private String userActual;
    private ObjectOutputStream out;


    public void setRecipiente(String recipiente){
        this.recipiente = recipiente;
    }

    @FXML
    public void initialize(String userActual, String recipiente){
        this.userActual = userActual;
        this.recipiente = recipiente;
        logoChiviado.getImage();

        try {
            Socket socket = new Socket("localHost",12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            new Thread(() -> listenForMensajes(socket)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  void listenForMensajes (Socket socket){
        try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
          while(true){
              String mensaje = (String) input.readObject();
              chatArea.appendText(mensaje + "\n");
              chatArea.getText();
          }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void enviarMensaje(ActionEvent event) {

        String mensaje = mensajeField.getText();
        LocalDate fecha = LocalDate.now();
        if(!mensaje.isEmpty()){
            try{
                out.writeObject(recipiente);
                out.writeObject(userActual+ ": " + mensaje + "\n" + fecha );
                out.flush();

                chatArea.appendText("Tu: " + mensaje + "\n" + fecha);
                chatArea.getText();
                mensajeField.clear();

            } catch (IOException e){
                e.printStackTrace();
            }
        }else {

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
