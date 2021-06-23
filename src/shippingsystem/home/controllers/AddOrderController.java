package shippingsystem.home.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shippingsystem.home.models.ItemDAO;
import shippingsystem.home.models.ItemModel;
import shippingsystem.home.models.OrderDAO;
import shippingsystem.home.models.OrderModel;
import shippingsystem.utils.AppData;
import shippingsystem.utils.DialogUtil;
import shippingsystem.utils.Helpers;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class AddOrderController implements Initializable {

    private ItemDAO itemDAO = new ItemDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private int totalPrice = 0;

    @FXML
    private ComboBox<ItemModel> itemCb;
    @FXML
    private Button addItemBtn;
    @FXML
    private TextField qtyTf;
    @FXML
    private TableView<ItemModel> orderDetailsTable;
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
        TableColumn column1 = new TableColumn("#");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn column2 = new TableColumn("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn column3 = new TableColumn("Item Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn column4 = new TableColumn("Quantity");
        column4.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn column5 = new TableColumn("Company");
        column5.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        orderDetailsTable.getColumns().addAll(column1, column2, column3, column4, column5);

        ArrayList<ItemModel> items = itemDAO.getItems();
        itemCb.setItems(FXCollections.observableArrayList(items));
        
        System.out.println("address: " + AppData.user.getAddress());
        addressTf.setText(AppData.user.getAddress());
        
        addHandlers();
    }

    private void addHandlers() {
        // Formatters
        qtyTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                qtyTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cancelBtn.setOnAction(event -> {
            Helpers.showScene(cancelBtn, "/shippingsystem/home/views/Home.fxml");
        });

        addItemBtn.setOnAction(event -> {
            if (itemCb.getValue() != null && !qtyTf.getText().trim().isEmpty()) {
                ItemModel im = itemCb.getValue();
                im.setQty(Integer.parseInt(qtyTf.getText().trim()));
                orderDetailsTable.getItems().add(im);
                totalPrice += (im.getQty() * im.getPrice());
                totalPriceLb.setText(totalPrice + "$");
                itemCb.getSelectionModel().clearSelection();
                qtyTf.clear();
            }
        });

        createBtn.setOnAction(event -> {
            String address = addressTf.getText().trim();
            List<ItemModel> items = orderDetailsTable.getItems();
            if (items.isEmpty()) {
                DialogUtil.getInstance().show("Please select at least one item", "Error");
            } else if (address.isEmpty()) {
                DialogUtil.getInstance().show("Please enter your address", "Error");
            } else {
                Random rand = new Random();
                int number = 0, c = 1;
                for (int i = 0; i < 5; i++) {
                    number += (rand.nextInt(10) * c);
                    c *= 10;
                }

                String approxTime = (rand.nextInt(5) + 2) + " days";

                OrderModel order = new OrderModel(number, address, totalPrice, approxTime, "Processing", AppData.user.getId());
                orderDAO.add(order, items);
                Helpers.showScene(createBtn, "/shippingsystem/home/views/Home.fxml");
            }
        });
    }

}
