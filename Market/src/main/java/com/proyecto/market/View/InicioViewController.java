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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    private Market market;

    @FXML
    public void initialize() {
        market = Market.getInstance(); // o cualquier lógica para obtener la instancia de Market
        System.out.println(market);//Para depuracion, depués borrar
    }

    public void continuar(ActionEvent actionEvent) {
        String usuario = txtUsuario.getText().trim();
        String contrasenia = pfContrasenia.getText().trim();

        System.out.println("Usuario: " + usuario); // Para depuración
        System.out.println("Contraseña: " + contrasenia); // Para depuración

        boolean loggedIn = market.login(usuario, contrasenia);//Para depuracion, depués borrar
        System.out.println("Login attempt: " + loggedIn);//Para depuracion, depués borrar

        if (loggedIn) {
            System.out.println("Login exitoso");
            // Aquí carga la siguiente escena
        } else {
            System.out.println("Login fallido. mirar credenciales.");
            // Mostrar mensaje de error
        }
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void registrar(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyecto/market/registro-view.fxml"));
            Parent root = loader.load();
            RegistroViewController controller = loader.getController();
            controller.setMarket(market);
            Stage stage = new Stage();
            stage.setTitle("Registro ");
            stage.setScene(new Scene(root, 1024, 765));
            stage.show();

            // Cerrar la ventana actual de registro si deseas
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

