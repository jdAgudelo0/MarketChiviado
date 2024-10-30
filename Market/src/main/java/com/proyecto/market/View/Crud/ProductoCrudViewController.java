package com.proyecto.market.View.Crud;


import com.proyecto.market.Controller.ProductoController;
import com.proyecto.market.Exceptions.ProductoException;
import com.proyecto.market.Model.Enum.Categoria;
import com.proyecto.market.Model.Enum.Estado;
import com.proyecto.market.Model.Producto;
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
import java.util.UUID;

public class ProductoCrudViewController {

    @FXML
    private ComboBox<Categoria> cbxCategoria;

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private TableView<Producto> tblAdmins;

    @FXML
    private TableColumn<Producto, String> tlCodigo;

    @FXML
    private TableColumn<Producto, String> tlNombre;

    @FXML
    private TableColumn<Producto, Float> tlPrecio;

    @FXML
    private TableColumn<Producto, Categoria> tlCategoria;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    ProductoController productoController;

    private File imagenSeleccionada;

    ObservableList<Producto> listProductos = FXCollections.observableArrayList();

    @FXML
    void initialize() throws IOException {
        productoController = new ProductoController();
        txtPrecio.setTextFormatter(new TextFormatter<>(TextFormatterUtil::floatFormat));
        cbxCategoria.setItems(FXCollections.observableArrayList(Categoria.values()));
        intiView();
        agregarSeleccionListener();
    }

    private void intiView() {
        initDataBinding();
        obtenerAdmins();
        tblAdmins.getItems().clear();
        tblAdmins.setItems(listProductos);
    }

    private void initDataBinding() {
        tlNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        tlCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tlPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tlCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    }

    private void obtenerAdmins() {
        listProductos.addAll(productoController.mostrarProductosVendedor());
    }

    private void actualizarTabla(){
        listProductos.clear();
        listProductos.addAll(productoController.mostrarProductosVendedor());
    }

    private void agregarSeleccionListener() {
        tblAdmins.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Llenar los campos de texto con la información del administrador seleccionado
                txtNombre.setText(newValue.getNombreProducto());
                txtPrecio.setText(String.valueOf(newValue.getPrecio()));
                cbxCategoria.getSelectionModel().select(newValue.getCategoria());
                imagenPerfil.setImage(new Image(new File(newValue.getImagen()).toURI().toString()));
            }
        });
    }

    @FXML
    void actualizarProducto(ActionEvent event) throws ProductoException {

        Producto productoActualizado = crearProducto();
        Producto productoAntiguo = tblAdmins.getSelectionModel().getSelectedItem();

        productoActualizado.setCodigo(productoAntiguo.getCodigo());
        productoActualizado.setLikes(productoAntiguo.getLikes());
        productoActualizado.setEstado(productoAntiguo.getEstado());

        if (verificarCampos(productoActualizado) && productoAntiguo != null) {
            if (imagenSeleccionada != null) {
                copiarImagenProducto(imagenSeleccionada,productoActualizado);
            }else{
                productoActualizado.setImagen(productoAntiguo.getImagen());}

            if (productoController.modificarProducto(productoActualizado,productoAntiguo.getCodigo()) == 1){
                mostrarMensaje("Notificación", "Producto Actualizado", "El Producto se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                limpiarCampos();
                actualizarTabla();
            } else {
                mostrarMensaje("Notificación", "Error al producto", "El Producto no se ha actualizado", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación", "Selección requerida", "Por favor, selecciona un Producto para actualizar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void crearProducto(ActionEvent event) throws ProductoException {
        if (verificarCampos(crearProducto())){
            Producto producto = crearProducto();
            if (imagenSeleccionada != null) {
                copiarImagenProducto(imagenSeleccionada, producto);
            }else{
                producto.setImagen("src/main/resources/Images/Admins/PerfilErroError.png");
            }
            if(productoController.crearProducto(producto) == 1){
                mostrarMensaje("Notificacion","Producto Creado", "EL Producto se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
                tblAdmins.refresh();
                actualizarTabla();
            }else{
                mostrarMensaje("Noticicacion", "Producto no creado","El Producto no se ah creado", Alert.AlertType.ERROR);
            }}}


    public Producto crearProducto(){
        Producto producto = new Producto();
        producto.setCodigo(UUID.randomUUID().toString());
        producto.setNombreProducto(txtNombre.getText());
        producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
        producto.setCategoria(cbxCategoria.getSelectionModel().getSelectedItem());
        producto.setVendedor(productoController.getVendedor());
        producto.setEstado(Estado.PUBLICADO);
        producto.setLikes(0);
        return producto;
    }


    @FXML
    void eliminarProducto(ActionEvent event) throws ProductoException {
        Producto producto = tblAdmins.getSelectionModel().getSelectedItem();
        String ruta;
        if (!producto.getImagen().equals("src/main/resources/Images/Admins/PerfilErroError.png")) {
            ruta = producto.getImagen();
        }else{
            ruta = "";
        }

        if (producto != null) {
            if (productoController.eliminarProducto(producto)== 1){
                eliminarImagenProducto(ruta);
                mostrarMensaje("Notificacion","Producto Eliminado", "EL Producto se ha eliminado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
                tblAdmins.refresh();
                actualizarTabla();
            }else{
                mostrarMensaje("Noticicacion", "Producto no Elimnado","El Producto no se ah eliminado", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Noticicacion", "Producto no seleccionado","Seleccione un producto para continuar con la eliminicacion", Alert.AlertType.ERROR);
        }}


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


    public void copiarImagenProducto(File archivoImagen, Producto producto) {
        String carpetaDestino = "src/main/resources/Images/Product"; // Cambia a la carpeta deseada
        String extension = getExtension(archivoImagen.getName());
        Path destino = Path.of(carpetaDestino, producto.getCodigo() + "." + extension);

        try {
            // Copiar la imagen al destino
            Files.copy(archivoImagen.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

            // Actualizar la ruta de la imagen en el objeto Admin
            producto.setImagen(destino.toString());
            System.out.println("Imagen copiada en: " + destino.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getExtension(String nombreArchivo) {
        int i = nombreArchivo.lastIndexOf('.');
        return (i > 0) ? nombreArchivo.substring(i + 1) : "";
    }


    private void eliminarImagenProducto(String rutaImagen) {
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
        txtPrecio.setText("");
        cbxCategoria.getSelectionModel().clearSelection();
        imagenPerfil.setImage(null);

    }

    private boolean verificarCampos(Producto producto){
        String mensaje = "";

        if (producto.getNombreProducto() == null || txtNombre.getText().equals(""))
            mensaje += "El nombre es invalido \n";

        if (txtPrecio.getText().equals(""))
            mensaje += "El apellido es invalido \n";

        if (producto.getCategoria() == null || cbxCategoria.getSelectionModel().getSelectedItem() == null)
            mensaje += "El categoria es invalido \n";

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


