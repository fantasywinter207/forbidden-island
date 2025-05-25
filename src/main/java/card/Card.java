package card;

public abstract class Card {
    // Card type enumeration
    public enum CardType {
        TREASURE,       // Treasure Cards
        SPECIAL_ACTION, // Special Ops Cards
        WATERS_RISE,    // Water level rise card
        FLOOD           // Flood card
    }

    protected CardType type;

    public Card(CardType type) {
        this.type = type;
    }

    public CardType getType() {
        return type;
    }

    public abstract String getName();
}