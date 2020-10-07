import java.util.*;

public class BellmanFord {

    public void collectDistances(Graph graph, GraphVertex source) {

        Map<GraphVertex, Integer> result = new HashMap<>();
        source.distance = 0;

        for (int i = 1; i < graph.numberOfVertices; i++) { // at most V-1
            for (Edge edge : graph.edges) {
                GraphVertex from = edge.from;
                GraphVertex to = edge.to;
                int weight = edge.weight;

                if (from.distance != Integer.MAX_VALUE &&
                        to.distance > from.distance + weight) {
                    to.distance = from.distance + weight;
                }


            }
        }

		for (Edge edge : graph.edges) {
			GraphVertex from = edge.from;
			GraphVertex to = edge.to;
			int weight = edge.weight;

			if (from.distance != Integer.MAX_VALUE &&
					to.distance > from.distance + weight) {
				to.distance = from.distance + weight;
				System.out.println("Cycle present negative weight");
				return;
			}


		}
        // return result;

    }

    private static class Graph {
        int numberOfVertices;
        List<Edge> edges;

        public Graph(int numberOfVertices, List<Edge> edges) {
            this.numberOfVertices = numberOfVertices;
            this.edges = edges;
        }
    }

    private static class GraphVertex {
        public int label;
        public int distance;
        public List<Edge> edges = new ArrayList<>();

        public GraphVertex(int label) {
            this.label = label;
            distance = Integer.MAX_VALUE;
        }
    }

    private static class Edge {
        public GraphVertex from, to;
        public int weight;

        public Edge(GraphVertex from, GraphVertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            from.edges.add(this);
        }
    }


    public static void main(String[] args) {

        GraphVertex v1 = new GraphVertex(1);
        GraphVertex v2 = new GraphVertex(2);
        GraphVertex v3 = new GraphVertex(3);
        GraphVertex v4 = new GraphVertex(4);
        GraphVertex v5 = new GraphVertex(5);
        GraphVertex v6 = new GraphVertex(6);

        List<GraphVertex> g = new ArrayList<>();
        g.add(v1);
        g.add(v2);
        g.add(v3);
        g.add(v4);
        g.add(v5);
        g.add(v6);

        Edge e1 = new Edge(v1, v2, 4);
        Edge e2 = new Edge(v2, v4, 1);
        Edge e3 = new Edge(v1, v3, 1);
        Edge e4 = new Edge(v2, v3, 9);
        Edge e5 = new Edge(v4, v6, 7);
        Edge e6 = new Edge(v5, v1, 9);
        List<Edge> edges = new ArrayList<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);
//        edges.add(new Edge(v3, v1, -2));

        new BellmanFord().collectDistances(new Graph(g.size(), edges), v1);
        g.stream().map(v -> v.distance).forEach(System.out::println);


    }


}