package userinterface;

public interface CommandFactory<T> {
    T createRegister(int var1, String var2, String var4);

    T createDoneActing(int var1);

    T createWatch(int var1);

    T createMoved(int c, int var1, int var2, String n, String m);

    T createNewGame(int c);

    T createGameFinished(int c, int var1, String msg);

    T createGamePaused(int c, int var1);

    T lostPassword(int var1);

    T createChat(int c, String msg);

    T createPlayer(int c, String n, String t);

    T createLogin(int c, String a, int b);
}
