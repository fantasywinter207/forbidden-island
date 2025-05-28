package player;

import card.Card;
import card.impl.TreasureCard;
import card.impl.SpecialActionCard;
import java.util.ArrayList;
import java.util.List;

public class PlayerCardHandler {
    private final List<Card> cards;
    private static final int MAX_HAND_SIZE = 5;

    public PlayerCardHandler() {
        cards = new ArrayList<>();
    }

    // 添加卡牌到手牌
    public boolean addCards(List<Card> newCards) {
        int size = newCards.size() + cards.size();
        if (size < MAX_HAND_SIZE) {
            cards.addAll(newCards);
            return true;
        }
        System.err.println("Exceeds the maximum number of cards you can hold:" + size + "/" + MAX_HAND_SIZE);
        return false;
    }

    /**
     * Add a single card, if the maximum card holding is exceeded, a callback will be triggered
     * @param card cards
     * @param callBack Callback method
     */
    public void addCards(Card card, Runnable callBack) {
        cards.add(card);
        if (checkHandLimit()) {
            callBack.run();
        }
    }

    // 检查手牌限制
    private boolean checkHandLimit() {
        return cards.size() < MAX_HAND_SIZE;
    }

    // 弃牌
    public Card discardCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.remove(index);
        }
        return null;
    }

    // 使用特殊行动卡
    public SpecialActionCard useSpecialActionCard(Card.SpecialActionType type) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getType() == Card.CardType.SPECIAL_ACTION) {
                SpecialActionCard actionCard = (SpecialActionCard) card;
                if (actionCard.getActionType() == type) {
                    cards.remove(i);
                    return actionCard;
                }
            }
        }
        System.err.println("There are currently no Special Ops cards");
        return null;
    }

    // 获取手牌中的宝藏卡
    public List<TreasureCard> getTreasureCards(Card.TreasureType type) {
        List<TreasureCard> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getType() == Card.CardType.TREASURE) {
                TreasureCard treasureCard = (TreasureCard) card;
                if (treasureCard.getTreasureType() == type) {
                    result.add(treasureCard);
                }
            }
        }
        return result;
    }

    // 获取手牌列表
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

}
