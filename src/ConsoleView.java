import java.util.*;

/**
 * Handles all console input and output for the Uno game.
 * This class is responsible for showing game information
 * and getting input from players.
 * @author Aws Ali
 * @version 1.0
 */
public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Asks the user for the number of players.
     *
     * @return the number of players (between 2 and 4)
     */
    public int askPlayerCount() {
        System.out.print("Enter number of players (2-4): ");
        int n = scanner.nextInt();
        scanner.nextLine();
        return Math.max(2, Math.min(4, n));
    }

    /**
     * Asks for a player's name.
     *
     * @param idx the player number (starting from 0)
     * @return the player's name
     */
    public String askPlayerName(int idx) {
        System.out.print("Player " + (idx + 1) + " name: ");
        return scanner.nextLine().trim();
    }

    /**
     * Displays the player's hand on the screen.
     *
     * @param p the player whose hand to show
     */
    public void showHand(Player p) {
        System.out.println("\n" + p.getName() + "'s hand:");
        List<Card> h = p.hand();
        for (int i = 0; i < h.size(); i++)
            System.out.println(" [" + (i + 1) + "] " + h.get(i));
    }

    /**
     * Shows the top card of the discard pile
     * and the current active color.
     *
     * @param top the top card on the discard pile
     * @param currentColor the current active color
     */
    public void showTop(Card top, Color currentColor) {
        System.out.println("Top of discard: " + (top == null ? "(none)" : top)
                + (currentColor != null ? " | ActiveColor=" + currentColor : ""));
    }

    /**
     * Prompts a player to make a move.
     *
     * @param p the player taking their turn
     * @return the player's input
     */
    public String promptTurn(Player p) {
        System.out.print("\n" + p.getName() + " – enter card index, 0 to draw or type in HAND/HELP: ");
        return scanner.nextLine().trim();
    }

    /**
     * Asks the player to choose a color for a Wild card.
     *
     * @return the color chosen
     */
    public Color promptWildColor() {
        System.out.print("Choose color [R,G,B,Y]: ");
        String s = scanner.nextLine().trim().toUpperCase();
        return switch (s) {
            case "R" -> Color.RED;
            case "G" -> Color.GREEN;
            case "B" -> Color.BLUE;
            default -> Color.YELLOW;
        };
    }

    /**
     * Shows which player goes next and how many cards they have.
     *
     * @param name the player's name
     * @param cardCount number of cards in their hand
     */
    public void showNext(String name, int cardCount) {
        System.out.println("Next: " + name + " (" + cardCount + " cards)");
    }

    /**
     * Displays a short game message.
     *
     * @param msg the message to display
     */
    public void announce(String msg) {
        System.out.println("== " + msg);
    }
}
