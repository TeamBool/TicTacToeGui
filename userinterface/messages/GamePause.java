package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;


public class GamePause implements Event{

    //Fields
    private int gameID;

    /**
     * @param gameID
     */
    public GamePause(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Execute as {@link Event} for a given {@link Client}
     *
     * @param client to manipulate
     */
    @Override
    public void executeEvent(Game game) {
        game.setPaused();
    }

    /**
     * Tests if this {@link Event}/{@link Command} is valid as {@link Event} for a given {@link Client}
     *
     * @param client {@link Client} to test
     * @return returns true if valid else false
     */
    @Override
    public boolean validateEvent(Game game) {
        return !game.isPaused();
    }

    @Override
    public void sendCommand(ClientConnection cc) {
        throw new UnsupportedOperationException("You are not allowed to call a GamePausedEvent!");
    }

    @Override
    public String getText() {
        return String.format("Game is paused!");
    }

}
