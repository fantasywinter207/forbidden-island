package card.impl;

import card.Card;
import island.IslandTile;
import player.Player;
import game.GameState;

public class FloodCard extends Card {
    private final String tileName;

    public FloodCard(String tileName) {
        super(CardType.FLOOD);
        this.tileName = tileName;
        setDescription("Flood or sink the " + tileName + " tile");
    }

    @Override
    public boolean func(Player player, GameState gameState) {
        // 获取对应岛屿板块
        IslandTile tile = gameState.getIslandTile(tileName);
        if (tile == null) {
            System.err.println("Error: Island plate not found " + tileName);
            return false;
        }
        // Performs the Flood effect
        if (!tile.isFlooded()) {
            // UnSubmerged → Submerged
            tile.flood();
            System.out.println(tileName + " HAS BEEN SUBMERGED！");
        } else {
            // Submerged → Sunk
            tile.sink();
            System.out.println(tileName + " SUNK！");
            // Check to see if there are any players on the sunken plate
            gameState.handleSunkTile(tileName);
        }

        return true;
    }

    @Override
    public String getName() {
        return "Flood: " + tileName;
    }

    public String getTileName() {
        return tileName;
    }
}