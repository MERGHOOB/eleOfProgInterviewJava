import java.util.*;

public class MakingWiredConnectionORBipartiteGraph {

    public static Boolean isAnyPlacementFeasible(List<GraphVertex> g) {

        // for()
        for (GraphVertex vertex : g) {

            if (vertex.d == -1) { //unvisited
                vertex.d = 0;  // make it as root for BFS
                if (!bfs(vertex)) {
                    return false;
                }
            }

        }
        return true;
    }

    private static boolean bfs(GraphVertex u) {
        Queue<GraphVertex> q = new LinkedList<>();
        q.add(u);

        while (!q.isEmpty()) {

            GraphVertex curr = q.poll();
            for (GraphVertex neigh : curr.edges) {

                if (neigh.d == -1) {
                    neigh.d = curr.d + 1;
                    q.add(neigh);
                } else if (neigh.d == curr.d) {
                    return false;
                }

            }

        }
        return true;
    }


    private static class GraphVertex {

        public int d = -1; // it is not visited so it does not have distance now
        public List<GraphVertex> edges = new ArrayList<>();

    }

}