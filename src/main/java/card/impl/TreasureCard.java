package card.impl;

import card.Card;
import player.Player;

// 宝藏卡
public class TreasureCard extends Card {
    private TreasureType treasureType;

    public TreasureCard(TreasureType treasureType) {
        super(CardType.TREASURE);
        this.treasureType = treasureType;
    }

    public TreasureType getTreasureType() {
        return treasureType;
    }

    @Override
    public void func(Player player) {

    }

    @Override
    public String getName() {
        return treasureType.toString();
    }
}