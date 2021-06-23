package shippingsystem.home.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shippingsystem.utils.BasicDB;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class OrderDAO {

    public OrderModel add(OrderModel order, List<ItemModel> items) {
        OrderModel temp = null;

        try {
            // Form the query
            String query = "insert into orders (number, shipping_address, total_price, approx_time, status, user_id) values("
                    + order.getNumber() + ",'" + order.getShippingAddress() + "'," + order.getTotalPrice() + ",'" + order.getApproxTime() + "','" + order.getStatus() + "'," + order.getUserId() + ")";
            // Execute the query
            int rows = BasicDB.manipulate(query);
            query = "Select * from orders where id = (select max(id) from orders)";

            // Execute the query
            ResultSet result = BasicDB.retrieve(query);
            while (result.next()) {
                temp = new OrderModel();
                temp.setId(result.getInt(1));
                temp.setNumber(result.getInt(2));
                temp.setShippingAddress(result.getString(3));
                temp.setTotalPrice(result.getInt(4));
                temp.setApproxTime(result.getString(5));
                temp.setStatus(result.getString(6));
                temp.setUserId(result.getInt(7));
            }
            for (ItemModel om : items) {
                query = "insert into order_details values (" + temp.getId() + "," + om.getId() + "," + om.getQty() + ")";
                rows = BasicDB.manipulate(query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }

    /**
     * Retrieves all the stored records in the "orders" table.
     *
     * @return
     */
    public ArrayList<OrderModel> getOrders() {
        ArrayList<OrderModel> res = new ArrayList<>();
        // Form the Select * query
        String query = "Select * from orders";
        // Execute the query
        ResultSet result = BasicDB.retrieve(query);
        // Copy the returned result set into the array list
        try {
            while (result.next()) {
                OrderModel temp = new OrderModel();
                // Form the OrderModel object for each returned row
                temp.setId(result.getInt(1)); // The first index of the columns is 1 not 0
                temp.setNumber(result.getInt(2));
                temp.setShippingAddress(result.getString(3));
                temp.setTotalPrice(result.getInt(4));
                temp.setApproxTime(result.getString(5));
                temp.setStatus(result.getString(6));

                // Add the record to the list
                res.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
