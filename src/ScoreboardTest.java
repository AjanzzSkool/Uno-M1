import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test class for Scoreboard functionality
 * @author Aws Ali
 * @version 1.0
 */
public class ScoreboardTest {
    private Scoreboard scoreboard;

    @Before
    public void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    public void testEnsurePlayer() {
        scoreboard.ensurePlayer("Player1");
        assertEquals(0, scoreboard.get("Player1"));
    }

    @Test
    public void testAddPoints() {
        scoreboard.ensurePlayer("Player1");
        scoreboard.add("Player1", 10);
        assertEquals(10, scoreboard.get("Player1"));
    }

    @Test
    public void testMultiplePointAdditions() {
        scoreboard.ensurePlayer("Player1");
        scoreboard.add("Player1", 10);
        scoreboard.add("Player1", 15);
        assertEquals(25, scoreboard.get("Player1"));
    }

    @Test
    public void testGetNonExistentPlayer() {
        assertEquals(0, scoreboard.get("NonExistent"));
    }

    @Test
    public void testAsMap() {
        scoreboard.ensurePlayer("Player1");
        scoreboard.ensurePlayer("Player2");
        scoreboard.add("Player1", 10);
        scoreboard.add("Player2", 20);
        
        var scores = scoreboard.asMap();
        assertEquals(2, scores.size());
        assertEquals(Integer.valueOf(10), scores.get("Player1"));
        assertEquals(Integer.valueOf(20), scores.get("Player2"));
    }
}