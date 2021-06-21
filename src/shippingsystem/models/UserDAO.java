package shippingsystem.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shippingsystem.utils.BasicDB;

/**
 * Created Wednesday 9th,June,2021.
 *
 * @author Leen
 */
public class UserDAO {

    //Functionality
    /**
     * Sends a "Select * " query to the DBMS for retrieving books from a certain
     * category/genre.
     *
     * @param category
     * @return
     */
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
                UserModel temp = new UserModel();
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
     * Inserts a new record to the database based on the entered book data from
     * the GUI.
     *
     * @param book
     */
    public void add(UserModel user) {
        // Form the query
        String query = "insert into users (email, password, name, phone, address) values( '"
                + user.getEmail() + "','" + user.getPassword() + "','" + user.getName() + "','" + user.getPhone() + "','" + user.getAddress() + "')";
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
     * Deletes a record from the table based on the book name(title).
     *
     * @param name
     */
    public void delete(String name) {
        //Form the delete query
        String query = "delete from books where name= '" + name + "'";
        //Execute query
        int rows = BasicDB.manipulate(query);
    }

    /**
     * Retrieves all the stored records in the "books" table.
     *
     * @return
     */
    public ArrayList<UserModel> getUsers() {
        ArrayList<UserModel> res = new ArrayList<>();
        // Form the Select * query
        String query = "Select * from users";
        // Execute the query
        ResultSet result = BasicDB.retrieve(query);
        // Copy the returned result set into the array list
        UserModel temp = new UserModel();
        try {
            while (result.next()) {
                // Form the BookModel object for each returned row
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
