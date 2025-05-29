import card.Card;
import card.CardManager;
import card.impl.FloodCard;
import player.Player;
import player.PlayerCardHandler;

import java.util.List;
import java.util.stream.Collectors;

public class CardTest {
    public static void main(String[] args) {
        // Initialize the Card Manager
        CardManager cardManager = CardManager.getInstance();

        // Initialize the players
        Player player1 = new Player();
        Player player2 = new Player();

        // Game starts, deal cards to players
        System.out.println("The game begins, and cards are dealt to the players");
        player1.getPlayerCardHandler().addCards(cardManager.drawTreasureCards(2));
        player2.getPlayerCardHandler().addCards(cardManager.drawTreasureCards(2));

        // Display initial deck status
        System.out.println(cardManager.getDeckStatus());

        // Player 1's turn
        System.out.println("\n=== Player 1's turn ===");
        // 1. Action phase (simulated)
        System.out.println("Player 1 performs actions...");

        // 2. Draw 2 treasure cards
        System.out.println("Player 1 draws 2 treasure cards");
        List<Card> drawnCards = cardManager.drawTreasureCards(2);
        System.out.println("Drawn: " + drawnCards.stream().map(Card::getName).collect(Collectors.toList()));
        player1.getPlayerCardHandler().addCards(drawnCards);

        // 3. Draw flood cards (based on water level)
        System.out.println("Drawing flood cards");
        List<Card> floodCards = cardManager.drawFloodCards();
        System.out.println("Flood cards: " + floodCards.stream()
                .map(c -> ((FloodCard) c).getTileName()).collect(Collectors.toList()));

        // Display current status
        System.out.println("\nCurrent status:");
        System.out.println(cardManager.getDeckStatus());
        System.out.println("Player 1's hand: " + player1.getPlayerCardHandler().getCards().stream()
                .map(Card::getName).collect(Collectors.toList()));
    }
}