/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingsystem.auth.presenters;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import shippingsystem.utils.Helpers;

/**
 * FXML Controller class
 *
 * @author alilo
 */
public class LoginController implements Initializable {

    @FXML
    private Button signupBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signupBtn.setOnAction(event -> {
            Helpers.showScene(signupBtn, "/shippingsystem/auth/views/Signup.fxml");
        });
    }
}
