package userinterface.messages;

import userinterface.Userinterface;
import userinterface.connection.ClientConnection;
import userinterface.model.Game;


public class Login implements Event {

    //Fields
    private int id;
    private String name;

    /**
     * @param gameID
     */
    public Login(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Execute as {@link Event} for a given {@link Client}
     *
     * @param client to manipulate
     */
    @Override
    public void executeEvent(Game game) {
        try {
            Userinterface.semaphore.acquire();
            Userinterface.playerlist.put(this.id, this.name);
            Userinterface.semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if this {@link Event}/{@link Command} is valid as {@link Event} for a given {@link Client}
     *
     * @param client {@link Client} to test
     * @return returns true if valid else false
     */
    @Override
    public boolean validateEvent(Game game) {
        return !Userinterface.playerlist.containsKey(id);
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
