package shippingsystem.utils;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created Friday 4th,June,2021.
 *
 * @author Leen
 */
public class BasicDB {

    //This class will be used as the low-level interface with the database
    /**
     * Represents the MySQL driver name.
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * Represents the URL of the database which the application will connect to.
     */
    private static final String DATABASE_URL = "jdbc:mysql://localhost/shipping";
    /**
     * Represents the username of the database account.
     */
    private static final String DATABASE_USER = "root";
    /**
     * Represents the password of the database account.
     */
    private static final String DATABASE_PASSWORD = "";
    /**
     * Represents the connection object that will be used to initialize a new
     * database session.
     */
    private static Connection connection = null;

    //Functionality
    /**
     * Returns a connection/session to/with a database in MySQL database server.
     *
     * @return
     */
    private static void connect() {
        //Create the connection with mysql server 
        try {
            //Register the driver
            Class.forName(DRIVER);
            //Initilaise the connection
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the connection with the database server.
     */
    private static void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {

        }
    }

    /**
     * Executes a query to retrieve data from a certain table in the database.
     *
     * @param query the query to execute as a String object
     * @return
     */
    public static ResultSet retrieve(String query) {
        //First connect to the database server
        connect();
        try {
            //Create a statement in order to execute the query
            Statement statement = connection.createStatement();
            //Execute the query
            CachedRowSetImpl result = new CachedRowSetImpl();
            //Save the result as a cached result 
            result.populate(statement.executeQuery(query));
            //Close the connection
            disconnect();
            return result;
        } catch (SQLException ex) {

        }
        disconnect();
        return null;
    }

    /**
     * Executes a DML query on a specific table in the database.
     *
     * @param query
     * @return
     */
    public static int manipulate(String query) {
        //First connect to the database server
        connect();
        try {
            //Create the Statement object
            Statement statement = connection.createStatement();
            //Execute the DML query
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
