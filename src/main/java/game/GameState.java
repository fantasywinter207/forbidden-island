package game;

import card.*;
import island.IslandTile;
import player.Player;
import java.util.List;
import java.util.Map;

public class GameState {
    private int waterLevel;
    private final int MAX_WATER_LEVEL = 10;
    private List<Player> players;

    private List<IslandTile> islandTiles; // 岛屿板块集合

    public boolean useHelicopterLift(Player player) {
        return true;
    }

    public void increaseWaterLevel() {
        waterLevel++;
        // Check if the game is over
        if (waterLevel >= MAX_WATER_LEVEL) {
            gameOver();
        }
    }

    public void reshuffleFloodDiscard() {

    }

    public void gameOver() {

    }

    public IslandTile getIslandTile(String tileName) {
        return islandTiles.stream()
                .filter(item -> item.getName().equalsIgnoreCase(tileName))
                .findFirst()
                .orElse(null);
    }

    public void handleSunkTile(String tileName) {
    }
}