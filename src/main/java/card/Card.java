package card;

import player.Player;
import game.GameState;

public abstract class Card {
    public enum CardType {
        TREASURE,
        SPECIAL_ACTION,
        WATERS_RISE,
        FLOOD
    }

    public enum TreasureType {
        CRYSTAL_OF_FIRE,
        STATUE_OF_THE_WIND,
        OCEANS_CHALICE,
        EARTH_STONE
    }

    public enum SpecialActionType {
        HELICOPTER_LIFT,
        SANDBAGS
    }

    private static int ID_COUNT = 0;
    private final int id;
    protected CardType type;
    private String imagePath;
    private String description;

    public Card(CardType type) {
        this.id = ID_COUNT++;
        this.type = type;
    }

    /**
     * Perform card functions
     * @param player Players who use cards
     * @param gameState The current state of the game
     * @return Whether the execution was successful
     */
    public abstract boolean func(Player player, GameState gameState);

    public abstract String getName();

    public int getId() { return id; }
    public CardType getType() { return type; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String path) { this.imagePath = path; }
    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }


    @Override
    public String toString() {
        return "Card{id=" + id + ", type=" + type + ", name=" + getName() + "}";
    }
}
