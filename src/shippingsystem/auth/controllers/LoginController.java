package shippingsystem.auth.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shippingsystem.models.UserDAO;
import shippingsystem.models.UserModel;
import shippingsystem.utils.AppData;
import shippingsystem.utils.DialogUtil;
import shippingsystem.utils.Helpers;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class LoginController implements Initializable {

    @FXML
    private Button signupBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField emailTf;
    @FXML
    private PasswordField passwordTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginBtn.setOnAction(event -> {
            String email = emailTf.getText().trim();
            String password = passwordTf.getText().trim();
            if (email.isEmpty() || password.isEmpty()) {
                DialogUtil.getInstance().show(Alert.AlertType.ERROR, "Fields cannot be empty", "Error");
            } else {
                UserDAO userDAO = new UserDAO();
                ArrayList<UserModel> users = userDAO.getUsers();
                boolean isExist = false;
                for (UserModel um : users) {
                    System.out.println(um.getEmail() + " " + um.getPassword());
                    if (um.getEmail().equals(email) && um.getPassword().equals(password)) {
                        DialogUtil.getInstance().show("Welcome " + um.getName(), email);
                        isExist = true;
                        AppData.user = um;
                    }
                }
                if (isExist) {
                    Helpers.showScene(signupBtn, "/shippingsystem/home/views/Home.fxml");
                } else {
                    DialogUtil.getInstance().show("Invalid email or password", email);
                }
            }
        });

        signupBtn.setOnAction(event -> {
            Helpers.showScene(signupBtn, "/shippingsystem/auth/views/Signup.fxml");
        });
    }
}
