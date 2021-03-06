package userinterface.messages;

import userinterface.CommIds;
import userinterface.CommandFactory;

public class CommandFactoryImpl implements CommandFactory<Command> {

    private CommIds commIds;
    private int playerCount;

    public CommandFactoryImpl(CommIds commIds) {
        this.commIds = commIds;
        playerCount = 0;
    }

    public CommIds getCommIds() {
        return commIds;
    }

    public int getPlayerCount() {
        return this.playerCount;
    }

    @Override
    public Command createRegister(int var1, String var2, String var4) {
        return null;
    }

    @Override
    public Command createDoneActing(int var1) {
        return null;
    }

    @Override
    public Command createWatch(int var1) {
        return null;
    }

    @Override
    public Command createMoved(int c, int var1, int var2, String n, String m) {
        return null;
    }

    @Override
    public Command createNewGame(int c) {
        return null;
    }

    @Override
    public Command createGameFinished(int c, int var1, String msg) {
        return null;
    }

    @Override
    public Command createGamePaused(int c, int var1) {
        return null;
    }

    @Override
    public Command lostPassword(int var1) {
        return null;
    }

    @Override
    public Command createChat(int c, String msg) {
        return null;
    }

    @Override
    public Command createPlayer(int c, String n, String t) {
        return null;
    }

    @Override
    public Command createLogin(int c, String a, int b) {
        return null;
    }


}
