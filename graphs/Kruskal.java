import java.util.*;

public class Kruskal {
	

	private static int root(int label, int [] disjoint) {

		while (disjoint[label] != label) {
			label = disjoint[label];
		}

		return label;
	}

	private static void union(int x, int y, int[] disjoint) {

		disjoint[root(y, disjoint)] = root(x, disjoint);

	}


	public static List<Edge> getMST(Graph graph) {

		int[] disjoint = new int[graph.numberOfVertices+10];
		for (int i = 0; i< graph.numberOfVertices; i++ ) { 
			disjoint[i] = i;	// AS there is no union still, every vertex is disjoint.
		}


		graph.edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});


		List<Edge> mst = new ArrayList<>();
		
		for(Edge edge: graph.edges) {

			int fromLabel = edge.from.label;
			int toLabel = edge.to.label;

			if(root(fromLabel, disjoint) != root(toLabel, disjoint)) {
				union(fromLabel, toLabel, disjoint);
				mst.add(edge);
			}
		}


		return mst;
	}


	private static class Graph {
		
		public List<Edge> edges;// = new ArrayList<>();
		public int numberOfVertices;
		public List<Vertex> vertices;
		
		public Graph(int numberOfVertices) {
			this.numberOfVertices = numberOfVertices;
			edges = new ArrayList<>();
			vertices = new ArrayList<>();
		}
	}

	private static class Vertex {
		public int label;
		public List<Edge> edges;

		public Vertex(int label) {
			this.label = label;
		}

	}
	private static class Edge {
		
		public Vertex from, to;
		public int weight;

		public Edge(Vertex from, Vertex to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public Edge(int from, int to, int weight) {
			this.from = new Vertex(from);
			this.to = new Vertex(to);
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "[ " + from.label + " ----- " + weight + " ----- "+  to.label +" ]";
		}

	}

	public static void main(String[] arr) {

		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		Edge e1 = new Edge(v1,v2,1);
		Edge e2 = new Edge(v1,v3,7);

		Edge e3 = new Edge(v2,v3,5);
		Edge e4 = new Edge(v2,v4,4);
		Edge e5 = new Edge(v4,v5,2);
		Edge e6 = new Edge(v2,v5,3);
		Edge e7 = new Edge(v5,v3,6);


		Graph graph = new Graph(5);
		graph.edges.add(e1);	
		graph.edges.add(e2);
		graph.edges.add(e3);
		graph.edges.add(e4);
		graph.edges.add(e5);
		graph.edges.add(e6);
		graph.edges.add(e7);



		List<Edge> mst = getMST(graph);
		mst.forEach(System.out::println);

	}

}