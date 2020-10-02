
import java.util.*;

public class MatchResult {


    public boolean canTeamADefeatTeamB(List<MatchOutcome> matches, String teamA, String teamB) {

        return isReachable(buildGraph(matches), teamA, teamB, new HashSet<>());


    }

    private boolean isReachable(Map<String, Set<String>> graph, String curr, String dest, Set<String> visited) {

        if (curr.equals(dest)) {
            return true;
        } else if (visited.contains(curr) || graph.get(curr) == null) {
            return false;
        }

        visited.add(curr);

        return graph.get(curr).stream().anyMatch(neighbour -> isReachable(graph, neighbour, dest, visited) == true);

    }


    private Map<String, Set<String>> buildGraph(List<MatchOutcome> matches) {
        Map<String, Set<String>> graph = new HashMap<>();

        matches.forEach(match -> {
            graph.putIfAbsent(match.winningTeam, new HashSet<>());
            graph.get(match.winningTeam).add(match.losingTeam);
        });

        return graph;

    }


    public static void main(String[] args) {

        List<MatchOutcome> matches = new ArrayList<>();

        matches.add(new MatchOutcome("A", "T1"));
        matches.add(new MatchOutcome("A", "T3"));
        matches.add(new MatchOutcome("T2", "T1"));
        matches.add(new MatchOutcome("B", "T3"));
        matches.add(new MatchOutcome("T3", "T2"));
        matches.add(new MatchOutcome("T3", "B"));

        System.out.println(new MatchResult().canTeamADefeatTeamB(matches, "A", "B"));

    }

}

class MatchOutcome {
    public String winningTeam;
    public String losingTeam;

    public MatchOutcome(String winningTeam, String losingTeam) {
        this.winningTeam = winningTeam;
        this.losingTeam = losingTeam;
    }
}