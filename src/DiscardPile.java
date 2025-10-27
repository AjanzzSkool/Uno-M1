import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class DiscardPile {
    private final ArrayDeque<Card> pile = new ArrayDeque<>();


    public void push(Card c) { pile.push(c); }
    public Card top() { return pile.peek(); }
    public boolean isEmpty() { return pile.isEmpty(); }


    public List<Card> takeAllButTop() {
    
        Card keep = pile.poll();
        List<Card> rest = new ArrayList<>(pile);
        pile.clear();
        if (keep != null) pile.push(keep);
        return rest;
    }
}
