package userinterface;

public class ActiveGame {
    public final int gameID;
    public final String status;
    public final int userX;
    public final int userO;
    public final String turn;

    ActiveGame(int gameID, String status, int userX, int userO, String turn) {
        this.gameID = gameID;
        this.turn = turn;
        this.status = status;
        this.userX = userX;
        this.userO = userO;
    }
}
