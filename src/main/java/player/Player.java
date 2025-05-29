package player;

import card.Card;
import game.GameState;
import island.IslandTile;

import java.util.List;

public class Player {
    private String name;
    private final PlayerCardHandler playerCardHandler;
    private String currentTile;
    private IslandTile islandTile;


    public Player() {
        playerCardHandler = new PlayerCardHandler(this);
    }

    public PlayerCardHandler getPlayerCardHandler() {
        return playerCardHandler;
    }


    public String chooseTileToProtect() {
        return "Breakers Bridge";
    }

    public void setCurrentTile(IslandTile islandTile) {
        this.islandTile = islandTile;
    }

    public IslandTile getCurrentTile() {
        return islandTile;
    }

    public String getName() {
        return name;
    }
}
