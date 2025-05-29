package card.impl;

import card.Card;
import card.CardManager;
import player.Player;
import game.GameState;

public class WatersRiseCard extends Card {

    public WatersRiseCard() {
        super(CardType.WATERS_RISE);
        setDescription("Raise water level and reshuffle flood discard pile");
    }

    @Override
    public boolean func(Player player, GameState gameState) {
        CardManager cardManager = CardManager.getInstance();
        if (gameState == null || cardManager == null) {
            System.err.println("GameState or CardManager not initialized for WatersRiseCard");
            return false;
        }

        // 1. Increase the water level
        int previousWaterLevel = gameState.getWaterLevel();
        gameState.increaseWaterLevel();
        int newWaterLevel = gameState.getWaterLevel();

        System.out.println("\n=== The water level rise card is triggered ===");
        System.out.println("Water level from " + previousWaterLevel + " Rise to " + newWaterLevel);

        // 2. Reset the Flood pile
        cardManager.reshuffleFloodDiscard();

        // 3. Check if the game is failing due to high water level
        if (newWaterLevel >= gameState.getMaxWaterLevel()) {
            System.out.println("Warning: The water level reaches its maximum and the game is about to end！");
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "Waters Rise!";
    }
}