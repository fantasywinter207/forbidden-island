package player;

import card.Card;
import game.GameState;
import island.IslandTile;

import java.util.List;

public class Player {
    private String name;
    private final PlayerCardHandler playerCardHandler;

    private List<Card> hand;
    private String currentTile;
    private IslandTile islandTile;


    public Player() {
        playerCardHandler = new PlayerCardHandler();
    }

    public PlayerCardHandler getPlayerCardHandler() {
        return playerCardHandler;
    }

    public boolean useCard(int cardIndex, GameState gameState) {
        if (cardIndex < 0 || cardIndex >= hand.size()) {
            return false;
        }

        Card card = hand.get(cardIndex);
        boolean success = card.func(this, gameState);

        if (success && card.getType() != Card.CardType.TREASURE) {
            hand.remove(cardIndex); // 使用后移除卡牌(宝藏卡除外)
        }

        return success;
    }

    public String chooseTileToProtect() {
        // 实现玩家选择要保护的板块
        // 可以是UI交互或AI逻辑
        return "Breakers Bridge"; // 示例
    }

    public void setCurrentTile(IslandTile islandTile) {
        this.islandTile = islandTile;
    }

    public IslandTile getIslandTile() {
        return islandTile;
    }
}
