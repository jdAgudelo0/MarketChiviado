package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.ProductoException;
import com.proyecto.market.Model.Enum.Categoria;
import com.proyecto.market.Model.Enum.Estado;
import com.proyecto.market.Model.Producto;
import com.proyecto.market.Model.Vendedor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ProductoTrueController {

    @FXML
    private ComboBox<Categoria> combCategoria;

    @FXML
    private AnchorPane ventana;

    @FXML
    private ImageView logoChiviado;

    @FXML
    private ImageView productoImagen;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtNombre;

    private File imagenSeleccionada;


    @FXML
    void initialize() {
        combCategoria.setItems(FXCollections.observableArrayList(Categoria.values()));
        logoChiviado.getImage();

    }


    ModelFactory modelFactory;

    Vendedor vendedor;

    public ProductoTrueController() {
        this.modelFactory = ModelFactory.getInstance();
        vendedor = modelFactory.getVendedor();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @FXML
    public int crearProducto(Producto producto) throws ProductoException {
        if (modelFactory.addProducto(producto) == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    @FXML
    void crearProducto(ActionEvent event) throws ProductoException {
        if (verificarCampos(crearProducto())) {
            Producto producto = crearProducto();
            if (imagenSeleccionada != null) {
                copiarImagenProducto(imagenSeleccionada, producto);
            } else {
                producto.setImagen("src/main/resources/Images/Admins/PerfilErroError.png");
            }
            if (crearProducto(producto) == 1) {
                mostrarMensaje("Notificacion", "Producto Creado", "EL Producto se ha creado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("NotiFicacion", "Producto no creado", "El Producto no se ah creado", Alert.AlertType.ERROR);
            }
        }
    }

    public Producto crearProducto() {
        Producto producto = new Producto();
        producto.setCodigo(UUID.randomUUID().toString());
        producto.setNombreProducto(txtNombre.getText());
        producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
        producto.setCategoria(combCategoria.getSelectionModel().getSelectedItem());
        producto.setVendedor(getVendedor());
        producto.setEstado(Estado.PUBLICADO);
        producto.setLikes(0);
        return producto;
    }

    @FXML
    void seleccionarImagen(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif")
        );

        // Obtener la ventana principal para que el diálogo se abra en el contexto adecuado
        Stage stage = (Stage) productoImagen.getScene().getWindow();
        imagenSeleccionada = fileChooser.showOpenDialog(stage);

        if (imagenSeleccionada != null) {
            // Mostrar la imagen seleccionada en el ImageView
            productoImagen.setImage(new Image(imagenSeleccionada.toURI().toString()));
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

    private void limpiarCampos() {
        txtNombre.setText("");
        txtNombre.getPromptText();
        txtPrecio.setText("");
        txtPrecio.getPromptText();
        combCategoria.getSelectionModel().clearSelection();
        combCategoria.setPromptText("Categoria");
        productoImagen.setImage(null);

    }

    private boolean verificarCampos(Producto producto) {
        String mensaje = "";

        if (producto.getNombreProducto() == null || txtNombre.getText().isEmpty())
            mensaje += "El nombre es invalido \n";

        if (txtPrecio.getText().isEmpty())
            mensaje += "El apellido es invalido \n";

        if (producto.getCategoria() == null || combCategoria.getSelectionModel().getSelectedItem() == null)
            mensaje += "El categoria es invalido \n";

        if (mensaje.isEmpty()) {

            return true;

        } else {
            mostrarMensaje("Notificacion", "Datos Invalidos", mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
