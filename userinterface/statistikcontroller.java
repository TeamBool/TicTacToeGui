package userinterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class statistikcontroller {

    public void backbutton2(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/main.fxml"));
            Stage stage = new Stage();
            stage.setTitle(" ");
            stage.setScene(new Scene(root, 600, 570));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }
    public void doExit() {
        Platform.exit();
    }
}
