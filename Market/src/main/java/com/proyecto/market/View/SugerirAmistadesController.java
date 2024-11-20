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

import java.io.IOException;
import java.util.List;

public class SugerirAmistadesController {


    @FXML
    private ImageView logoChiviado;

    @FXML
    private ListView<String> sugerencias;

    private Vendedor currentUser;

    @FXML
    void enviarSolicitud() {

        String vendedorSeleccionado = sugerencias.getSelectionModel().getSelectedItem();

        if (vendedorSeleccionado != null) {
            Vendedor receptor = Market.getVendedores().stream()
                    .filter(v -> v.getNombre().equals(vendedorSeleccionado))
                    .findAny()
                    .orElse(null);

            if (receptor != null) {
                receptor.recibirSolicitud(currentUser);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Solicitud enviada a " + receptor.getNombre());
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione un vendedor.");
            alert.show();
        }

    }


    @FXML
    void initialize() {

        sugerencias.getItems().clear();

        logoChiviado.getImage();
        String currentUsername = InicioViewController.getCurrentUser();
        currentUser = Market.getVendedores().stream()
                .filter(v -> v.getNombre().equals(currentUsername))
                .findAny().orElse(null);

        if (currentUser != null) {

            // Mostrar vendedores aleatorios que no son aliados ni el mismo usuario
            List<Vendedor> vendedoresAleatorios = Market.getVendedores().stream()
                    .filter(v -> !v.equals(currentUser) && !currentUser.getAliados().contains(v))
                    .toList();

            vendedoresAleatorios.forEach(v -> sugerencias.getItems().add(v.getUsuario()));

        }
    }

    @FXML
    void volver(ActionEvent actionEvent) {
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

