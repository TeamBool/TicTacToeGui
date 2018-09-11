package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;

public class NewGame implements Event {

    private String teamName = "";

    public NewGame() {

    }

    public void executeEvent(Game game) {
        game = new Game();
    }

    public boolean validateEvent(Game game) {
        return true;
    }


    public void sendCommand(ClientConnection cc) {
        throw new UnsupportedOperationException("You are not allowed to send a WinnerEvent!");
    }


    public String getText() {
        return String.format("newGame");
    }

}
