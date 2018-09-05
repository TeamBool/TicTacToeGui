package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.model.Game;

/**
 * Created by Team14 on 9/12/16.
 */
public interface Event {

    /**
     * Executes the Event at the given {@link Client} instance.
     * @param game {@link Game} to be updated
     * @param client {@link Client} to be used
     */
    public void executeEvent(Game game);

    /**
     * Test the Event at the given {@link Client} instance.
     * @param game {@link Game} to be updated
     * @param client {@link Client} to be used (for ActNow espacially)
     * @return true, if Event is valid
     */
    public boolean validateEvent(Game game);

    /**
     * Sends a command to the server using a given {@link ClientConnection} instance
     * @param cc Connection instance to use for sending
     */
    public void sendCommand(ClientConnection cc);

    /**
     * Gets a text that can be printed out into a logfile
     * @param cli The client on which the event should be executed on
     * @return a string that represents the event in a logfile
     */
    public String getText();
}
