import card.Card;
import card.CardManager;
import card.impl.FloodCard;
import player.Player;
import player.PlayerCardHandler;

import java.util.List;
import java.util.stream.Collectors;

public class CardTest {
    public static void main(String[] args) {
        // 初始化卡牌管理器
        CardManager cardManager =CardManager.getInstance();

        // 初始化玩家
        Player player1 = new Player();
        Player player2 = new Player();
        // 游戏开始，给玩家发牌
        System.out.println("游戏开始，给玩家发牌");
        player1.getPlayerCardHandler().addCards(cardManager.drawTreasureCards(2));
        player2.getPlayerCardHandler().addCards(cardManager.drawTreasureCards(2));

        // 显示初始牌堆状态
        System.out.println(cardManager.getDeckStatus());

        // 玩家1回合
        System.out.println("\n=== 玩家1回合 ===");
        // 1. 行动阶段 (模拟)
        System.out.println("玩家1执行行动...");

        // 2. 抽2张宝藏卡
        System.out.println("玩家1抽2张宝藏卡");
        List<Card> drawnCards = cardManager.drawTreasureCards(2);
        System.out.println("抽到: " + drawnCards.stream().map(Card::getName).collect(Collectors.toList()));
        player1.getPlayerCardHandler().addCards(drawnCards);

        // 3. 抽洪水卡 (根据水位等级)
        System.out.println("抽取洪水卡");
        List<Card> floodCards = cardManager.drawFloodCards(2);
        System.out.println("洪水卡: " + floodCards.stream().map(c -> ((FloodCard)c).getTileName()).collect(Collectors.toList()));

        // 显示当前状态
        System.out.println("\n当前状态:");
        System.out.println(cardManager.getDeckStatus());
        System.out.println("玩家1手牌: " + player1.getPlayerCardHandler().getCards().stream().map(Card::getName).collect(Collectors.toList()));
    }
}
