package island;

import player.Player;
import java.util.ArrayList;
import java.util.List;

public class IslandTile {

    public enum TileState {
        NORMAL,   // normal
        FLOODED,  // flooded
        SUNK      // sunk
    }

    private final String name;
    private final List<Player> playersOnTile;
    private TileState state;

    public IslandTile(String name) {
        this.name = name;
        this.playersOnTile = new ArrayList<>();
        this.state = TileState.NORMAL;
    }

    // Submerged plates
    public void flood() {
        if (state == TileState.NORMAL) {
            state = TileState.FLOODED;
        }
    }

    // Sunken plates
    public void sink() {
        if (state == TileState.FLOODED) {
            state = TileState.SUNK;
        }
    }

    // Players ascend to the board
    public void addPlayer(Player player) {
        playersOnTile.add(player);
        player.setCurrentTile(this);
    }

    // The player leaves the board
    public void removePlayer(Player player) {
        playersOnTile.remove(player);
    }

    // Check if the plate is passable
    public boolean isPassable() {
        return state != TileState.SUNK;
    }

    public void restore() {
    }

    // Getters
    public String getName() { return name; }
    public TileState getState() { return state; }
    public boolean isFlooded() { return state == TileState.FLOODED; }
    public boolean isSunk() { return state == TileState.SUNK; }
    public List<Player> getPlayersOnTile() { return new ArrayList<>(playersOnTile); }
}