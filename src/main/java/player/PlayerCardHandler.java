package player;

import card.Card;
import card.CardManager;
import card.impl.TreasureCard;
import card.impl.SpecialActionCard;
import game.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerCardHandler {
    private final Player player;
    private final List<Card> cards;
    private static final int MAX_HAND_SIZE = 5;

    public PlayerCardHandler(Player player) {
        cards = new ArrayList<>();
        this.player = player;
    }

    public boolean useCard(int cardIndex, GameState gameState) {
        if (cardIndex < 0 || cardIndex >= cards.size()) {
            return false;
        }

        Card card = cards.get(cardIndex);
        boolean success = card.func(player, gameState);

        if (success) {
            if (card.getType() == Card.CardType.TREASURE) {
                // Special handling after the use of the treasure card
                if (!((TreasureCard) card).isKeepAfterUse()) {
                    cards.remove(cardIndex);
                }
            } else {
                cards.remove(cardIndex);
            }
        }

        return success;
    }

    // 添加多张卡牌\
    public boolean addCards(List<Card> newCards) {
        int currentSize = cards.size();
        int newSize = currentSize + newCards.size();

        if (newSize <= MAX_HAND_SIZE) {
            cards.addAll(newCards);
            return true;
        } else if (!newCards.isEmpty()) {
            int addableCount = MAX_HAND_SIZE - currentSize;
            if (addableCount > 0) {
                cards.addAll(newCards.subList(0, addableCount));
            }
            System.err.printf("Only %d cards can be added, beyond which %d can be added %n", addableCount, newSize - MAX_HAND_SIZE);
            return false;
        }
        return true;
    }

    // 添Add a single card, and trigger a callback when the limit is exceeded
    public void addCards(Card card, Runnable callBack) {
        cards.add(card);
        if (cards.size() > MAX_HAND_SIZE) {
            callBack.run();
        }
    }

    // Check if your hand is over the limit
    public boolean isOverLimit() {
        return cards.size() > MAX_HAND_SIZE;
    }

    // 弃牌
    public Card discardCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.remove(index);
        }
        return null;
    }

    // Drop cards of a specific type in batches
    public boolean discardCardsByType(Card.CardType type) {
        return cards.removeIf(card -> card.getType() == type);
    }

    // Use Special Ops Cards
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
        System.err.println("There are currently no Special Ops cards of this type");
        return null;
    }

    //Get the treasure cards in your hand
    public List<TreasureCard> getTreasureCards() {
        return cards.stream()
                .filter(card -> card.getType() == Card.CardType.TREASURE)
                .map(card -> (TreasureCard) card)
                .collect(Collectors.toList());
    }

    // Get a list of hands
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    // Get the number of cards in your hand
    public int getCardCount() {
        return cards.size();
    }

    // Determine if there is a specific type of card
    public boolean hasCardType(Card.CardType type) {
        return cards.stream().anyMatch(card -> card.getType() == type);
    }

    // Determine if there is a specific type of special ops card
    public boolean hasSpecialAction(Card.SpecialActionType actionType) {
        return cards.stream()
                .filter(card -> card.getType() == Card.CardType.SPECIAL_ACTION)
                .map(card -> (SpecialActionCard) card)
                .anyMatch(actionCard -> actionCard.getActionType() == actionType);
    }
}