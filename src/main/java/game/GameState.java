package game;

import card.Card;
import island.IslandTile;
import player.Player;
import java.util.List;
import java.util.Map;

public class GameState {

    private static GameState gameState;
    private int waterLevel;
    private final int MAX_WATER_LEVEL = 10;
    private List<Player> players;

    private List<IslandTile> islandTiles; // Collection of island plates

    private GameState() {}

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

    public boolean claimTreasure(Player player, Card.TreasureType treasureType) {
        return false;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getMaxWaterLevel() {
        return MAX_WATER_LEVEL;
    }

    public static GameState getGameState() {
        if (gameState == null) gameState = new GameState();
        return gameState;
    }
}