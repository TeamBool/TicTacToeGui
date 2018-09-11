package userinterface;

public interface EventFactory<T> {
    T createRegister();

    T createRegistrationAbort();

    T createActNow(int var1);

    T createDoneActing(int var1);

    T createWinner(String var1);

    T createMove(int x, int y, String tile, String owner);

    T createNewGame();

    T createGameFinish(int id, String name);

    T createGamePause(int id);

    T createChat(String message);

    T createPlayer(String name, String type);

    T createLogin(String name, int id);
}
