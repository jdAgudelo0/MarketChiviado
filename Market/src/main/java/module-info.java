module com.proyecto.market {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.proyecto.market to javafx.fxml;
    exports com.proyecto.market;
    exports com.proyecto.market.Model;
    opens com.proyecto.market.Model to javafx.fxml;
}