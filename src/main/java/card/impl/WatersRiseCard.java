package card.impl;

import card.Card;
import player.Player;

public class WatersRiseCard extends Card {
    public WatersRiseCard() {
        super(CardType.WATERS_RISE);
    }

    @Override
    public void func(Player player) {

    }

    @Override
    public String getName() {
        return "Waters Rise!";
    }
}