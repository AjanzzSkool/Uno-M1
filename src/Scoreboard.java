import java.util.*;

/**
 * Keeps track of player scores in the Uno game.
 */
public class Scoreboard {
    private final Map<String, Integer> scores = new LinkedHashMap<>();

    /**
     * Makes sure a player is added to the scoreboard.
     * If the player doesn't exist, they are added with a score of 0.
     *
     * @param name the player's name
     */
    public void ensurePlayer(String name) { 
        scores.putIfAbsent(name, 0); 
    }

    /**
     * Gets a player's current score.
     *
     * @param name the player's name
     * @return the player's score, or 0 if not found
     */
    public int get(String name) { 
        return scores.getOrDefault(name, 0); 
    }

    /**
     * Adds points to a player's score.
     *
     * @param name the player's name
     * @param delta the number of points to add
     */
    public void add(String name, int delta) { 
        scores.put(name, get(name) + delta); 
    }

    /**
     * Returns an unmodifiable view of all player scores.
     *
     * @return a map of player names to their scores
     */
    public Map<String, Integer> asMap() { 
        return Collections.unmodifiableMap(scores); 
    }
}
