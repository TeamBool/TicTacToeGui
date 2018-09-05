
package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;

public class NewPlayer implements Event {

    private String name;
    private String type;

    public NewPlayer(String name, String type){
        this.name = name;
        this.type = type;
    }

    public void executeEvent(Game game){
        game.addPlayer(this.name, this.type);
    }

    public boolean validateEvent(Game game){
        return game.getPlayerList().size() < 2;
    }


    public void sendCommand(ClientConnection cc) {
        throw new UnsupportedOperationException("You are not allowed to send a NewPlayer!");
    }


    public String getText() {
        return String.format("%s newPlayer!", this.name);
    }

}
