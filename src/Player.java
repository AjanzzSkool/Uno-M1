import java.util.*;


public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();


    public Player(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Card> hand() { return hand; }


    public void drawFrom(Deck deck, int n) {
        for (int i=0;i<n;i++) {
            Card c = deck.draw(); if (c!=null) hand.add(c);
        }
    }


    public boolean hasWon() { return hand.isEmpty(); }
}