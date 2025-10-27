import java.util.*;

/**
 * Controls the flow of an Uno game.
 * Handles setup, turns, and scoring.
 * @version 1.0 
 * @author Danilo
 */
public class UnoGame {
    private final ConsoleView view = new ConsoleView();
    private final RulesEngine rules = new RulesEngine();
    private final Scoreboard scores = new Scoreboard();

    private final List<Player> players = new ArrayList<>();
    private final Deck deck = new Deck();
    private final DiscardPile discard = new DiscardPile();

    private int current = 0;
    private int direction = +1;
    private Color activeColor = null;

    /**
     * Runs the Uno game from start to finish.
     */
    public void run() {
        setup();
        startRound();
        loop();
    }

    /**
     * Sets up the game by adding players, dealing cards,
     * and starting the discard pile with a number card.
     */
    private void setup() {
        int n = view.askPlayerCount();
        for (int i = 0; i < n; i++) {
            String name = view.askPlayerName(i);
            players.add(new Player(name));
            scores.ensurePlayer(name);
        }

        for (Player p : players) {
            p.drawFrom(deck, 7);
        }

        Card top;
        while (true) {
            top = deck.draw();
            if (top == null) throw new IllegalStateException("Deck empty during setup");
            if (isNumber(top)) break;
            deck.addAllToBottom(List.of(top));
        }

        discard.push(top);
        activeColor = top.getColor();
    }

    /**
     * Resets turn order and direction at the start of a round.
     */
    private void startRound() {
        current = 0;
        direction = +1;
    }

    /**
     * Main game loop that runs until a player wins.
     */
    private void loop() {
        while (true) {
            Player p = players.get(current);

            view.showTop(discard.top(), activeColor);
            view.showHand(p);

            if (p.hasWon()) {
                endRound(p);
                break;
            }

            view.announce("Enter card number (1–" + p.hand().size() + ") or 0 to draw:");
            String input = view.promptTurn(p);

            if (input.equalsIgnoreCase("HELP")) {
                view.announce("Enter the CARD NUMBER from your hand (starting at 1). Type 0 to draw a card.");
                continue;
            }
            if (input.equalsIgnoreCase("HAND")) {
                view.showHand(p);
                continue;
            }

            Integer idx = parseIndex(input);
            if (idx == null) {
                view.announce("Invalid input. Enter 0 or a valid card number.");
                continue;
            }

            if (idx == 0) {
                drawOne(p);
                advanceTurn();
                continue;
            }

            int listIndex = idx - 1;
            if (listIndex < 0 || listIndex >= p.hand().size()) {
                view.announce("Invalid number. Choose between 1 and " + p.hand().size() + ".");
                continue;
            }

            Card chosen = p.hand().get(listIndex);
            if (!rules.canPlay(chosen, discard.top(), activeColor)) {
                view.announce("You cannot play " + chosen + " on " + discard.top() + " (ActiveColor=" + activeColor + ")");
                continue;
            }

            p.hand().remove(listIndex);
            discard.push(chosen);

            if (chosen.getRank() == Rank.WILD || chosen.getRank() == Rank.WILD_DRAW_TWO) {
                activeColor = view.promptWildColor();
            } else {
                activeColor = chosen.getColor();
            }

            int drawNext = 0;
            boolean skipNext = false;

            switch (chosen.getRank()) {
                case REVERSE -> {
                    direction *= -1;
                    view.announce("Direction reversed!");
                }
                case SKIP -> {
                    skipNext = true;
                    view.announce("Next player skipped!");
                }
                case DRAW_TWO, WILD_DRAW_TWO -> drawNext = 2;
                default -> { }
            }

            int nextIdx = normalized(current + direction);

            if (drawNext > 0) {
                Player np = players.get(nextIdx);
                ensureDeckHas(drawNext);
                np.drawFrom(deck, drawNext);
                view.announce(np.getName() + " draws " + drawNext + ".");
            }

            current = skipNext ? normalized(nextIdx + direction) : nextIdx;

            Player next = players.get(current);
            view.showNext(next.getName(), next.hand().size());

            if (p.hasWon()) {
                endRound(p);
                break;
            }
        }
    }

    /**
     * Makes a player draw one card from the deck.
     *
     * @param p the player who draws
     */
    private void drawOne(Player p) {
        ensureDeckHas(1);
        Card c = deck.draw();
        if (c != null) {
            p.hand().add(c);
            view.announce(p.getName() + " drew: " + c);
            if (rules.canPlay(c, discard.top(), activeColor)) {
                view.announce("Playable! You could have played it, but drawing passes the turn.");
            }
        } else {
            view.announce("Deck is empty; you cannot draw.");
        }
    }

    /**
     * Ensures the deck has enough cards by refilling it from the discard pile if needed.
     *
     * @param n the minimum number of cards required
     */
    private void ensureDeckHas(int n) {
        if (deck.size() >= n) return;
        List<Card> back = discard.takeAllButTop();
        if (!back.isEmpty()) deck.addAllToBottom(back);
    }

    /**
     * Ends the round and awards points to the winner.
     *
     * @param winner the player who won the round
     */
    private void endRound(Player winner) {
        int gained = 0;
        for (Player q : players) {
            if (q != winner) gained += rules.handScore(q.hand());
        }
        scores.add(winner.getName(), gained);
        view.announce("Round over! Winner: " + winner.getName() + " +" + gained + " points");
        view.announce("Scores: " + scores.asMap());
    }

    /**
     * Moves to the next player's turn.
     */
    private void advanceTurn() {
        current = normalized(current + direction);
        Player next = players.get(current);
        view.showNext(next.getName(), next.hand().size());
    }

    /**
     * Keeps the player index within bounds.
     *
     * @param idx the index to normalize
     * @return a valid player index
     */
    private int normalized(int idx) {
        int n = players.size();
        return ((idx % n) + n) % n;
    }

    /**
     * Checks if a card is a number card (0–9).
     *
     * @param c the card to check
     * @return true if the card is numeric, false otherwise
     */
    private boolean isNumber(Card c) {
        return c.getRank().ordinal() <= Rank.NINE.ordinal();
    }

    /**
     * Parses a string into an integer index.
     *
     * @param s the input string
     * @return the parsed number, or null if invalid
     */
    private Integer parseIndex(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
