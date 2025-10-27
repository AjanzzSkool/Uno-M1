import java.util.*;

public final class Card {
    private final Color color;
    private final Rank rank;


    public Card(Color color, Rank rank) {
        if (rank == Rank.WILD || rank == Rank.WILD_DRAW_TWO) {
            this.color = Color.WILD;
        } else {
            this.color = color;
        }
        this.rank = rank;
    }


    public Color getColor() {
        return color;
    }

    public Rank getRank() {
        return rank;
    }


    @Override public String toString() {
        if (rank == Rank.WILD || rank == Rank.WILD_DRAW_TWO) return "W:" + pretty(rank);
        return color.toString().charAt(0) + ":" + pretty(rank);
    }


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
