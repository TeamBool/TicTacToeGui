package userinterface;

public class History {
    public final int gameID;
    public final String status;
    public final int winner;
    public final int loser;
    public final int tie;

    History(int gameID, String status, int winner, int loser, int tie) {
        this.gameID = gameID;
        this.tie = tie;
        this.status = status;
        this.winner = winner;
        this.loser = loser;
    }
}
