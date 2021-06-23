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
import shippingsystem.utils.DialogUtil;
import shippingsystem.utils.Helpers;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class SignupController implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private Button signupBtn;
    @FXML
    private TextField emailTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField phoneTf;
    @FXML
    private TextField addressTf;
    @FXML
    private PasswordField passwordTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signupBtn.setOnAction(event -> {
            String email = emailTf.getText().trim();
            String password = passwordTf.getText().trim();
            String name = nameTf.getText().trim();
            String phone = phoneTf.getText().trim();
            String address = addressTf.getText().trim();

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                DialogUtil.getInstance().show(Alert.AlertType.ERROR, "Fields cannot be empty", "Error");
            } else {
                UserDAO userDAO = new UserDAO();
                ArrayList<UserModel> users = userDAO.getUsers();
                boolean isExist = false;
                for (UserModel um : users) {
                    if (um.getEmail().equals(email)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    DialogUtil.getInstance().show("There is already account with this email", "Error");
                } else {
                    UserModel userModel = new UserModel();
                    userModel.setEmail(email);
                    userModel.setPassword(password);
                    userModel.setName(name);
                    userModel.setPhone(phone);
                    userModel.setAddress(address);
                    userDAO.add(userModel);
                    DialogUtil.getInstance().show("Congratulations, you have been registered successfully!", "Success");
                    loginBtn.fire();
                }
            }
        });

        loginBtn.setOnAction(event -> {
            Helpers.showScene(loginBtn, "/shippingsystem/auth/views/Login.fxml");
        });
    }

}
