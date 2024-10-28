package com.proyecto.market;

import com.proyecto.market.Model.Market;
import com.proyecto.market.Model.Vendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/proyecto/market/inicioSesion-view.fxml"));
            primaryStage.setTitle("Inicio Sesion");
            primaryStage.setScene(new Scene(root, 1024, 765));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
        Market market= Market.getInstance();
        Vendedor vendedor= new Vendedor("Juan", " rodriguez", " 11231231", " Juan12", " 12Juan12");
        market.registrarUsuario(vendedor);
        if(market.login("Juan12", "12Juan12")){
            System.out.println("Si esta registrado");
        }
    }
}

