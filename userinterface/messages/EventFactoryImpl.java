package userinterface.messages;

import userinterface.EventFactory;
import userinterface.TicTacToeController;


/**
 * Created by DJ MacHack on 12.09.2016.
 */
public class EventFactoryImpl implements EventFactory<Event> {

    public EventFactoryImpl()
    {}


    @Override
    public Event createRegistered() {
        return null;
    }

    @Override
    public Event createRegistrationAborted() {
        return null;
    }

    @Override
    public Event createActNow(int var1) {
        return null;
    }

    @Override
    public Event createDoneActing(int var1) {
        return null;
    }

    @Override
    public Event createWinner(String var1) {
        return null;
    }

    @Override
    public Event createMoved(int x, int y, String tile, String name) {
        return new Moved(x, y, tile, name);
    }

    @Override
    public Event createNewGame() {
        return new NewGame();
    }

    @Override
    public Event createGameFinished(int id, String name) {
        return new GameFinished(id, name);
    }

    @Override
    public Event createGamePaused(int id) {
        return new GamePaused(id);
    }

    @Override
    public Event createChat(String message) {
        return new Chat(message);
    }

    @Override
    public Event createPlayer(String name, String type) {
        return new NewPlayer(name, type);
    }
}
