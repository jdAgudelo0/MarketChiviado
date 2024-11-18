package com.proyecto.market.View;

import com.proyecto.market.Controller.LogginController;
import com.proyecto.market.Model.Vendedor;
import com.proyecto.market.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MuroViewController {

    @FXML
    private ImageView Img1;

    @FXML
    private ImageView Img2;

    @FXML
    private ImageView Img3;

    @FXML
    private ImageView Img4;

    @FXML
    private ImageView Img5;

    @FXML
    private ImageView Img6;

    @FXML
    private Label LblNombre;

    @FXML
    private Button btnBuscarVendedor;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnSugerirAmistades;

    @FXML
    private Button btnVendedoresAliados;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Button anadirProducto;

    private Vendedor vendedor;

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @FXML
    public void initialize() {
        vendedor=new Vendedor();
        LblNombre.setText(vendedor.getNombre());
        System.out.println("Vendedor: " + vendedor.getNombre());
    }
    @FXML
    void anadirProductos(ActionEvent event) throws IOException {
        cambiarVentana("ProductoCrud.fxml", event);
    }
    @FXML
    void generarReporte(ActionEvent event) throws IOException {
        cambiarVentana("ReporteView.fxml", event);
    }

    @FXML
    void sugerirAmistades(ActionEvent event) throws  IOException{
        cambiarVentana("SugerirAmistades.fxml", event);
    }

    @FXML
    void mostrarVendedoresAliados(ActionEvent event)throws IOException{
        cambiarVentana("AmistadesView.fxml", event);
    }

    @FXML
    void abrirChats(ActionEvent event)throws  IOException{
        cambiarVentana("ChatView.fxml", event);
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
