/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingsystem.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shippingsystem.auth.presenters.LoginController;

/**
 *
 * @author alilo
 */
public class Helpers {

    public static void showScene(Node node, String url, String title) {
        try {
            Stage signupWindow = (Stage) node.getScene().getWindow();
            FXMLLoader mainLoader = new FXMLLoader(LoginController.class.getClass().getResource(url));
            AnchorPane root = mainLoader.load();

            Scene scene = new Scene(root);

            signupWindow.setTitle(title);
            signupWindow.setScene(scene);
            signupWindow.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void showScene(Node node, String url) {
        showScene(node, url, "Shipping System");
    }
}
