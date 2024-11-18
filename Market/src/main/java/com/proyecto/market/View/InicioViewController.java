package com.proyecto.market.View;

import com.proyecto.market.Controller.LogginController;
import com.proyecto.market.Model.Market;
import com.proyecto.market.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioViewController {

    @FXML
    private HBox HbContrasenia;

    @FXML
    private HBox HboxUsuario;

    @FXML
    private VBox Vb;

    @FXML
    private Button btnContinuar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private ImageView imgIcono1;

    @FXML
    private ImageView imgIcono2;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbInicio;

    @FXML
    private PasswordField pfContrasenia;

    @FXML
    private TextField txtUsuario;

    private LogginController controller;

    private static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void initialize() {
        controller = new LogginController();
    }




    @FXML
    public void registrar(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        //Intenta abrir el inicio sesion
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/proyecto/market/registro.fxml"));
            Parent root = loader.load();
            Stage stage= new Stage();
            stage.setTitle("Inicio sesion");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();

        }
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {

        if (verificarCampo()){

            int opciones = controller.loggin(txtUsuario.getText(),pfContrasenia.getText());
            currentUser = txtUsuario.getText();

      try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("AUTH");
            out.writeObject(username);
            out.writeObject(password);
            String response = (String) in.readObject();

            if (response.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login exitoso");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Login fallido");
                alert.show();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

            switch (opciones){

                case 1: cambiarVentana("muro.fxml",event);
                    break;

                case 2: cambiarVentana("AdminCrud.View.fxml",event);
                    break;

                case 0: mostrarMensaje("Error Al Iniciar Sesion","Error con los datos","El Usuario"+
                        "o Contraseña no han sido registrados", Alert.AlertType.ERROR);
                    break;
            }}}

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean verificarCampo() {
        String mensaje = "";
        if (txtUsuario.getText().isEmpty() || txtUsuario == null)
            mensaje += "Ingrese el campo de UserName\n";
        if (pfContrasenia.getText().isEmpty() || pfContrasenia == null) {
            mensaje += "Ingrese el campo de Password\n";
        }
        if (mensaje==""){
            return true;
        }else {
            mostrarMensaje("Datos incorrectos","Datos invalidos",mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }

    public void cambiarVentana(String nombreFxml,ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource(nombreFxml));
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

