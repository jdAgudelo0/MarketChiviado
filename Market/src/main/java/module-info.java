module com.proyecto.market {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.proyecto.market to javafx.fxml; // Para la clase principal, si es necesario
    exports com.proyecto.market; // Exporta tu paquete principal
    exports com.proyecto.market.Model; // Exporta tu modelo
    opens com.proyecto.market.Model to javafx.fxml; // Si Model tiene FXML

    // Asegúrate de abrir el paquete donde está tu controlador
    opens com.proyecto.market.View to javafx.fxml; // Abre el paquete del controlador
}
