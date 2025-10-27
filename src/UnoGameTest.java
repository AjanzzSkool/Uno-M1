import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Test class for UnoGame
 * @author Danilo
 * @version 1.0
 */
public class UnoGameTest {

    private UnoGame game;

    @Before
    public void setUp() {
        game = new UnoGame();
    }

    /** Checks that parseIndex returns a number for valid input */
    @Test
    public void testParseIndexValid() throws Exception {
        var method = UnoGame.class.getDeclaredMethod("parseIndex", String.class);
        method.setAccessible(true);

        assertEquals(5, method.invoke(game, "5"));
        assertEquals(0, method.invoke(game, " 0 "));
    }

    /** Checks that parseIndex returns null for invalid input */
    @Test
    public void testParseIndexInvalid() throws Exception {
        var method = UnoGame.class.getDeclaredMethod("parseIndex", String.class);
        method.setAccessible(true);

        assertNull(method.invoke(game, "abc"));
        assertNull(method.invoke(game, ""));
        assertNull(method.invoke(game, "7x"));
    }

    /** Makes sure normalized wraps player indexes correctly */
    @Test
    public void testNormalizedWraps() throws Exception {
        var field = UnoGame.class.getDeclaredField("players");
        field.setAccessible(true);
        List<Player> list = new ArrayList<>();
        list.add(new Player("A"));
        list.add(new Player("B"));
        list.add(new Player("C"));
        field.set(game, list);

        var method = UnoGame.class.getDeclaredMethod("normalized", int.class);
        method.setAccessible(true);

        assertEquals(0, method.invoke(game, 0));
        assertEquals(1, method.invoke(game, 1));
        assertEquals(0, method.invoke(game, 3));   
        assertEquals(2, method.invoke(game, -1));  
    }

    /** Checks that isNumber detects number vs action cards */
    @Test
    public void testIsNumber() throws Exception {
        var method = UnoGame.class.getDeclaredMethod("isNumber", Card.class);
        method.setAccessible(true);

        Card num = new Card(Color.RED, Rank.FOUR);
        Card action = new Card(Color.GREEN, Rank.SKIP);

        assertTrue((boolean) method.invoke(game, num));
        assertFalse((boolean) method.invoke(game, action));
    }

    /** Verifies ensureDeckHas runs safely when deck has enough cards */
    @Test
    public void testEnsureDeckHas() throws Exception {
        var deckField = UnoGame.class.getDeclaredField("deck");
        deckField.setAccessible(true);

        Deck deck = new Deck();
        deckField.set(game, deck);

        var method = UnoGame.class.getDeclaredMethod("ensureDeckHas", int.class);
        method.setAccessible(true);

        method.invoke(game, 1); // should not throw errors
    }
}
