module com.proyecto.market {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.xml.crypto;
    requires java.desktop;
    requires java.logging;


    opens com.proyecto.market to javafx.fxml;
    exports com.proyecto.market;

    opens com.proyecto.market.Utils to javafx.fxml;
    exports com.proyecto.market.Utils;

    exports com.proyecto.market.Model;
    exports com.proyecto.market.Controller;
    opens com.proyecto.market.Controller to javafx.fxml;

    opens com.proyecto.market.View to javafx.fxml;
    exports com.proyecto.market.View;

    opens com.proyecto.market.View.Crud to javafx.fxml;
    exports com.proyecto.market.View.Crud;

}