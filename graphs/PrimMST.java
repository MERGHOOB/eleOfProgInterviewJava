import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class PrimMST {

	public boolean[] visited = new boolean[1001];

	public static int prim(Map<Integer,List<AdjVertex>> graph, boolean[] visited) {

		//Assuming vertex 1 is present.

		int mstWeight = 0;
		PriorityQueue<AdjVertex> p = new PriorityQueue<>(graph.keySet().size(), new Comparator<AdjVertex>() {

				@Override
				public int compare(AdjVertex one, AdjVertex two) {
					return Integer.compare(one.weight, two.weight);
				}
		});

		p.add(new AdjVertex(1,0));
		while(!p.isEmpty()) {
			AdjVertex polled = p.poll();
			if(visited[polled.label]) {
				continue;
			}
			mstWeight += polled.weight;
			visited[polled.label] = true;

			for(AdjVertex adj: graph.get(polled.label)) {
				if(visited[adj.label]) {
					continue;
				}
				p.add(adj);
			}
		}
		return mstWeight;
	}

	// private static class GraphVertex {
	// 	int label;
	// 	List<GraphVertex> vertices = new ArrayList<>();
	// }

	private static class AdjVertex {
		int label;
		int weight;

		AdjVertex(int label, int weight) {
			this.label = label;
			this.weight = weight;
		}

	}

	public static void main(String [] args) 
	{

		try
		{
			BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));


			String line = br.readLine();
			String arr[] = line.split("\\s+");
			int nodes = Integer.parseInt(arr[0].trim());
			int edges = Integer.parseInt(arr[1].trim());

			Map<Integer, List<AdjVertex>> graph = new HashMap<>();

			for(int i = 0; i< edges; i++) {
				
				line = br.readLine();
				arr = line.split("\\s+");
				
				int x = Integer.parseInt(arr[0].trim());
				int y = Integer.parseInt(arr[1].trim());
				int weight = Integer.parseInt(arr[2].trim());

				graph.putIfAbsent(x, new ArrayList<>());
				graph.get(x).add(new AdjVertex(y, weight));
				
				graph.putIfAbsent(y, new ArrayList<>());
				graph.get(y).add(new AdjVertex(x,weight));

			}

			boolean [] visited = new boolean[nodes+1];
			System.out.println(prim(graph, visited));

		} 
		catch(IOException ignore) {

		}


	}
}