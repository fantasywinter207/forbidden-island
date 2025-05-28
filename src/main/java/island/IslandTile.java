package island;

import player.Player;
import java.util.ArrayList;
import java.util.List;

public class IslandTile {
    public enum TileState {
        NORMAL,   // 正常
        FLOODED,  // 被淹没
        SUNK      // 已沉没
    }

    private final String name;
    private final List<Player> playersOnTile;
    private TileState state;

    public IslandTile(String name) {
        this.name = name;
        this.playersOnTile = new ArrayList<>();
        this.state = TileState.NORMAL;
    }

    // 淹没板块
    public void flood() {
        if (state == TileState.NORMAL) {
            state = TileState.FLOODED;
        }
    }

    // 沉没板块
    public void sink() {
        if (state == TileState.FLOODED) {
            state = TileState.SUNK;
        }
    }

    // 玩家登上板块
    public void addPlayer(Player player) {
        playersOnTile.add(player);
        player.setCurrentTile(this);
    }

    // 玩家离开板块
    public void removePlayer(Player player) {
        playersOnTile.remove(player);
    }

    // 检查板块是否可通行
    public boolean isPassable() {
        return state != TileState.SUNK;
    }

    // Getters
    public String getName() { return name; }
    public TileState getState() { return state; }
    public boolean isFlooded() { return state == TileState.FLOODED; }
    public boolean isSunk() { return state == TileState.SUNK; }
    public List<Player> getPlayersOnTile() { return new ArrayList<>(playersOnTile); }
}