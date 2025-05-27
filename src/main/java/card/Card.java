package card;


import player.Player;

public abstract class Card {
    // Card type enumeration
    public enum CardType {
        TREASURE,       // Treasure Cards
        SPECIAL_ACTION, // Special Ops Cards
        WATERS_RISE,    // Water level rise card
        FLOOD           // Flood card
    }

    // Treasure type enumeration
    public enum TreasureType {
        CRYSTAL_OF_FIRE,
        STATUE_OF_THE_WIND,
        OCEANS_CHALICE,
        EARTH_STONE
    }

    // Special Ops Card Type Enumeration
    public enum SpecialActionType {
        HELICOPTER_LIFT,
        SANDBAGS
    }

    private int id;
    protected CardType type;
    private String imagePath;
    private String description;


    public Card(CardType type) {
        this.type = type;
    }

    /**
     * The card function interface is used to realize the specific purpose of various cards
     * @param player
     */
    public abstract void func(Player player);

    public CardType getType() {
        return type;
    }

    public abstract String getName();
}