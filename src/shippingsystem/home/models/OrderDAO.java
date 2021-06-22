package shippingsystem.home.models;

import shippingsystem.models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shippingsystem.utils.BasicDB;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class OrderDAO {

    public boolean retrieve(String category) {
        //Build the SQL query
        String query = "select * from books where category = '" + category + "'";
        //Execute the query via the BasicDB methods
        ResultSet result = BasicDB.retrieve(query);

        try {
            if (!result.next()) {
                return false;
            }
            do {
                //Copy the returned result set into the array list
                OrderModel temp = new OrderModel();
                //Form the BookModel object for each returned row
                /*
                temp.setName(result.getString(2)); // The first index of the columns is 1 not 0
                temp.setCategory(result.getString(3));
                temp.setNumOfCopies(result.getInt(4));
                 */
            } while (result.next());
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * Inserts a new record to the database based on the entered order data from
     * the GUI.
     *
     * @param order
     */
    public void add(OrderModel order) {
        // Form the query
        String query = "insert into orders (number, shipping_address, total_price, approx_time, status) values( '"
                + order.getNumber() + "','" + order.getShippingAddress() + "','" + order.getTotalPrice() + "','" + order.getApproxTime() + "','" + order.getStatus() + "')";
        // Execute the query
        int rows = BasicDB.manipulate(query);
    }

    /**
     * Updates the number of copies of a certain book given its name.
     *
     * @param name
     * @param newCopies
     */
    public void update(String name, int newCopies) {
        //Form the query 
        String query = "update books set copies = " + newCopies + " where name= '" + name + "'";
        //Execute the query 
        int rows = BasicDB.manipulate(query);
    }

    /**
     * Deletes a record from the table based on the order id.
     *
     * @param id
     */
    public void delete(int id) {
        // Form the delete query
        String query = "delete from orders where id= " + id;
        // Execute query
        int rows = BasicDB.manipulate(query);
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
