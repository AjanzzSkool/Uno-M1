import java.util.List;


public class RulesEngine {
    public boolean canPlay(Card candidate, Card top, Color currentColor) {
        if (candidate.getRank() == Rank.WILD || candidate.getRank() == Rank.WILD_DRAW_TWO) return true;
        if (top == null) return true;

        Color topColor = (currentColor != null && currentColor != Color.WILD) ? currentColor : top.getColor();
        return candidate.getColor() == topColor || candidate.getRank() == top.getRank();
    }


    public int scoreOf(Card c) {
        return switch (c.getRank()) {
            case ZERO -> 0; case ONE -> 1; case TWO -> 2; case THREE -> 3; case FOUR -> 4; case FIVE -> 5; case SIX -> 6; case SEVEN -> 7; case EIGHT -> 8; case NINE -> 9;
            case SKIP, REVERSE, DRAW_TWO -> 20;
            case WILD, WILD_DRAW_TWO -> 50;
        };
    }


    public int handScore(List<Card> hand) {
        return hand.stream().mapToInt(this::scoreOf).sum();
    }
}