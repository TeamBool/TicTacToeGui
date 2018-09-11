
package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;

public class GameFinish implements Event {

    private String teamName = "";

    public GameFinish(int id, String NameWnr){
        this.teamName = NameWnr;
    }

    public void executeEvent(Game game){
        game.setRunning(false);
    }

    public boolean validateEvent(Game game){
     return true;
    }


    public void sendCommand(ClientConnection cc) {
        throw new UnsupportedOperationException("You are not allowed to send a WinnerEvent!");
    }


    public String getText() {
        return String.format("%s wins!", this.teamName);
    }

    public String getNameWnr() {
        return this.teamName;
    }
}
