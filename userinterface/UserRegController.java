package userinterface;


import userinterface.SqliteConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserRegController  {

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;


   
    @FXML
    void signup(ActionEvent event) throws Exception {

        
        try {

            String username = tf_username.getText();
           
            String password = pf_password.getText();
            //Database database = new Database("D:\\Hendrik\\Onedrive\\HTW\\SS 18\\VT\\TicTacToeGuijdbc:sqlite:sqlite.db");
            Database database = (Database) SqliteConnection.Connector();
            if(database.registerUser(username,password));
                backbutton(event);
        }catch (Exception ex) {}
}

   
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void backbutton(ActionEvent event)throws Exception {
        
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/startside.fxml"));
            Stage stage = new Stage();
            stage.setTitle(" ");
            stage.setScene(new Scene(root, 600, 350));
            stage.show();
            
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
        public void doExit(){
            Platform.exit();
        
         
    }
}
