import java.util.*;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public int askPlayerCount() {
        System.out.print("Enter number of players (2-4): ");
        int n = scanner.nextInt();
        scanner.nextLine();
        return Math.max(2, Math.min(4, n));
    }

    public String askPlayerName(int idx) {
        System.out.print("Player " + (idx + 1) + " name: ");
        return scanner.nextLine().trim();
    }

    public void showHand(Player p) {
        System.out.println("\n" + p.getName() + "'s hand:");
        List<Card> h = p.hand();
        for (int i = 0; i < h.size(); i++)
            System.out.println(" [" + (i + 1) + "] " + h.get(i));
    }

    public void showTop(Card top, Color currentColor) {
        System.out.println("Top of discard: " + (top == null ? "(none)" : top)
                + (currentColor != null ? " | ActiveColor=" + currentColor : ""));
    }

    public String promptTurn(Player p) {
        System.out.print("\n" + p.getName() + " – enter card index, 0 to draw or type in HAND/HELP: ");
        return scanner.nextLine().trim();
    }

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

    public void showNext(String name, int cardCount) {
        System.out.println("Next: " + name + " (" + cardCount + " cards)");
    }

    public void announce(String msg) {
        System.out.println("== " + msg);
    }
}