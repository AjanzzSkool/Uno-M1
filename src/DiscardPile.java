import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the discard pile in an Uno game.
 * Stores cards that players have played.
 */
public class DiscardPile {
    private final ArrayDeque<Card> pile = new ArrayDeque<>();

    /**
     * Adds a card to the top of the discard pile.
     *
     * @param c the card to add
     */
    public void push(Card c) { 
        pile.push(c); 
    }

    /**
     * Returns the top card of the discard pile without removing it.
     *
     * @return the top card, or null if the pile is empty
     */
    public Card top() { 
        return pile.peek(); 
    }

    /**
     * Checks if the discard pile is empty.
     *
     * @return true if there are no cards, false otherwise
     */
    public boolean isEmpty() { 
        return pile.isEmpty(); 
    }

    /**
     * Removes and returns all cards except the top one.
     * Used when reshuffling the pile back into the deck.
     *
     * @return a list of all cards except the top one
     */
    public List<Card> takeAllButTop() {
        Card keep = pile.poll();
        List<Card> rest = new ArrayList<>(pile);
        pile.clear();
        if (keep != null) pile.push(keep);
        return rest;
    }
}
