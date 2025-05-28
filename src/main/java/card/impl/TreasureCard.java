package card.impl;

import card.Card;
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
        // 宝藏卡的功能是在玩家行动阶段兑换宝藏
        // 这里只是卡片本身，兑换逻辑在Player类中实现
        return false; // 宝藏卡不能直接使用
    }

    @Override
    public String getName() {
        return treasureType.toString();
    }

    public TreasureType getTreasureType() {
        return treasureType;
    }
}