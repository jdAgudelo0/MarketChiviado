package com.proyecto.market.Utils;


import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

import java.util.function.UnaryOperator;

public class TextFormatterUtil {


    public static Change upperCaseFormat (Change change){
        change.setText(change.getText().toUpperCase());
        return change;
    }

    public static Change integerFormat (Change change){

        if (change.getText().matches("[0-9]*")){
            return change;
        }
        return null;
    }

    public static Change floatFormat(Change change) {
        String newText = change.getControlNewText();
        // Permitir solo números, punto decimal y el signo negativo
        if (newText.matches("[-]?\\d*\\.?\\d*")) {
            return change; // Permitir el cambio
        }
        return null; // Cancelar el cambio
    }


}


