package shippingsystem.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shippingsystem.utils.BasicDB;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class UserDAO {

    /**
     * Inserts a new record to the database based on the entered user data from
     * the GUI.
     *
     * @param user
     */
    public void add(UserModel user) {
        // Form the query
        String query = "insert into users (email, password, name, phone, address) values( '"
                + user.getEmail() + "','" + user.getPassword() + "','" + user.getName() + "','" + user.getPhone() + "','" + user.getAddress() + "')";
        // Execute the query
        int rows = BasicDB.manipulate(query);
    }

    /**
     * Retrieves all the stored records in the "users" table.
     *
     * @return ArrayList<UserModel>
     */
    public ArrayList<UserModel> getUsers() {
        ArrayList<UserModel> res = new ArrayList<>();
        // Form the Select * query
        String query = "Select * from users";
        // Execute the query
        ResultSet result = BasicDB.retrieve(query);
        // Copy the returned result set into the array list
        try {
            while (result.next()) {
                UserModel temp = new UserModel();
                // Form the BookModel object for each returned row
                temp.setId(result.getInt(1));
                temp.setEmail(result.getString(2)); // The first index of the columns is 1 not 0
                temp.setPassword(result.getString(3));
                temp.setName(result.getString(4));
                // Add the record to the list
                res.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
