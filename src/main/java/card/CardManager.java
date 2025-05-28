package card;

import card.impl.FloodCard;
import card.impl.SpecialActionCard;
import card.impl.WatersRiseCard;
import card.impl.TreasureCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardManager {
    public static CardManager instance;
    private Stack<Card> treasureDeck;
    private Stack<Card> treasureDiscard;
    private Stack<Card> floodDeck;
    private Stack<Card> floodDiscard;

    private final List<Card> cardList = new ArrayList<>();

    private CardManager() {
        initializeDecks();
    }

    // 初始化所有牌堆
    private void initializeDecks() {
        // 初始化宝藏牌堆
        treasureDeck = new Stack<>();
        treasureDiscard = new Stack<>();

        // 添加宝藏卡 (每种5张)
        for (Card.TreasureType type : Card.TreasureType.values()) {
            for (int i = 0; i < 5; i++) {
                treasureDeck.push(new TreasureCard(type));
            }
        }

        // 添加特殊行动卡
        for (int i = 0; i < 3; i++) {
            treasureDeck.push(new SpecialActionCard(Card.SpecialActionType.HELICOPTER_LIFT));
        }
        for (int i = 0; i < 2; i++) {
            treasureDeck.push(new SpecialActionCard(Card.SpecialActionType.SANDBAGS));
        }

        // 添加水位上升卡
        for (int i = 0; i < 3; i++) {
            treasureDeck.push(new WatersRiseCard());
        }

        // 洗混宝藏牌堆
        Collections.shuffle(treasureDeck);

        // 初始化洪水牌堆
        floodDeck = new Stack<>();
        floodDiscard = new Stack<>();

        // 添加洪水卡 (假设有24个岛屿板块)
        String[] tileNames = {"Fools' Landing", "Breakers Bridge", "Cave of Embers", /* 其他板块名称 */};
        for (String tileName : tileNames) {
            floodDeck.push(new FloodCard(tileName));
        }

        // 洗混洪水牌堆
        Collections.shuffle(floodDeck);
    }

    // 从宝藏牌堆抽牌
    public List<Card> drawTreasureCards(int count) {
        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (treasureDeck.isEmpty()) {
                if (treasureDiscard.isEmpty()) {
                    break; // 无牌可抽
                }
                // 洗混弃牌堆作为新牌堆
                Collections.shuffle(treasureDiscard);
                treasureDeck.addAll(treasureDiscard);
                treasureDiscard.clear();
            }

            Card card = treasureDeck.pop();

            // 处理水位上升卡
            if (card.getType() == Card.CardType.WATERS_RISE) {
                handleWatersRise();
                // 不加入手牌，继续抽下一张
                i--;
                continue;
            }

            drawnCards.add(card);
        }

        return drawnCards;
    }

    // 处理水位上升卡
    private void handleWatersRise() {
        // 1. 水位上升逻辑 (需要与游戏状态交互)
        // gameState.increaseWaterLevel();

        // 2. 洗混洪水弃牌堆并放回牌堆顶部
        if (!floodDiscard.isEmpty()) {
            Collections.shuffle(floodDiscard);
            floodDeck.addAll(0, floodDiscard); // 加到牌堆顶部
            floodDiscard.clear();
        }
    }

    // 从洪水牌堆抽牌
    public List<Card> drawFloodCards(int count) {
        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (floodDeck.isEmpty()) {
                if (floodDiscard.isEmpty()) {
                    break; // 无牌可抽
                }
                // 洗混弃牌堆作为新牌堆
                Collections.shuffle(floodDiscard);
                floodDeck.addAll(floodDiscard);
                floodDiscard.clear();
            }

            Card card = floodDeck.pop();
            floodDiscard.push(card); // 洪水卡使用后进入弃牌堆
            drawnCards.add(card);
        }

        return drawnCards;
    }

    // 弃牌到宝藏弃牌堆
    public void discardToTreasurePile(Card card) {
        treasureDiscard.push(card);
    }

    // 获取当前牌堆状态
    public String getDeckStatus() {
        return String.format("宝藏牌堆: %d张, 宝藏弃牌堆: %d张, 洪水牌堆: %d张, 洪水弃牌堆: %d张",
                treasureDeck.size(), treasureDiscard.size(),
                floodDeck.size(), floodDiscard.size());
    }


    public static CardManager getInstance() {
        if (instance == null) instance = new CardManager();
        return instance;
    }

    public void reshuffleFloodDiscard() {
        if (!floodDiscard.isEmpty()) {
            System.out.println("重置洪水牌堆：将" + floodDiscard.size() + "张牌洗入牌堆");
            Collections.shuffle(floodDiscard);
            floodDeck.addAll(0, floodDiscard); // 添加到牌堆顶部
            floodDiscard.clear();
        } else {
            System.out.println("洪水弃牌堆为空，无需重置");
        }
    }
}