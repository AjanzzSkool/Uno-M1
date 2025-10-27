import java.util.*;

/**
 * Represents the Uno deck containing all cards.
 * Handles building, shuffling, drawing, and refilling the deck.
 * @author Ajan
 * @version 1.0
 */
public class Deck {
    private final ArrayDeque<Card> cards = new ArrayDeque<>();
    private final Random random = new Random();

    /**
     * Creates a new deck and fills it with standard Uno cards.
     */
    public Deck() {
        buildStandardUno();
    }

    /**
     * Builds a standard Uno deck with all colors, numbers, and special cards.
     * The deck is shuffled after being built.
     */
    public void buildStandardUno() {
        cards.clear();

        for (Color c : List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)) {
            cards.add(new Card(c, Rank.ZERO));
            for (int i = 0; i < 2; i++) {
                cards.add(new Card(c, Rank.ONE));
                cards.add(new Card(c, Rank.TWO));
                cards.add(new Card(c, Rank.THREE));
                cards.add(new Card(c, Rank.FOUR));
                cards.add(new Card(c, Rank.FIVE));
                cards.add(new Card(c, Rank.SIX));
                cards.add(new Card(c, Rank.SEVEN));
                cards.add(new Card(c, Rank.EIGHT));
                cards.add(new Card(c, Rank.NINE));
                cards.add(new Card(c, Rank.SKIP));
                cards.add(new Card(c, Rank.REVERSE));
                cards.add(new Card(c, Rank.DRAW_TWO));
            }
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Color.WILD, Rank.WILD));
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Color.WILD, Rank.WILD_DRAW_TWO));
        }

        List<Card> list = new ArrayList<>(cards);
        Collections.shuffle(list, random);
        cards.clear();
        list.forEach(cards::push);
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if there are no cards left, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Returns the number of cards left in the deck.
     *
     * @return the deck size
     */
    public int size() {
        return cards.size();
    }

    /**
     * Draws (removes and returns) the top card from the deck.
     *
     * @return the drawn card, or null if the deck is empty
     */
    public Card draw() {
        return cards.poll();
    }

    /**
     * Adds a list of cards to the bottom of the deck after shuffling them.
     *
     * @param cs the cards to add
     */
    public void addAllToBottom(List<Card> cs) {
        Collections.shuffle(cs, random);
        for (Card c : cs) cards.addLast(c);
    }
}
