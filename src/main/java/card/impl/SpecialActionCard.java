package card.impl;

import card.Card;
import player.Player;

public class SpecialActionCard extends Card {
    private SpecialActionType actionType;

    public SpecialActionCard(SpecialActionType actionType) {
        super(CardType.SPECIAL_ACTION);
        this.actionType = actionType;
    }

    public SpecialActionType getActionType() {
        return actionType;
    }

    @Override
    public void func(Player player) {

    }

    @Override
    public String getName() {
        return actionType.toString();
    }
}
