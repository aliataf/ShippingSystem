package shippingsystem.home.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shippingsystem.home.models.OrderModel;
import shippingsystem.utils.AppData;
import shippingsystem.utils.Helpers;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class ViewOrderController implements Initializable {

    private OrderModel order;

    @FXML
    private Label numberLb;
    @FXML
    private Label shippingAddressLb;
    @FXML
    private Label totalPriceLb;
    @FXML
    private Label approxTimeLb;
    @FXML
    private Label statusLb;
    @FXML
    private Button homeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        order = AppData.selectedOrder;
        fillOrderData();
        addHandlers();
    }

    private void fillOrderData() {
        numberLb.setText("" + order.getNumber());
        shippingAddressLb.setText(order.getShippingAddress());
        totalPriceLb.setText("" + order.getTotalPrice());
        approxTimeLb.setText(order.getApproxTime());
        statusLb.setText(order.getStatus());
    }
    
    private void addHandlers() {
        homeBtn.setOnAction(event -> {
            AppData.selectedOrder = null;
            Helpers.showScene(homeBtn, "/shippingsystem/home/views/Home.fxml");
        });
    }

}
