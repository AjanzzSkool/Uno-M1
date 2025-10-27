import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class for the  card class.
 *
 * @author Ajan Balaganesh
 * @version 1.0
 */
public class CardTest {

    private Card redSeven;
    private Card blueSkip;
    private Card wild;
    private Card wildDrawTwo;


    /**
     * Method to set up 4 different cards for the various test cases.
     */
    @Before
    public void setUp() {
        redSeven = new Card(Color.RED, Rank.SEVEN);
        blueSkip = new Card(Color.BLUE, Rank.SKIP);
        wild = new Card(Color.GREEN, Rank.WILD);
        wildDrawTwo = new Card(Color.RED, Rank.WILD_DRAW_TWO);
    }


    /**
     * Method to make sure that the cards have the expected colors.
     */
    @Test
    public void testGetColor() {
        assertEquals("Expected color red for redSeven", Color.RED, redSeven.getColor());
        assertEquals("Expected color blue for blueSkip", Color.BLUE, blueSkip.getColor());
    }


    /**
     * Method to make sure that the cards have the expected ranks.
     */
    @Test
    public void testGetRank() {
        assertEquals("Expected rank 7", Rank.SEVEN, redSeven.getRank());
        assertEquals("Expected rank skip", Rank.SKIP, blueSkip.getRank());
    }


    /**
     * Method to make sure that the wild cards have the expected colors.
     */
    @Test
    public void testWildForcesWildColor() {
        assertEquals("Wild cards should have wild as color", Color.WILD, wild.getColor());
        assertEquals("WildDrawTwo cards should have wild as color", Color.WILD, wildDrawTwo.getColor());
    }

    /**
     * Method to make sure that the cards have the proper string expression.
     */
    @Test
    public void testToStringNormalCards() {
        String str = redSeven.toString();
        assertEquals("R:7", str);
    }

    /**
     * Method to make sure that the wild cards had the right string expression.
     */
    @Test
    public void testToStringWildCards() {
        assertEquals("W:Wild", wild.toString());
        assertEquals("W:WildDrawTwo", wildDrawTwo.toString());
    }


    /**
     * Test case for making sure that different cards have different string descriptions.
     */
    @Test
    public void testDifferentCardsNotEqualStrings() {
        assertNotEquals("Two different cards shouldn't really have same string description",
                redSeven.toString(), blueSkip.toString());
    }
}

