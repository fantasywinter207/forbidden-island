package card.impl;

import card.Card;
import player.Player;

public class FloodCard extends Card {
    private String tileName;

    public FloodCard(String tileName) {
        super(CardType.FLOOD);
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

    @Override
    public void func(Player player) {

    }

    @Override
    public String getName() {
        return "Flood: " + tileName;
    }
}
