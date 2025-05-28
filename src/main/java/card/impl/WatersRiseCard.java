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
        // 水位上升卡在抽到时自动执行
        gameState.increaseWaterLevel();
        CardManager.getInstance().reshuffleFloodDiscard();
        return true;
    }

    @Override
    public String getName() {
        return "Waters Rise!";
    }
}