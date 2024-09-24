module com.proyecto.market {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.proyecto.market to javafx.fxml;
    exports com.proyecto.market;
}