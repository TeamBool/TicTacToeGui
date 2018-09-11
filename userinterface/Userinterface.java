

package userinterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import userinterface.connection.ClientConnection;
import userinterface.messages.Event;
import userinterface.messages.EventFactoryImpl;

public class Userinterface extends Application {
    
    public static ClientConnection clientConnection;
    public static EventFactory eventFactory;
    public static int playerID;
    public static String playerName;

    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/userinterface/startside.fxml"));
        Scene scene = new Scene(root,600,350);
        stage.setScene(scene);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Userinterface.eventFactory = new EventFactoryImpl();
        Userinterface.clientConnection = new ClientConnection("127.0.0.1", 33033, Userinterface.eventFactory);
        launch(args);
    }
    
}

