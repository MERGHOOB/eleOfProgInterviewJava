import java.util.*;

public class Dijkstra {

	public void dijkstra(Graph graph, GraphVertex source) {

		// Assume like graph does have all edges, all GraphVertex has a distance infinity at start
		source.distance = 0;
		PriorityQueue<GraphVertex> priorityQueue = new PriorityQueue<>(graph.numOfVertices, new Comparator<GraphVertex>() {
			@Override
			public int compare(GraphVertex o1, GraphVertex o2) {
				return Integer.compare(o1.distance, o2.distance);
			}
		});

		priorityQueue.add(source);

		while(!priorityQueue.isEmpty())
 		{	
 			GraphVertex curr = priorityQueue.poll();
 			for(GraphEdge next: curr.edges) {
 				if(curr.distance + next.weight < next.to.distance) {
 					next.to.distance = curr.distance + next.weight;
 					// PriorityQueue.add(next.to);
 					priorityQueue.remove(next.to);
 					priorityQueue.add(next.to);
 				}
 			}
 		}
	}

	private static class Graph {
		public int numOfVertices;


	}

	private static class GraphEdge {
		public GraphVertex from, to;
		public int weight;
	}

	private static class GraphVertex  {
		public int distance = Integer.MAX_VALUE;
		public List<GraphEdge> edges;
	}

}