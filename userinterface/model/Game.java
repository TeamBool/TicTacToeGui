package userinterface.model;

import java.util.ArrayList;

public class Game {

    private int id;
    private Field field;
    private int size = 3;
    private ArrayList<Player> players = new ArrayList<Player>();
    private boolean running = false;

    public Game(){
        //TODO: get GameID form DB
    }

    public boolean startGame(){
        try {
            createField();
            this.running = true;
        } catch (Exception e){
            System.out.println("Fehler: " );
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Field createField(){
        this.field = new Field(this.size, players);
        return this.field;
    }

    private void setSize(int x){
        this.size = x;
    }

    public boolean addPlayer(Player player){
        try {
            players.add(player);
        } catch (Exception e){
            System.out.println("Fehler: " );
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean hasWinner()
    {
        if(this.running && this.field.checkWinner() == null){
            return false;
        } else {
            this.running = false;
            return true;
        }
    }

    public boolean setMove(Tile t){
        if(field.checkSetTile(t)){
            this.field.setTile(t);
            return true;
        } else
            return false;
    }

    public void addPlayer(String player, String tile){
        Tile.TILE_TYPE tile_type;
        if(tile.toLowerCase().equals("x"))
            tile_type = Tile.TILE_TYPE.X;
        else
            tile_type = Tile.TILE_TYPE.O;
        this.addPlayer(new Player(player, tile_type));
    }

    public Field getField(){
        return this.field;
    }

    public ArrayList getPlayerList(){
        return this.players;
    }

    public void setPaused() {
        //TODO
    }

    public boolean isPaused() {
        //TODO
        return false;
    }

    public void setRunning(boolean b) {
        //TODO
    }
}
