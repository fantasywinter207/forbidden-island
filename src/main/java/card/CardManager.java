package card;

import card.impl.FloodCard;
import card.impl.SpecialActionCard;
import card.impl.WatersRiseCard;
import card.impl.TreasureCard;
import game.GameState;

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

    // Initialize all decks
    private void initializeDecks() {
        // Initialize the Treasure pile
        treasureDeck = new Stack<>();
        treasureDiscard = new Stack<>();

        // Add Treasure Cards (5 of each)
        for (Card.TreasureType type : Card.TreasureType.values()) {
            for (int i = 0; i < 5; i++) {
                treasureDeck.push(new TreasureCard(type));
            }
        }

        // Add Special Ops Cards
        for (int i = 0; i < 3; i++) {
            treasureDeck.push(new SpecialActionCard(Card.SpecialActionType.HELICOPTER_LIFT));
        }
        for (int i = 0; i < 2; i++) {
            treasureDeck.push(new SpecialActionCard(Card.SpecialActionType.SANDBAGS));
        }

        // Add a water level rise card
        for (int i = 0; i < 3; i++) {
            treasureDeck.push(new WatersRiseCard());
        }

        // Shuffle the Treasure pile
        Collections.shuffle(treasureDeck);

        // Initialize the Flood deck
        floodDeck = new Stack<>();
        floodDiscard = new Stack<>();

        // Add Flood Card (assuming there are 24 island tiles)
        String[] tileNames = {"Fools' Landing", "Breakers Bridge", "Cave of Embers", /* Other plate names */};
        for (String tileName : tileNames) {
            floodDeck.push(new FloodCard(tileName));
        }

        // Shuffle the flood pile
        Collections.shuffle(floodDeck);
    }

    // Draw cards from the treasure pile
    public List<Card> drawTreasureCards(int count) {
        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (treasureDeck.isEmpty()) {
                if (treasureDiscard.isEmpty()) {
                    break; // No cards to draw
                }
                // Shuffle the discard piles as new piles
                Collections.shuffle(treasureDiscard);
                treasureDeck.addAll(treasureDiscard);
                treasureDiscard.clear();
            }

            Card card = treasureDeck.pop();

            // Handle the water level rise card
            if (card.getType() == Card.CardType.WATERS_RISE) {
                handleWatersRise();
                // Don't add a card to your hand and move on to the next one
                i--;
                continue;
            }

            drawnCards.add(card);
        }

        return drawnCards;
    }

    // Handle the water level rise card
    private void handleWatersRise() {
        // 1. Water level rise logic
        GameState.getGameState().increaseWaterLevel();

        // 2. Shuffle the flood discard pile and put it back on top
        if (!floodDiscard.isEmpty()) {
            Collections.shuffle(floodDiscard);
            floodDeck.addAll(0, floodDiscard); // Add to the top of the pile
            floodDiscard.clear();
        }
    }

    // Draw cards from the flood pile
    public List<Card> drawFloodCards() {
        int count = Math.min(3, GameState.getGameState().getWaterLevel() + 1); // 水位0抽1张，水位1抽2张，依此类推
        List<Card> drawnCards = new ArrayList<>();

        System.out.println("\n=== ABSTRACTING " + count + " Flood cards ===");

        for (int i = 0; i < count; i++) {
            if (floodDeck.isEmpty()) {
                if (floodDiscard.isEmpty()) {
                    System.out.println("Both the flood and discard piles are empty and cannot be drawn");
                    break;
                }
                reshuffleFloodDiscard(); // 洗牌弃牌堆
            }

            Card card = floodDeck.pop();
            floodDiscard.push(card); // 洪水卡使用后进入弃牌堆
            drawnCards.add(card);

            System.out.println("drawn: " + card.getName());
        }

        return drawnCards;
    }

    // Fold to the treasure discard pile
    public void discardToTreasurePile(Card card) {
        treasureDiscard.push(card);
    }

    // 获取当前牌堆状态
    public String getDeckStatus() {
        return String.format("Treasure pile: %d, Treasure discard pile: %d, Flood pile: %d, Flood discard: %d",
                treasureDeck.size(), treasureDiscard.size(),
                floodDeck.size(), floodDiscard.size());
    }


    public static CardManager getInstance() {
        if (instance == null) instance = new CardManager();
        return instance;
    }

    public void reshuffleFloodDiscard() {
        if (!floodDiscard.isEmpty()) {
            System.out.println("Reset Flood deck: Will" + floodDiscard.size() + "Cards are shuffled into the pile");
            Collections.shuffle(floodDiscard);
            floodDeck.addAll(0, floodDiscard); // 添加到牌堆顶部
            floodDiscard.clear();
        } else {
            System.out.println("The flood discard pile is empty and does not need to be reset");
        }
    }
}