package userinterface;

import userinterface.connection.ServerConnection;
import userinterface.messages.CommandFactoryImpl;
import userinterface.model.Game;
import userinterface.model.Player;

import java.util.HashMap;

public class Server {

    public static ServerConnection serverConnection;
    public static CommandFactory commandFactory;
    public static CommIds commIds;
    public static boolean running = true;
    public static HashMap<Integer, Game> runningGames = new HashMap<>();
    public static HashMap<Integer, Player> onlinePlayers = new HashMap<Integer, Player>();

    public static void main(String[] args) {
        commIds = new CommIds();
        Server.commandFactory = new CommandFactoryImpl(commIds);
        if (args.length < 2)
            Server.serverConnection = new ServerConnection(33033, commandFactory);
        else
            Server.serverConnection = new ServerConnection(Integer.parseInt(args[1]), commandFactory);

        run();
    }

    /**
     * Starts the {@link Server}.
     */
    public static void run() {
        while (running) {

        }
        serverConnection.close();
    }

    public void close() {
        running = false;
        serverConnection.close();
    }


    public CommIds getCommIds() {
        return commIds;
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

    public void addGame(Game game) {
        runningGames.put(game.getId(), game);
    }

    public boolean removeGame(int id) {
        if (runningGames.remove((id)) != null)
            return true;
        else
            return false;
    }
}
