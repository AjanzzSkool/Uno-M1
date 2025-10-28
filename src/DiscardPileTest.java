import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Test class for the DiscardPile class.
 *
 * @author Danilo
 * @version 1.0
 */
public class DiscardPileTest {

    private DiscardPile pile;
    private Card redFive;
    private Card blueTwo;

    @Before
    public void setUp() {
        pile = new DiscardPile();
        // Simple dummy Card objects for testing
        redFive = new Card(Color.RED, Rank.FIVE);
        blueTwo = new Card(Color.BLUE, Rank.TWO);
    }

    /**
     * Tests that a new DiscardPile is initially empty.
     */
    @Test
    public void testNewPileIsEmpty() {
        assertTrue(pile.isEmpty());
        assertNull(pile.top());
    }

    /**
     * Tests that pushing one card makes the pile non-empty and top returns it.
     */
    @Test
    public void testPushSingleCard() {
        pile.push(redFive);
        assertFalse(pile.isEmpty());
        assertEquals(redFive, pile.top());
    }

    /**
     * Tests that pushing multiple cards stacks them correctly (LIFO order).
     */
    @Test
    public void testPushMultipleCards() {
        pile.push(redFive);
        pile.push(blueTwo);
        assertEquals(blueTwo, pile.top());
    }

    /**
     * Tests takeAllButTop method when there are multiple cards.
     * returned list should contain all but the top card,
     * and the pile should retain only the top card.
     */
    @Test
    public void testTakeAllButTopMultipleCards() {
        pile.push(redFive);
        pile.push(blueTwo);

        List<Card> removed = pile.takeAllButTop();

        assertEquals(1, removed.size());
        assertTrue(removed.contains(redFive));
        assertEquals(blueTwo, pile.top());
    }

    /**
     * Tests takeAllButTop method when there is only one card.
     * Should return an empty list and retain the single card.
     */
    @Test
    public void testTakeAllButTopSingleCard() {
        pile.push(redFive);
        List<Card> removed = pile.takeAllButTop();

        assertTrue(removed.isEmpty());
        assertEquals(redFive, pile.top());
    }

    /**
     * Tests takeAllButTop on an empty pile.
     * Should return an empty list and remain empty.
     */
    @Test
    public void testTakeAllButTopEmptyPile() {
        List<Card> removed = pile.takeAllButTop();

        assertTrue(removed.isEmpty());
        assertTrue(pile.isEmpty());
        assertNull(pile.top());
    }
}
