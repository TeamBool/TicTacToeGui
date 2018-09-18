package userinterface.messages;

import userinterface.EventFactory;



public class EventFactoryImpl implements EventFactory<Event> {

    public EventFactoryImpl() {
    }


    @Override
    public Event createRegister() {
        return null;
    }

    @Override
    public Event createRegistrationAbort() {
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
    public Event createMove(int x, int y, String tile, String name) {
        return new Move(x, y, tile, name);
    }

    @Override
    public Event createNewGame() {
        return new NewGame();
    }

    @Override
    public Event createGameFinish(int id, String name) {
        return new GameFinish(id, name);
    }

    @Override
    public Event createGamePause(int id) {
        return new GamePause(id);
    }

    @Override
    public Event createChat(String message) {
        return new Chat(message);
    }

    @Override
    public Event createPlayer(String name, String type) {
        return new NewPlayer(name, type);
    }

    @Override
    public Event createLogin(String name, int id) {
        return new Login(name, id);
    }
}
