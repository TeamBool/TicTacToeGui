
package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;
import userinterface.model.OTile;
import userinterface.model.Tile;
import userinterface.model.XTile;

public class Move implements Event {

    private int x;
    private int y;
    private String tile = "";
    private String name = "";
    private Tile t;

    public Move(int x, int y, String tile, String name){this.x = x;
    this.y = y;
    this.tile = tile;
    this.name = name;
    if(tile.toLowerCase().equals("x"))
        this.t = new XTile(this.name, x, y);
    else
        this.t = new OTile(this.name, x, y);
    }

    public void executeEvent(Game game){
        if(validateEvent(game))
            game.getField().setTile(t);
    }

    public boolean validateEvent(Game game){

        return game.getField().checkSetTile(this.t);
    }


    public void sendCommand(ClientConnection cc) {
        throw new UnsupportedOperationException("You are not allowed to send a WinnerEvent!");
    }


    public String getText() {
        return String.format("new Tile");
    }

    public String getNameWnr() {
        return this.name;
    }
}
