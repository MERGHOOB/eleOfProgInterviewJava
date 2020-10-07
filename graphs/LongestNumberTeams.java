

import java.util.*;

public class LongestNumberTeams {






	public static int findLargestNumberTeams(List<GraphVertex> G) {
		Deque<GraphVertex> orderedVertices = buildTopoOrdering(G);

		return findLongestPath(orderedVertices);
	}


	private static int findLongestPath(Deque<GraphVertex> orderedVertices) {
		int maxDistance = 0;
		while(!orderedVertices.isEmpty()) {
			GraphVertex peek = orderedVertices.peekFirst();
			maxDistance = Math.max(maxDistance, peek.maxDistance);

			for(GraphVertex v: peek.edges) {
				v.maxDistance = Math.max(v.maxDistance, peek.maxDistance + 1);
			}
			orderedVertices.removeFirst();
		}
		return maxDistance;
	}

	private static Deque<GraphVertex> buildTopoOrdering(List<GraphVertex> G) {
		Deque<GraphVertex> orderedVertices = new LinkedList<>();
		for(GraphVertex g: G) {
			if(!g.isVisited) {
				DFS(g, orderedVertices);
			}
		}
		return orderedVertices;
	}

	private static void DFS(GraphVertex curr, Deque<GraphVertex> orderedVertices) {

		curr.isVisited = true;
		for(GraphVertex next: curr.edges) {
			if(next.isVisited) {
				continue;
			}

			DFS(curr, orderedVertices);
		}
		orderedVertices.addFirst(curr);
	}

	private static class GraphVertex {


		public List<GraphVertex> edges;
		public boolean isVisited = false;
		public int maxDistance = 1;

	}


}