package userinterface.model;

public class Player {

    private String name;
    private Tile.TILE_TYPE tileType;

    public Player(String name, Tile.TILE_TYPE type){
        this.name = name;
        this.tileType = type;
    }

    public Tile.TILE_TYPE getTileType(){
        return this.tileType;
    };

    public String getName(){
        return this.name;
    }
}
