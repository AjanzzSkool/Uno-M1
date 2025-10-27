import java.util.*;

/**
 * Represents a single Uno card with a color and rank.
 * @author Ajan Balaganesh
 * @version 1.0
 */
public final class Card {
    private final Color color;
    private final Rank rank;

    /**
     * Creates a new card with the given color and rank.
     * If the rank is a Wild card, the color is set to WILD automatically.
     *
     * @param color the color of the card
     * @param rank  the rank of the card
     */
    public Card(Color color, Rank rank) {
        if (rank == Rank.WILD || rank == Rank.WILD_DRAW_TWO) {
            this.color = Color.WILD;
        } else {
            this.color = color;
        }
        this.rank = rank;
    }

    /**
     * Gets the color of the card.
     *
     * @return the card color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the rank of the card.
     *
     * @return the card rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns a short text version of the card.
     * Example: "R:5" or "W:Wild".
     *
     * @return the card as text
     */
    @Override
    public String toString() {
        if (rank == Rank.WILD || rank == Rank.WILD_DRAW_TWO) return "W:" + pretty(rank);
        return color.toString().charAt(0) + ":" + pretty(rank);
    }

    /**
     * Formats the rank into a readable string.
     *
     * @param r the rank to format
     * @return the rank as text
     */
    private String pretty(Rank r) {
        return switch (r) {
            case ZERO -> "0";
            case ONE -> "1";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case SKIP -> "Skip";
            case REVERSE -> "Reverse";
            case DRAW_TWO -> "DrawTwo";
            case WILD -> "Wild";
            case WILD_DRAW_TWO -> "WildDrawTwo";
        };
    }
}
