import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Test class for RulesEngine functionality
 * @author aws
 * @version 1.0
 */
public class RulesEngineTest {
    private RulesEngine rules;

    @Before
    public void setUp() {
        rules = new RulesEngine();
    }

    @Test
    public void testCanPlaySameColor() {
        Card top = new Card(Color.RED, Rank.FIVE);
        Card candidate = new Card(Color.RED, Rank.SEVEN);
        assertTrue(rules.canPlay(candidate, top, null));
    }

    @Test
    public void testCanPlaySameRank() {
        Card top = new Card(Color.RED, Rank.FIVE);
        Card candidate = new Card(Color.BLUE, Rank.FIVE);
        assertTrue(rules.canPlay(candidate, top, null));
    }

    @Test
    public void testCanPlayWildCard() {
        Card top = new Card(Color.RED, Rank.FIVE);
        Card wild = new Card(Color.WILD, Rank.WILD);
        assertTrue(rules.canPlay(wild, top, null));
    }

    @Test
    public void testCannotPlayDifferentColorAndRank() {
        Card top = new Card(Color.RED, Rank.FIVE);
        Card candidate = new Card(Color.BLUE, Rank.SEVEN);
        assertFalse(rules.canPlay(candidate, top, null));
    }

    @Test
    public void testScoreOfNumberCards() {
        Card card = new Card(Color.RED, Rank.FIVE);
        assertEquals(5, rules.scoreOf(card));
    }

    @Test
    public void testScoreOfActionCards() {
        Card skipCard = new Card(Color.RED, Rank.SKIP);
        assertEquals(20, rules.scoreOf(skipCard));
    }

    @Test
    public void testScoreOfWildCards() {
        Card wildCard = new Card(Color.WILD, Rank.WILD);
        assertEquals(50, rules.scoreOf(wildCard));
    }

    @Test
    public void testHandScore() {
        var hand = List.of(
            new Card(Color.RED, Rank.FIVE),
            new Card(Color.BLUE, Rank.SKIP),
            new Card(Color.WILD, Rank.WILD)
        );
        assertEquals(75, rules.handScore(hand)); // 5 + 20 + 50
    }
}