package com.proyecto.market.View;

import com.proyecto.market.Model.Market;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MensajeExitosoController {

    @FXML
    private VBox VbBandeja;

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }


    @FXML
    private Button btnVolver;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbBienvenido;

    @FXML
    private Label lbMensaje;

    private Market market;

    @FXML
    public void initialize(){
        market=Market.getInstance();
        System.out.println(market);//Para depuracion, depués borrar
    }
    @FXML
    public void volver(ActionEvent actionEvent){
        //Cierra la ventana actual
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

        //Intenta abrir el inicio sesion
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/proyecto/market/inicioSesion-view.fxml"));
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
