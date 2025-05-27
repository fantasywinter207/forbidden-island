package player;

public class Player {
    private String name;
    private final PlayerCardHandler playerCardHandler;


    public Player() {
        playerCardHandler = new PlayerCardHandler();
    }

    public PlayerCardHandler getPlayerCardHandler() {
        return playerCardHandler;
    }
}
