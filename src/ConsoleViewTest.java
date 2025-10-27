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

    /**
     * Method to set up the testing environment before each test.
     * Redirects System.out to a custom ByteArrayOutputStream to capture all console output.
     */
    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Method to clean up the testing environment after each test.
     * Restores the original System.out and System.in streams.
     */
    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Method to create a new ConsoleView instance with mocked System.in input.
     *
     * @param data The string data to be fed as input to the view (e.g., "3\nJohn\n").
     * @return A new ConsoleView object.
     */
    private ConsoleView newViewWithInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        return new ConsoleView(); // Scanner binds to the NEW System.in
    }

    /**
     * Method to retrieve the entire console output captured during the test.
     *
     * @return The string content of System.out.
     */
    private String out() {
        return outputStream.toString();
    }

    /**
     * Method to reset/clear the captured console output stream.
     */
    private void clearOut() {
        outputStream.reset();
    }

    /**
     * Method to test the askPlayerCount method with a valid input (3).
     * Verifies that the correct value is returned and the prompt is displayed.
     */
    @Test
    public void testAskPlayerCount() {
        ConsoleView view = newViewWithInput("3\n");
        assertEquals(3, view.askPlayerCount());
        assertTrue(out().contains("Enter number of players (2-4):"));
    }

    /**
     *  Method to test the askPlayerCount method with input greater than 4 (5).
     * Verifies that the result is clamped to the maximum allowed value of 4.
     */
    @Test
    public void testAskPlayerCountWithInvalidInput_clampsTo4() {
        ConsoleView view = newViewWithInput("5\n");
        assertEquals(4, view.askPlayerCount());
    }

    /**
     * Method to test the askPlayerCount method with input less than 2 (1).
     * Verifies that the result is clamped to the minimum allowed value of 2.
     */
    @Test
    public void testAskPlayerCountWithTooSmall_clampsTo2() {
        ConsoleView view = newViewWithInput("1\n");
        assertEquals(2, view.askPlayerCount());
    }

    /**
     * Method to test the askPlayerName method.
     * Verifies that the input name is returned and the prompt is correctly indexed.
     */
    @Test
    public void testAskPlayerName() {
        ConsoleView view = newViewWithInput("John\n");
        assertEquals("John", view.askPlayerName(0));
        assertTrue(out().contains("Player 1 name:"));
    }

    /**
     * Method to test the promptTurn method.
     * Verifies that the player's name is included in the prompt and the input is returned.
     */
    @Test
    public void testPromptTurn() {
        ConsoleView view = newViewWithInput("1\n");
        Player player = new Player("TestPlayer");
        assertEquals("1", view.promptTurn(player));
        assertTrue(out().contains("TestPlayer"));
    }

    /**
     * Method to test the promptWildColor method with valid input 'R'.
     * Verifies that the correct Color.RED enum is returned and the prompt is shown.
     */
    @Test
    public void testPromptWildColor_Red() {
        ConsoleView view = newViewWithInput("R\n");
        assertEquals(Color.RED, view.promptWildColor());
        assertTrue(out().contains("Choose color [R,G,B,Y]:"));
    }

    /**
     * Method to test the promptWildColor method with invalid input ('X').
     * Verifies that the method defaults to Color.YELLOW.
     */
    @Test
    public void testPromptWildColor_DefaultsToYellow() {
        ConsoleView view = newViewWithInput("X\n");
        assertEquals(Color.YELLOW, view.promptWildColor());
    }

    /**
     * Method to test the showTop method when a card is present on the discard pile
     * and an active color is set.
     * Verifies the output contains the card and the active color.
     */
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

    /**
     * Method to test the showTop method when the discard pile is empty null top card.
     * Verifies the output indicates "Top of discard: (none)".
     */
    @Test
    public void testShowTop_noneWhenNullTop() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        view.showTop(null, null);
        String output = out();
        assertTrue(output.contains("Top of discard: (none)"));
    }

    /**
     * Method to test the showNext method.
     * Verifies that the next player's name and their card count are displayed.
     */
    @Test
    public void testShowNext() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        view.showNext("Player1", 5);
        String output = out();
        assertTrue(output.contains("Next: Player1"));
        assertTrue(output.contains("5 cards"));
    }

    /**
     * Method to test the announce method.
     * Verifies that the message is displayed with the expected formatting.
     */
    @Test
    public void testAnnounce() {
        ConsoleView view = newViewWithInput("");
        clearOut();
        view.announce("Game Over!");
        assertTrue(out().contains("== Game Over!"));
    }

    /**
     * Method to test the showHand method.
     * Verifies that the player's name is used in the header and that card indices are printed.
     * Note: This test relies on Deck and Player functionality to mock a hand.
     */
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
