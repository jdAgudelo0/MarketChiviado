package com.proyecto.market.View;

import com.proyecto.market.Model.Market;
import com.proyecto.market.Model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SugerirAmistadesController {


    @FXML
    private ImageView logoChiviado;

    @FXML
    private ListView<String> sugerencias;

    private Vendedor currentUser;

    @FXML
    void enviarSolicitud(ActionEvent event) {

        String vendedorSeleccionado = sugerencias.getSelectionModel().getSelectedItem();

        if (vendedorSeleccionado != null) {
            Vendedor receptor = Market.getVendedores().stream()
                    .filter(v -> v.getUsuario().equals(vendedorSeleccionado))
                    .findAny()
                    .orElse(null);

            if (receptor != null) {

                JOptionPane.showMessageDialog(null, " Solicitud enviada :D");
                receptor.recibirSolicitud(currentUser);
            }

        } else {

            JOptionPane.showMessageDialog(null, "Seleccione un vendedor");
        }

    }


    @FXML
    void initialize() {



        logoChiviado.getImage();
        String currentUsername = InicioViewController.getCurrentUser();
        currentUser = Market.getVendedores().stream()
                .filter(v -> v.getUsuario().equals(currentUsername))
                .findAny().orElse(null);

        if (currentUser != null) {

            Set<String> existentes = new HashSet<>(sugerencias.getItems());

            List<Vendedor> vendedoresFiltrados = Market.getVendedores().stream()
                    .filter(v -> !v.getUsuario().equals(currentUsername)) // Excluir al usuario actual
                    .filter(v -> !currentUser.getAliados().contains(v))  // Excluir aliados actuales
                    .toList();

            // Agregar solo los usuarios que no estén ya en la lista
            vendedoresFiltrados.forEach(v -> {
                if (!existentes.contains(v.getUsuario())) {
                    sugerencias.getItems().add(v.getUsuario());
                    existentes.add(v.getUsuario());
                }
            });
        }
    }

    @FXML
    void volver(ActionEvent actionEvent) {

        sugerencias.getItems().clear();
        //Cierra la ventana actual
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

        //Intenta abrir el Muro
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

