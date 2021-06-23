package shippingsystem.home.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import shippingsystem.home.models.OrderDAO;
import shippingsystem.home.models.OrderModel;
import shippingsystem.utils.AppData;
import shippingsystem.utils.Helpers;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class HomeController implements Initializable {

    ArrayList<OrderModel> orders = new ArrayList<>();
    ArrayList<OrderModel> filteredOrders = new ArrayList<>();

    @FXML
    private TableView<OrderModel> ordersTable;
    private OrderDAO orderDAO = new OrderDAO();
    @FXML
    private Button addOrderBtn;
    @FXML
    private TextField searchTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn column1 = new TableColumn("#");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn column2 = new TableColumn("Order Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn column3 = new TableColumn("Shipping Address");
        column3.setCellValueFactory(new PropertyValueFactory<>("shippingAddress"));

        TableColumn column4 = new TableColumn("Total Price");
        column4.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        TableColumn column5 = new TableColumn("Approx Time");
        column5.setCellValueFactory(new PropertyValueFactory<>("approxTime"));

        TableColumn column6 = new TableColumn("Order Status");
        column6.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>> cellFactory
                = new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
            @Override
            public TableCell call(final TableColumn<OrderModel, String> param) {
                final TableCell<OrderModel, String> cell = new TableCell<OrderModel, String>() {

                    final Button btn = new Button("View");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                OrderModel order = getTableView().getItems().get(getIndex());
                                AppData.selectedOrder = order;
                                Helpers.showScene(btn, "/shippingsystem/home/views/ViewOrder.fxml");
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

        ordersTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, actionCol);

        orders = orderDAO.getOrders(AppData.user.getId());
        ObservableList<OrderModel> list = FXCollections.observableArrayList(orders);
        ordersTable.setItems(list);

        addHandlers();

    }

    private void addHandlers() {
        addOrderBtn.setOnAction(event -> {
            Helpers.showScene(addOrderBtn, "/shippingsystem/home/views/AddOrder.fxml");
        });

        searchTf.setOnKeyReleased(event -> {
            filteredOrders.clear();
            for (OrderModel om : orders) {
                if (String.valueOf(om.getNumber()).contains(searchTf.getText().trim())) {
                    filteredOrders.add(om);
                }
            }
            ObservableList<OrderModel> list = FXCollections.observableArrayList(filteredOrders);
            ordersTable.setItems(list);
        });
    }
}
