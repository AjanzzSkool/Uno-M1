/**
 * Represents the ranks of Uno cards.
 * @author Ajan
 * @version 1.0
 */
public enum Rank {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_TWO;

    public boolean isNumber() {
        return ordinal() <= NINE.ordinal();
    }
}
