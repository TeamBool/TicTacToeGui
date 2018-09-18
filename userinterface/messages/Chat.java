package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;

public class Chat implements Event {

    //Fields
    private String message;

    /**
     * @param message
     */
    public Chat(String message) {
        this.message = message;
    }

    /**
     * Execute as {@link Event} for a given {@link Game}
     *
     * @param game to manipulate
     */
    @Override
    public void executeEvent(Game game) {
        //game.
        //client.resetCreatureStats(game);
    }

    /**
     * Tests if this {@link Event}/{@link Command} is valid as {@link Event} for a given {@link Game}
     *
     * @param game {@link Game} to test
     * @return returns true if valid else false
     */
    @Override
    public boolean validateEvent(Game game) {
        return true;
    }

    @Override
    public void sendCommand(ClientConnection cc) {
        throw new UnsupportedOperationException("You are not allowed to call a ChatEvent!");
    }

    @Override
    public String getText() {
        return String.format(this.message);
    }

}
