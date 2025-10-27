import java.util.*;


public class Deck {
    private final ArrayDeque<Card> cards = new ArrayDeque<>();
    private final Random random = new Random();


    public Deck() {
        buildStandardUno();
    }


    public void buildStandardUno() {
        cards.clear();

        for (Color c : List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)) {
            cards.add(new Card(c, Rank.ZERO));
            for (int i=0;i<2;i++) {
                cards.add(new Card(c, Rank.ONE));
                cards.add(new Card(c, Rank.TWO));
                cards.add(new Card(c, Rank.THREE));
                cards.add(new Card(c, Rank.FOUR));
                cards.add(new Card(c, Rank.FIVE));
                cards.add(new Card(c, Rank.SIX));
                cards.add(new Card(c, Rank.SEVEN));
                cards.add(new Card(c, Rank.EIGHT));
                cards.add(new Card(c, Rank.NINE));
                cards.add(new Card(c, Rank.SKIP));
                cards.add(new Card(c, Rank.REVERSE));
                cards.add(new Card(c, Rank.DRAW_TWO));
            }
        }

        for (int i=0;i<4;i++) {
            cards.add(new Card(Color.WILD, Rank.WILD));
        }

        for (int i=0;i<4;i++) {
            cards.add(new Card(Color.WILD, Rank.WILD_DRAW_TWO));
        }

        List<Card> list = new ArrayList<>(cards);
        Collections.shuffle(list, random);
        cards.clear();
        list.forEach(cards::push);
    }


    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }


    public Card draw() {
        return cards.poll();
    }


    public void addAllToBottom(List<Card> cs) {
        Collections.shuffle(cs, random);
        for (Card c : cs) cards.addLast(c);
    }
}

