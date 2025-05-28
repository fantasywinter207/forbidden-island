package card.impl;

import card.Card;
import island.IslandTile;
import player.Player;
import game.GameState;

public class FloodCard extends Card {
    private final String tileName;

    public FloodCard(String tileName) {
        super(CardType.FLOOD);
        this.tileName = tileName;
        setDescription("Flood or sink the " + tileName + " tile");
    }

    @Override
    public boolean func(Player player, GameState gameState) {
        // 获取对应岛屿板块
        IslandTile tile = gameState.getIslandTile(tileName);
        if (tile == null) {
            System.err.println("错误：未找到岛屿板块 " + tileName);
            return false;
        }

        // 执行洪水效果
        if (!tile.isFlooded()) {
            // 未淹没 → 淹没
            tile.flood();
            System.out.println(tileName + " 已被淹没！");
        } else {
            // 已淹没 → 沉没
            tile.sink();
            System.out.println(tileName + " 已沉没！");
            // 检查是否有玩家在沉没的板块上
            gameState.handleSunkTile(tileName);
        }

        return true;
    }

    @Override
    public String getName() {
        return "Flood: " + tileName;
    }

    public String getTileName() {
        return tileName;
    }
}