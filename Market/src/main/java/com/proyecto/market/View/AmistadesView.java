package com.proyecto.market.View;

import com.proyecto.market.Controller.ChatController;
import com.proyecto.market.Model.Market;
import com.proyecto.market.Model.Vendedor;
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
import java.util.List;

public class AmistadesView {

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<String> solicitudesList;

    @FXML
    private ListView<String> vendedoresAliadosList;

    private Vendedor currentUser;

    private ObservableList<String> aliados = FXCollections.observableArrayList();

    private ObservableList<String> solicitudes = FXCollections.observableArrayList(); // Lista observable de solicitudes

    private static String contacto;



    public static String getContacto() {
        return contacto;
    }

    @FXML
    public void initialize (){

        String currentUsername = InicioViewController.getCurrentUser();
        currentUser = Market.getVendedores().stream()
                .filter(v -> v.getUsuario().equals(currentUsername))
                .findAny().orElse(null);

        if (currentUser != null) {
            // Cargar la lista de aliados
            List<Vendedor> aliadosList = currentUser.getAliados();

            aliadosList.forEach(v -> this.aliados.add(v.getUsuario())); // Añadir aliados a la lista observable

            // Cargar las solicitudes recibidas (suponiendo que el vendedor las tiene)
            currentUser.getSolicitudesRecibidas().forEach(v -> solicitudes.add(v.getUsuario())); // Añadir solicitudes

            // Asignar la lista observable a la vista
            vendedoresAliadosList.setItems(aliados);
            solicitudesList.setItems(solicitudes);
        }
    }

    @FXML
    public void nuevoMensaje (ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        contacto = String.valueOf(vendedoresAliadosList.getSelectionModel().getSelectedItem());

        if (contacto != null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyecto/market/Chat.fxml"));
                Parent root = loader.load();

                ChatController chatController = loader.getController();

                chatController.initialize(contacto);


                Stage stage = new Stage();
                stage.setTitle("Chat con: " + contacto);
                stage.setScene(new Scene(root));
                stage.show();



            } catch (IOException e) {

               e.printStackTrace();

            }

        }else if (contacto== null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona un aliado para abrir el chat.");
            alert.show();
        }

    }

    @FXML
    void aceptarSolicitud (ActionEvent event) {

        String vendedorSeleccionado = solicitudesList.getSelectionModel().getSelectedItem();
        if (vendedorSeleccionado != null) {
            Vendedor solicitante = Market.getVendedores().stream()
                    .filter(v -> v.getUsuario().equals(vendedorSeleccionado))
                    .findFirst()
                    .orElse(null);

            if (solicitante != null) {
                currentUser.aceptarSolicitud(solicitante); // Aceptar la solicitud

                // Actualizar la interfaz: agregar al solicitante a los aliados y eliminar la solicitud
                aliados.add(solicitante.getUsuario());
                solicitudes.remove(vendedorSeleccionado);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Solicitud aceptada de " + solicitante.getUsuario());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona una solicitud para aceptar.");
            alert.show();
        }


    }

    @FXML
    void rechazarSolicitud (ActionEvent event) {

        String vendedorSeleccionado = solicitudesList.getSelectionModel().getSelectedItem();
        if (vendedorSeleccionado != null) {
            Vendedor solicitante = Market.getVendedores().stream()
                    .filter(v -> v.getUsuario().equals(vendedorSeleccionado))
                    .findFirst()
                    .orElse(null);

            if (solicitante != null) {
                // Eliminar la solicitud y no agregar al solicitante a los aliados
                currentUser.getSolicitudesRecibidas().remove(solicitante); // Eliminar de la lista de solicitudes

                // Actualizar la interfaz: quitar al solicitante de la lista de solicitudes
                solicitudes.remove(vendedorSeleccionado);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Solicitud rechazada de " + solicitante.getUsuario());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona una solicitud para rechazar.");
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
