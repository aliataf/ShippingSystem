package shippingsystem.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Ali Ataf + Lilas Meraii
 */
public class DialogUtil {

    private static DialogUtil instance;

    private DialogUtil() {
    }

    public static DialogUtil getInstance() {
        if (instance == null) {
            instance = new DialogUtil();
        }
        return instance;
    }

    public void show(Alert.AlertType type, String contentText, String title, ButtonType... buttons) {
        Alert alert = new Alert(type, contentText, buttons);
        alert.setTitle(title);
        alert.show();
    }

    public void show(String contentText, String title, ButtonType... buttons) {
        show(Alert.AlertType.NONE, contentText, title, buttons);
    }

    public void show(String contentText, String title) {
        show(Alert.AlertType.NONE, contentText, title, ButtonType.YES);
    }

}
