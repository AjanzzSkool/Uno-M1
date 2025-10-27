import java.util.List;

/**
 * Handles the rules and scoring logic for the Uno game.
 */
public class RulesEngine {

    /**
     * Checks if a card can be played on top of the current card.
     *
     * @param candidate the card the player wants to play
     * @param top the top card on the discard pile
     * @param currentColor the current active color (after wild cards)
     * @return true if the card can be played, false otherwise
     */
    public boolean canPlay(Card candidate, Card top, Color currentColor) {
        if (candidate.getRank() == Rank.WILD || candidate.getRank() == Rank.WILD_DRAW_TWO) return true;
        if (top == null) return true;

        Color topColor = (currentColor != null && currentColor != Color.WILD) ? currentColor : top.getColor();
        return candidate.getColor() == topColor || candidate.getRank() == top.getRank();
    }

    /**
     * Gets the point value of a single card.
     *
     * @param c the card
     * @return the score value of the card
     */
    public int scoreOf(Card c) {
        return switch (c.getRank()) {
            case ZERO -> 0; case ONE -> 1; case TWO -> 2; case THREE -> 3; case FOUR -> 4; 
            case FIVE -> 5; case SIX -> 6; case SEVEN -> 7; case EIGHT -> 8; case NINE -> 9;
            case SKIP, REVERSE, DRAW_TWO -> 20;
            case WILD, WILD_DRAW_TWO -> 50;
        };
    }

    /**
     * Calculates the total score of all cards in a hand.
     *
     * @param hand the list of cards
     * @return the total hand score
     */
    public int handScore(List<Card> hand) {
        return hand.stream().mapToInt(this::scoreOf).sum();
    }
}
