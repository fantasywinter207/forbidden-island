package card.impl;

import card.Card;
import island.IslandTile;
import player.Player;
import game.GameState;

public class SpecialActionCard extends Card {
    private final SpecialActionType actionType;

    public SpecialActionCard(SpecialActionType type) {
        super(CardType.SPECIAL_ACTION);
        this.actionType = type;

        switch(type) {
            case HELICOPTER_LIFT:
                setDescription("All players must be at Fools' Landing to win the game");
                break;
            case SANDBAGS:
                setDescription("Prevent a tile from flooding or sinking");
                break;
        }
    }

    @Override
    public boolean func(Player player, GameState gameState) {
        switch(actionType) {
            case HELICOPTER_LIFT:
                return gameState.useHelicopterLift(player);
            case SANDBAGS:
                // Sandbag cards require the player to choose which tile to protect
                String tileName = player.chooseTileToProtect();
                if (tileName == null || tileName.isEmpty()) {
                    System.out.println("The tile to be protected is not selected, and the sandbag card fails to be used");
                    return false;
                }

                // Get the corresponding island plate
                IslandTile tile = gameState.getIslandTile(tileName);
                if (tile == null) {
                    System.out.println("The specified island plate could not be found: " + tileName);
                    return false;
                }

                // Execute sandbag protection logic
                if (tile.isSunk()) {
                    System.out.println("无法保护已沉没的板块: " + tileName);
                    return false;
                }

                // If the plate has been submerged, it returns to its normal state
                if (tile.isFlooded()) {
                    tile.restore(); //
                    System.out.println("Successful use of sandbags！" + tileName + " Return to a normal state");
                } else {
                    // 防止板块被淹没
                    System.out.println("Successful use of sandbags！" + tileName + " WILL NOT BE OVERWHELMED");
                }

                return true;
            default:
                return false;
        }
    }

    @Override
    public String getName() {
        return actionType.toString();
    }

    public SpecialActionType getActionType() {
        return actionType;
    }
}