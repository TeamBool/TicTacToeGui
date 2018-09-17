package userinterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLDocController {

    @FXML
    private Label lblstatus;

    @FXML
    private TextField tx_username;

    @FXML
    private TextField tx_email;

    @FXML
    private PasswordField px_password;


    public void handlebutlog(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/log.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }


    public void signin(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/register.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Main Menue");
            stage.setScene(new Scene(root, 600, 350));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }

    public void gamebutton(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/TicTacToe.fxml"));
            Stage stage = new Stage();
            stage.setTitle("TicTacToe");
            stage.setScene(new Scene(root, 600, 570));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }

    public void gameroom(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/spielraum.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Main Menue");
            stage.setScene(new Scene(root, 600, 570));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    public void gamemultiplayer(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/TicTacToemulti.fxml"));
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root, 600, 570));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    public void statistikbtn(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/statistik.fxml"));
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root, 600, 570));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    public void historybtn(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/history.fxml"));
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root, 600, 570));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
    public void backbutton(ActionEvent event) throws Exception {

        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/startside.fxml"));
            Stage stage = new Stage();
            stage.setTitle(" ");
            stage.setScene(new Scene(root, 600, 350));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }

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


    public void handleCloseButtonAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    public void doExit() {
        Platform.exit();
    }


}