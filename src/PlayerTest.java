import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test class for Player functionality
 * @author aws
 * @version 1.0
 */
public class PlayerTest {
    private Player player;
    private Deck deck;

    @Before
    public void setUp() {
        player = new Player("TestPlayer");
        deck = new Deck();
    }

    @Test
    public void testPlayerCreation() {
        assertEquals("TestPlayer", player.getName());
        assertTrue(player.hand().isEmpty());
    }

    @Test
    public void testDrawFromDeck() {
        player.drawFrom(deck, 7);
        assertEquals(7, player.hand().size());
    }

    @Test
    public void testHasWon() {
        assertTrue(player.hasWon()); // Initially no cards
        player.drawFrom(deck, 1);
        assertFalse(player.hasWon()); // Has cards now
    }

    @Test
    public void testDrawFromEmptyDeck() {
        Deck emptyDeck = new Deck();
        // Draw all cards from deck first
        while (emptyDeck.draw() != null);
        
        player.drawFrom(emptyDeck, 5);
        assertEquals(0, player.hand().size());
    }

    @Test
    public void testHandAccess() {
        player.drawFrom(deck, 3);
        assertNotNull(player.hand());
        assertEquals(3, player.hand().size());
    }
}