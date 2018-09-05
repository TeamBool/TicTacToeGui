package userinterface;

public interface EventFactory<T> {
    T createRegistered();

    T createRegistrationAborted();

    T createActNow(int var1);

    T createDoneActing(int var1);

    T createWinner(String var1);

    T createMoved(int x, int y, String tile, String owner);

    T createNewGame();

    T createGameFinished(int id, String name);

    T createGamePaused(int id);

    T createChat(String message);

    T createPlayer(String name, String type);
}
