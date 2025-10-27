import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Test class for Deck.
 *
 * @author Ajan Balaganesh
 * @version 1.0
 */

public class DeckTest {

    private Deck deck;

    /**
     * Create new Dec object for setup
     */
    @Before
    public void setUp() {

        deck = new Deck();
    }


    /**
     * Test method that makes sure that an empty deck isn't built
     */
    @Test
    public void testDeckNotEmptyAfterBuild() {
        assertFalse(deck.isEmpty());
        assertTrue(deck.size() > 0);
    }

    /**
     * Test method that makes sure that drawing a card reduces deck size
     */
    @Test
    public void testDrawRemovesCard() {
        int before = deck.size();
        Card drawn = deck.draw();
        assertNotNull("Draw should actually return a card", drawn);
        assertEquals("Deck size should go down by 1 after a draw", before - 1, deck.size());
    }

    /**
     * Method to test that drawing mechanism could actually empty out the deck
     */
    @Test
    public void testDrawUntilEmpty() {
        int count = 0;
        while (!deck.isEmpty()) {
            Card c = deck.draw();
            assertNotNull("Drawn card should not be null before empty", c);
            count++;
        }
        assertTrue("After drawing all cards deck must be empty", deck.isEmpty());
    }
    /**
     * Test method that makes sure adding mechanism works for the deck
     */
    @Test
    public void testAddToIncreasesSize() {
        int before = deck.size();
        List<Card> extra = new ArrayList<>();
        extra.add(new Card(Color.RED, Rank.ONE));
        extra.add(new Card(Color.BLUE, Rank.SKIP));
        deck.addAllToBottom(extra);
        assertEquals("Adding cards should increase size by 2", before + 2, deck.size());
    }
    /**
     * Test method that makes building a new deck reset size
     */
    @Test
    public void testBuildStandardUnoResetsDeck() {
        deck.draw();
        int reduced = deck.size();
        deck.buildStandardUno();
        int reset = deck.size();
        assertTrue("Rebuilding a deck should reset deck to full size", reset > reduced);
    }
}


