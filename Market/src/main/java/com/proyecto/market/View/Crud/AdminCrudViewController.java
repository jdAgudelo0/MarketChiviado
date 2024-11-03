package com.proyecto.market.View.Crud;

import com.proyecto.market.Controller.AdminController;
import com.proyecto.market.Exceptions.AdminException;
import com.proyecto.market.Model.Administrador;
import com.proyecto.market.Utils.TextFormatterUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AdminCrudViewController {

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private TableView<Administrador> tblAdmins;

    @FXML
    private TableColumn<Administrador, String> tlApellido;

    @FXML
    private TableColumn<Administrador, String> tlCedula;

    @FXML
    private TableColumn<Administrador, String> tlNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;


    AdminController adminController;

    private File imagenSeleccionada;

    ObservableList<Administrador> listAdmin = FXCollections.observableArrayList();


    @FXML
    void initialize() throws IOException {
        adminController = new AdminController();
        txtCedula.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        intiView();
        agregarSeleccionListener();
    }

    private void intiView() {
        initDataBinding();
        obtenerAdmins();
        tblAdmins.getItems().clear();
        tblAdmins.setItems(listAdmin);
    }

    private void initDataBinding() {
        tlNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tlApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tlCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
    }

    private void obtenerAdmins() {
        listAdmin.addAll(adminController.mostrarAdmin());
    }

    private void actualizarTabla(){
        listAdmin.clear();
        listAdmin.addAll(adminController.mostrarAdmin());
    }


    private void agregarSeleccionListener() {
        tblAdmins.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Llenar los campos de texto con la información del administrador seleccionado
                txtNombre.setText(newValue.getNombre());
                txtApellido.setText(newValue.getApellido());
                txtCedula.setText(newValue.getCedula());
                txtUsername.setText(newValue.getUsuario());
                txtPassword.setText(newValue.getPassword());
                txtCedula.setDisable(true);

                imagenPerfil.setImage(new Image(new File(newValue.getRutaImagen()).toURI().toString()));
            }
        });
    }


    @FXML
    void actualizarAdmin(ActionEvent event) throws AdminException {

        Administrador administradorActualizado = crearAdmin();
        Administrador administradorAnterior = tblAdmins.getSelectionModel().getSelectedItem();
        System.out.printf(administradorAnterior.getCedula());
        if (verificarCampos(administradorActualizado) && administradorAnterior != null) {
            if (imagenSeleccionada != null) {
                copiarImagenAdmin(imagenSeleccionada,administradorActualizado);
            }else{
                administradorActualizado.setRutaImagen(administradorAnterior.getRutaImagen());}

            if (adminController.modificarAdmin(administradorActualizado,administradorAnterior.getCedula()) == 1){
                mostrarMensaje("Notificación", "Admin Actualizado", "El Administrador se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                limpiarCampos();
                actualizarTabla();
            } else {
                mostrarMensaje("Notificación", "Error al actualizar", "El Administrador no se ha actualizado", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación", "Selección requerida", "Por favor, selecciona un Administrador para actualizar", Alert.AlertType.WARNING);
        }
    }


    @FXML
    void eliminarAdmin(ActionEvent event) throws AdminException {
        Administrador administrador = tblAdmins.getSelectionModel().getSelectedItem();
        String ruta;
        if (!administrador.getRutaImagen().equals("src/main/resources/Images/Admins/PerfilErroError.png")) {
            ruta = administrador.getRutaImagen();
        }else{
            ruta = "";
        }

        if (administrador != null) {
            if (adminController.eliminarAdmin(administrador)== 1){
                eliminarImagenAdmin(ruta);
                mostrarMensaje("Notificacion","Admin Eliminado", "EL Admin se ha eliminado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
                tblAdmins.refresh();
                actualizarTabla();
            }else{
                mostrarMensaje("Noticicacion", "Admin no Elimnado","El Admin no se ah eliminado", Alert.AlertType.ERROR);
            }
       }else{
            mostrarMensaje("Noticicacion", "Admin no seleccionado","Seleccione un admin para continuar con la eliminicacion", Alert.AlertType.ERROR);
        }
    }


    @FXML
    void crearUsuario(ActionEvent event) throws AdminException {
        if (verificarCampos(crearAdmin())){
            Administrador administrador = crearAdmin();
            if (imagenSeleccionada != null) {
                copiarImagenAdmin(imagenSeleccionada, administrador);
            }else{
                administrador.setRutaImagen("src/main/resources/Images/Admins/PerfilErroError.png");
            }
            if(adminController.crearAdmin(administrador) == 1){
                mostrarMensaje("Notificacion","Admin Creado", "EL Admin se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
                tblAdmins.refresh();
                actualizarTabla();
            }else{
                mostrarMensaje("Noticicacion", "Admin no creado","El Admin no se ah creado", Alert.AlertType.ERROR);
            }
        }
    }


    public Administrador crearAdmin(){
        Administrador administrador = new Administrador();
        administrador.setNombre(txtNombre.getText());
        administrador.setApellido(txtApellido.getText());
        administrador.setCedula(txtCedula.getText());
        administrador.setUsuario(txtUsername.getText());
        administrador.setPassword(txtPassword.getText());
        return administrador;
    }

    @FXML
    void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif")
        );

        // Obtener la ventana principal para que el diálogo se abra en el contexto adecuado
        Stage stage = (Stage) imagenPerfil.getScene().getWindow();
        imagenSeleccionada = fileChooser.showOpenDialog(stage);

        if (imagenSeleccionada!= null) {
            // Mostrar la imagen seleccionada en el ImageView
            imagenPerfil.setImage(new Image(imagenSeleccionada.toURI().toString()));
        }
    }


    public void copiarImagenAdmin(File archivoImagen, Administrador admin) {
        String carpetaDestino = "src/main/resources/Images/Admins"; // Cambia a la carpeta deseada
        String extension = getExtension(archivoImagen.getName());
        Path destino = Path.of(carpetaDestino, admin.getCedula() + "." + extension);

        try {
            // Copiar la imagen al destino
            Files.copy(archivoImagen.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

            // Actualizar la ruta de la imagen en el objeto Admin
            admin.setRutaImagen(destino.toString());
            System.out.println("Imagen copiada en: " + destino.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getExtension(String nombreArchivo) {
        int i = nombreArchivo.lastIndexOf('.');
        return (i > 0) ? nombreArchivo.substring(i + 1) : "";
    }


    private void eliminarImagenAdmin(String rutaImagen) {
        try {
            Path path = Paths.get(rutaImagen);
            Files.deleteIfExists(path); // Elimina el archivo si existe
        } catch (IOException e) {
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        limpiarCampos();
    }

    private void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        imagenPerfil.setImage(null);
        txtCedula.setDisable(false);
    }

    private boolean verificarCampos(Administrador administrador){
        String mensaje = "";

        if (administrador.getNombre() == null || txtNombre.getText().equals(""))
            mensaje += "El nombre es invalido \n";

        if (administrador.getApellido() == null || txtApellido.getText().equals(""))
            mensaje += "El apellido es invalido \n";

        if (administrador.getCedula() == null || txtCedula.getText().equals(""))
            mensaje += "La cedula es invalido \n";

        if (administrador.getUsuario() == null || txtUsername.getText().equals(""))
            mensaje += "El username es invalido \n";

        if (administrador.getPassword() == null || txtPassword.getText().equals(""))
            mensaje += "El password es invalido \n";

        if (mensaje.equals("")) {

            return true;

        } else {
            mostrarMensaje("Notificacion","Datos Invalidos",mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }

    private void mostrarMensaje (String titulo, String header, String contenido, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }



}
