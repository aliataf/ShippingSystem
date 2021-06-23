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
public class ItemDAO {

    /**
     * Deletes a record from the table based on the item id.
     *
     * @param id
     */
    public void delete(int id) {
        // Form the delete query
        String query = "delete from item where id= " + id;
        // Execute query
        int rows = BasicDB.manipulate(query);
    }

    /**
     * Retrieves all the stored records in the "items" table.
     *
     * @return
     */
    public ArrayList<ItemModel> getItems() {
        ArrayList<ItemModel> res = new ArrayList<>();
        // Form the Select * query
        String query = "Select * from items inner join companies on items.company_id = companies.id";
        // Execute the query
        ResultSet result = BasicDB.retrieve(query);
        // Copy the returned result set into the array list
        try {
            while (result.next()) {
                ItemModel temp = new ItemModel();
                // Form the OrderModel object for each returned row
                temp.setId(result.getInt(1)); // The first index of the columns is 1 not 0
                temp.setName(result.getString(2));
                temp.setPrice(result.getInt(3));
                temp.setCompanyId(result.getInt(4));
                temp.setCompanyName(result.getString(6));

                // Add the record to the list
                res.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

}
