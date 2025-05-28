package card.impl;

import card.Card;
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
                // 沙袋卡需要玩家选择要保护的板块
                String tileToProtect = player.chooseTileToProtect();
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