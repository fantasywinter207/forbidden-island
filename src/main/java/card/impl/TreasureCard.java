package card.impl;

import card.Card;
import island.IslandTile;
import player.Player;
import game.GameState;

public class TreasureCard extends Card {
    private final TreasureType treasureType;

    public TreasureCard(TreasureType type) {
        super(CardType.TREASURE);
        this.treasureType = type;
        setDescription("Collect 4 of these to claim the " + type + " treasure");
    }

    @Override
    public boolean func(Player player, GameState gameState) {
        // Check that the player is standing on the correct tile for the corresponding treasure
        if (!isOnCorrectTile(player)) {
            System.out.println("You must stand on the tile of the corresponding treasure to redeem it！");
            return false;
        }

        // 检查玩家是否有足够的同类型宝藏卡
        int cardCount = player.getPlayerCardHandler()
                .getTreasureCards()
                .size();

        if (cardCount < 4) {
            System.out.println("A minimum of 4 is required" + treasureType + "TO REDEEM TREASURES！");
            return false;
        }

        // Try to redeem the treasure
        boolean success = gameState.claimTreasure(player, treasureType);

        if (success) {
            // 从玩家手牌中移除4张宝藏卡
            player.getPlayerCardHandler().discardCardsByType(CardType.TREASURE);
            System.out.println(player.getName() + "Successfully redeemed" + treasureType + "BURIED TREASURE！");
        }

        return success;
    }

    // Check that the player is standing on the correct tile for the corresponding treasure
    private boolean isOnCorrectTile(Player player) {
        IslandTile currentTile = player.getCurrentTile();
        if (currentTile == null) return false;

        switch (treasureType) {
            case CRYSTAL_OF_FIRE:
                return currentTile.getName().equals("Cave of Embers");
            case STATUE_OF_THE_WIND:
                return currentTile.getName().equals("Howling Garden");
            case OCEANS_CHALICE:
                return currentTile.getName().equals("Coral Palace");
            case EARTH_STONE:
                return currentTile.getName().equals("Temple of the Earth");
            default:
                return false;
        }
    }

    @Override
    public String getName() {
        return treasureType.toString();
    }

    public TreasureType getTreasureType() {
        return treasureType;
    }

    public boolean isKeepAfterUse() {
        return false;
    }
}