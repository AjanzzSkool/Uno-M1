import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Test class for ConsoleView functionality
 * JUnit 4 compatible
 */
public class ConsoleViewTest {
    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private ConsoleView newViewWithInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        return new ConsoleView(); // Scanner binds to the NEW System.in
    }

    private String out() {
        return outputStream.toString();
    }

    private void clearOut() {
        outputStream.reset();
    }

    @Test
    public void testAskPlayerCount() {
        ConsoleView view = newViewWithInput("3\n");
        assertEquals(3, view.askPlayerCount());
        assertTrue(out().contains("Enter number of players (2-4):"));
    }

    @Test
    public void testAskPlayerCountWithInvalidInput_clampsTo4() {
        ConsoleView view = newViewWithInput("5\n");
        assertEquals(4, view.askPlayerCount());
    }

    @Test
    public void testAskPlayerCountWithTooSmall_clampsTo2() {
        ConsoleView view = newViewWithInput("1\n");
        assertEquals(2, view.askPlayerCount());
    }

    @Test
    public void testAskPlayerName() {
        ConsoleView view = newViewWithInput("John\n");
        assertEquals("John", view.askPlayerName(0));
        assertTrue(out().contains("Player 1 name:"));
    }

    @Test
    public void testPromptTurn() {
        ConsoleView view = newViewWithInput("1\n");
        Player player = new Player("TestPlayer");
        assertEquals("1", view.promptTurn(player));
        assertTrue(out().contains("TestPlayer"));
    }

    @Test
    public void testPromptWildColor_Red() {
        ConsoleView view = newViewWithInput("R\n");
        assertEquals(Color.RED, view.promptWildColor());
        assertTrue(out().contains("Choose color [R,G,B,Y]:"));
    }

    @Test
    public void testPromptWildColor_DefaultsToYellow() {
        ConsoleView view = newViewWithInput("X\n");
        assertEquals(Color.YELLOW, view.promptWildColor());
    }

    @Test
    public void testShowTop_withTopAndActiveColor() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        Card topCard = new Card(Color.RED, Rank.FIVE);
        view.showTop(topCard, Color.RED);
        String output = out();
        assertTrue(output.contains("Top of discard:"));
        assertTrue(output.contains("RED"));
        assertTrue(output.contains("ActiveColor=RED"));
    }

    @Test
    public void testShowTop_noneWhenNullTop() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        view.showTop(null, null);
        String output = out();
        assertTrue(output.contains("Top of discard: (none)"));
    }

    @Test
    public void testShowNext() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        view.showNext("Player1", 5);
        String output = out();
        assertTrue(output.contains("Next: Player1"));
        assertTrue(output.contains("5 cards"));
    }

    @Test
    public void testAnnounce() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        view.announce("Game Over!");
        assertTrue(out().contains("== Game Over!"));
    }

    @Test
    public void testShowHand_printsHeaderAndIndices() {
        ConsoleView view = newViewWithInput("");
        clearOut();

        Player player = new Player("TestPlayer");
        // If Deck is random/large, you can add specific cards instead of drawing:
        player.drawFrom(new Deck(), 2);

        view.showHand(player);
        String output = out();
        assertTrue(output.contains("TestPlayer's hand:"));
        assertTrue(output.contains("[1]"));
        // If the player might have exactly 1 card, this next assert can be relaxed:
        assertTrue(output.contains("[2]"));
    }
}
