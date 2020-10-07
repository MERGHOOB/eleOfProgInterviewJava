import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShortestPathWithFewEdges {


    public static void shortestPath(GraphVertex s, GraphVertex t) {

        // set Initial cost to visit the source is 0;
        s.dwfe.distance = 0;
        SortedSet<GraphVertex> nodeSet = new TreeSet<>(); // that's why we have compareTo and equals
        nodeSet.add(s);

        while (!nodeSet.isEmpty()) {

            GraphVertex u = nodeSet.first();
            if (u.equals(t)) {
                break;
            }

            nodeSet.remove(nodeSet.first());
            // Relax neighboring vertex of u;
            for (VertexWithDistance v : u.edges) {
                int vDistance = u.dwfe.distance + v.distance;
                int vEdges = u.dwfe.edgeCount + 1;

                if (v.vertex.dwfe.distance > vDistance
                        || (v.vertex.dwfe.distance == vDistance && v.vertex.dwfe.edgeCount > vEdges)) {
                    nodeSet.remove(v.vertex);
                    v.vertex.dwfe.distance = vDistance;
                    v.vertex.dwfe.edgeCount = vEdges;
                    v.vertex.pred = u;
                    nodeSet.add(v.vertex);
                }
            }
        }

        outputShortestPath(t);

    }

    private static void outputShortestPath(GraphVertex t) {

        if (t != null) {
            outputShortestPath(t.pred);
            System.out.print(t.id + " ");
        }
    }


    private static class GraphVertex implements Comparable<GraphVertex> {

        public List<VertexWithDistance> edges = new ArrayList<>();
        public int id;


        // Some custom required to hold the distance value and to print output the pred.
        public GraphVertex pred = null;
        public DistanceWithFewestEdges dwfe = new DistanceWithFewestEdges(Integer.MAX_VALUE, 0);

        @Override
        public int compareTo(GraphVertex o) {
            return !this.dwfe.distance.equals(o.dwfe.distance)
                    ? Integer.compare(this.dwfe.distance, o.dwfe.distance)
                    : Integer.compare(this.dwfe.edgeCount, o.dwfe.edgeCount);
        }


        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof GraphVertex)) {
                return false;
            }
            if (this == obj) {
                return true;
            }

            GraphVertex that = (GraphVertex) obj;
            return id == that.id && dwfe.distance.equals(that.dwfe.distance) && dwfe.edgeCount.equals(that.dwfe.edgeCount);
        }

    }

    private static class DistanceWithFewestEdges {
        public Integer distance;
        public Integer edgeCount;

        DistanceWithFewestEdges(int distance, int edgeCount) {
            this.distance = distance;
            this.edgeCount = edgeCount;
        }
    }


    private static class VertexWithDistance {
        public GraphVertex vertex;
        public Integer distance;

        public VertexWithDistance(GraphVertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }


}