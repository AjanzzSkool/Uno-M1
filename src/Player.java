import java.util.*;
/**
 * Represents a player in the Uno game.
 * Each player has a name and a hand of cards.
 * 
 * @author Aws
 * @version 1.0
 */
public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    /**
     * Creates a new player with the given name.
     *
     * @param name the player's name
     */
    public Player(String name) { 
        this.name = name; 
    }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() { 
        return name; 
    }

    /**
     * Gets the player's hand of cards.
     *
     * @return the list of cards in the player's hand
     */
    public List<Card> hand() { 
        return hand; 
    }

    /**
     * Draws a number of cards from the deck and adds them to the player's hand.
     *
     * @param deck the deck to draw from
     * @param n the number of cards to draw
     */
    public void drawFrom(Deck deck, int n) {
        for (int i = 0; i < n; i++) {
            Card c = deck.draw(); 
            if (c != null) hand.add(c);
        }
    }

    /**
     * Checks if the player has won (no cards left).
     *
     * @return true if the player has no cards, false otherwise
     */
    public boolean hasWon() { 
        return hand.isEmpty(); 
    }
}
