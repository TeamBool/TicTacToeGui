package userinterface.messages;

import userinterface.connection.ClientConnection;
import userinterface.connection.ServerConnection;
import userinterface.model.Game;


public interface Command {
    /**
     * Executes the command at the given {@link Game} instance.
     * @param game Game model to change by the command
     */
    public void executeCommand(Game game);

    /**
     * Tests the command using the given {@link Game} instance
     * @param game {@link Game} instance
     * @return true, if command test was successful
     */
    public boolean validateCommand(Game game);

    /**
     * Sends the result of the executed command to the client using a given {@link ServerConnection}
     * @param sc Connection to the clients
     */
    public void sendResults(ServerConnection sc);
}
