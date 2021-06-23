package shippingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class ShippingSystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/shippingsystem/auth/views/Login.fxml"));
            AnchorPane root = mainLoader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
