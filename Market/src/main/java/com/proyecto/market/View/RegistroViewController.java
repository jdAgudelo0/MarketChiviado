package com.proyecto.market.View;


import com.proyecto.market.Controller.VendedorController;
import com.proyecto.market.Exceptions.VendedorException;
import com.proyecto.market.Model.Vendedor;
import com.proyecto.market.Utils.TextFormatterUtil;
import com.proyecto.market.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
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
    private VendedorController vendedorController;



    @FXML
    public void initialize() {
        vendedorController= new VendedorController();
        txtId.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }

    //boton crear cuenta tiene la logica para registrar el usuario y una vez registrado pasa a un mensaje en otra stage
    @FXML
    void crearCuenta(ActionEvent event) throws VendedorException, IOException {
        if (verificarCampos(crearVendedor())){
            if(vendedorController.crearVendedor(crearVendedor()) == 1){
                mostrarMensaje("Notificacion","Vendedor Creado", "EL Vendedor se ha creado con exito", Alert.AlertType.INFORMATION);
                cambiarVentana("mensajeExitoso.fxml", event);
            }else{
                mostrarMensaje("Noticicacion", "Vendedor no creado","El Vendedor no se ah creado", Alert.AlertType.ERROR);
            }
        }
    }



    //Metodo auxiliar para la validacion de la informacion del formulario.
    private boolean verificarCampos(Vendedor vendedor){
        String mensaje = "";

        if (vendedor.getNombre() == null || txtNombre.getText().equals(""))
            mensaje += "El nombre es invalido \n";

        if (vendedor.getApellido() == null || txtApellidos.getText().equals(""))
            mensaje += "El apellido es invalido \n";

        if (vendedor.getCedula() == null || txtId.getText().equals(""))
            mensaje += "La cedula es invalido \n";

        if (vendedor.getUsuario() == null || txtUsuario.getText().equals(""))
            mensaje += "El username es invalido \n";

        if (vendedor.getContrasenia() == null || txtContrasenia.getText().equals(""))
            mensaje += "El password es invalido \n";

        if (mensaje.equals("")) {

            return true;

        } else {
            mostrarMensaje("Notificacion","Datos Invalidos",mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }


    public Vendedor crearVendedor(){
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(txtNombre.getText());
        vendedor.setApellido(txtApellidos.getText());
        vendedor.setCedula(txtId.getText());
        vendedor.setUsuario(txtUsuario.getText());
        vendedor.setContrasenia(txtContrasenia.getText());
        return vendedor;
    }
    private void mostrarMensaje (String titulo, String header, String contenido, Alert.AlertType alertType){

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();

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
