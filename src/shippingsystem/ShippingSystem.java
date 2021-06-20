/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author alilo
 */
public class ShippingSystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("auth/views/Login.fxml"));
            AnchorPane root = mainLoader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
