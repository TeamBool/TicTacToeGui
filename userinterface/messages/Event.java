package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;



public interface Event {

    /**
     * Executes the Event at the given {@link Game} instance.
     *
     * @param game   {@link Game} to be updated
     */
    public void executeEvent(Game game);

    /**
     * Test the Event at the given {@link Game} instance.
     *
     * @param game   {@link Game} to be updated
     * @return true, if Event is valid
     */
    public boolean validateEvent(Game game);

    /**
     * Sends a command to the server using a given {@link ClientConnection} instance
     *
     * @param cc Connection instance to use for sending
     */
    public void sendCommand(ClientConnection cc);

    /**
     * Gets a text that can be printed out into a logfile
     *
     * @return a string that represents the event in a logfile
     */
    public String getText();
}
