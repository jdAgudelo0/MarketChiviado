package com.proyecto.market.View;

import com.proyecto.market.Model.Market;
import com.proyecto.market.Model.Vendedor;
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
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroViewController {

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lbRegistro11;

    @FXML
    private TextField txtApellidos;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;

    private Market market;

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @FXML
    public void initialize() {
        market = Market.getInstance();// o cualquier lógica para obtener la instancia de Market
        System.out.println(market);//Para depuracion, depués borrar
    }

    //boton crear cuenta tiene la logica para registrar el usuario y una vez registrado pasa a un mensaje en otra stage
    @FXML
    public void crearCuenta(ActionEvent actionEvent) {
        if (validarInformacion()) {
            // Registrar el usuario
            market.registrarUsuario(new Vendedor(txtNombre.getText(), txtApellidos.getText(), txtId.getText(), txtUsuario.getText(),txtContrasenia.getText()));

            // Cargar y mostrar la nueva ventana de registro exitoso
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyecto/market/mensajeExitoso.fxml"));
                Parent root = loader.load();
                MensajeExitosoController controller=loader.getController();
                controller.setMarket(Market.getInstance());
                Stage stage = new Stage();
                stage.setTitle("Registro Exitoso");
                stage.setScene(new Scene(root, 628, 400));
                stage.show();

                // Cerrar la ventana actual de registro si deseas
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Datos invalidos");
        }
    }



    //Metodo auxiliar para la validacion de la informacion del formulario.
    public boolean validarInformacion() {
        if (txtNombre.getText() == null || txtNombre.getText().isEmpty()) {
            System.out.println("Nombre no puede estar vacío");//para depuracion, se debe colocar como un mensaje en la interfaz
            return false;
        }
        if (txtApellidos.getText() == null || txtApellidos.getText().isEmpty()) {
            System.out.println("Apellidos no pueden estar vacíos");//para depuracion, se debe colocar como un mensaje en la interfaz
            return false;
        }
        if (txtUsuario.getText() == null || txtUsuario.getText().isEmpty()) {
            System.out.println("Usuario no puede estar vacío");//para depuracion, se debe colocar como un mensaje en la interfaz
            return false;
        }
        if (txtContrasenia.getText().length() < 7) {
            System.out.println("La contraseña debe tener al menos 7 caracteres");//para depuracion, se debe colocar como un mensaje en la interfaz
            return false;
        }
        return true;
    }
}
