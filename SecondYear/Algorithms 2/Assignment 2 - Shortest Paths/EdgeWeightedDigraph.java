import java.util.*;

public class EdgeWeightedDigraph {
	
	public int V; // number of vertices
	public int E; // number of edges
	public Map<Integer, List<DirectedEdge>> adj; // all edges pointing from v
	
	
	EdgeWeightedDigraph(int V, int E) {
		this.V = V;
		this.E = E;
		adj = new HashMap<>();
		
	}	
}
