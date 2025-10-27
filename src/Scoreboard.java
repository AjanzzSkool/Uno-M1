import java.util.*;


public class Scoreboard {
    private final Map<String,Integer> scores = new LinkedHashMap<>();


    public void ensurePlayer(String name) { scores.putIfAbsent(name, 0); }
    public int get(String name) { return scores.getOrDefault(name, 0); }
    public void add(String name, int delta) { scores.put(name, get(name) + delta); }
    public Map<String,Integer> asMap() { return Collections.unmodifiableMap(scores); }
}
