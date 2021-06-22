/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingsystem.home.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import shippingsystem.utils.Helpers;

/**
 * FXML Controller class
 *
 * @author alilo
 */
public class AddOrderController implements Initializable {

    @FXML
    private ComboBox<?> itemCb;
    @FXML
    private Button addItemBtn;
    @FXML
    private TextField qtyTf;
    @FXML
    private TableView<?> orderDetailsTable;
    @FXML
    private Label totalPriceLb;
    @FXML
    private TextField addressTf;
    @FXML
    private Button createBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addHandlers();
    }

    private void addHandlers() {
        cancelBtn.setOnAction(event -> {
            Helpers.showScene(cancelBtn, "/shippingsystem/home/views/Home.fxml");
        });
    }

}
