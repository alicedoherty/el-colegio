import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 * 
 * @author Alice Doherty
 * 
 */

public class CompetitionDijkstra {
	
	private int N; // number of intersections
	private int S; // number of streets
	private EdgeWeightedDigraph dijkstraGraph;
	private int speedA;
	private int speedB;
	private int speedC;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    	
    	this.speedA = sA;
    	this.speedB = sB;
    	this.speedC = sC;
    	
    	
    	String line;
    	String[] street = {};
    	
    	if(filename == null)
    		this.dijkstraGraph = null;
    	
    	else {
    		try {
        		File file = new File(filename);
            	Scanner scanner = new Scanner(file);
            	
            	N = Integer.parseInt(scanner.nextLine());
            	S = Integer.parseInt(scanner.nextLine());
            	
            	if(N == 0)
            		this.dijkstraGraph = null;
            	
            	else {
            		this.dijkstraGraph = new EdgeWeightedDigraph(N, S);
        		
	        		while(scanner.hasNextLine()) {
	        			line = scanner.nextLine();
	        			street = line.trim().split("\\s+");
	        			int from = Integer.parseInt(street[0]);
	        			int to = Integer.parseInt(street[1]);
	        			double weight = Double.parseDouble(street[2]);
	        			
	        			List<DirectedEdge> list = dijkstraGraph.adj.getOrDefault(from, new ArrayList<>());
	        			list.add(new DirectedEdge(from, to, weight));
	        			dijkstraGraph.adj.put(from, list);
	        		}
            	}
	
            	scanner.close();
            	
        	} catch (FileNotFoundException e) {
        		this.dijkstraGraph = null;
        	}
    	}	
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	
    	if(dijkstraGraph == null)
    		return -1;

    	int min = Math.min(speedA, Math.min(speedB, speedC));
    	int max = Math.max(speedA, Math.max(speedB, speedC));
    	
    	if(min < 50 || max > 100)
    		return -1;
    	
    	double maxDistance = 0.0;
    	
    	for(int i = 0; i < dijkstraGraph.V; i++) {
    		double[] distances = dijkstra(i);
    		
    		for(int j = 0; j < dijkstraGraph.V; j++) {
    			double tmpDist = distances[j];
    			if(tmpDist == Double.POSITIVE_INFINITY) 
    				return -1;
    			maxDistance = Math.max(maxDistance, tmpDist);
    		}
    	}
    	
    	double maxTimeNeeded = maxDistance * 1000 / min;
    	
    	return (int)Math.ceil(maxTimeNeeded);
    }
    
    public double[] dijkstra(int source) {
    	boolean[] visited = new boolean[dijkstraGraph.V];
    	double[] distTo = new double[dijkstraGraph.V];
    	Map<Integer, List<DirectedEdge>> adj = dijkstraGraph.adj;
    	PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparing(v -> distTo[v]));

    	for(int i = 0; i < distTo.length; i++) {
    		distTo[i] = Double.POSITIVE_INFINITY;
    	}
    	
    	distTo[source] = 0.0;
    	priorityQueue.add(source);
    	
    	
    	while(!priorityQueue.isEmpty()) {
    		int currentV = priorityQueue.poll();
    		visited[currentV] = true;
    		
    		for(DirectedEdge adjacent : adj.getOrDefault(currentV, new ArrayList<>())) {
    			int v = adjacent.to;
    			if(visited[v] == false) {
    				double distanceCount = distTo[currentV] + adjacent.weight;
    				if (distTo[v] > distanceCount) {
    					distTo[v] = distanceCount;
    				}
    				priorityQueue.remove(v);
    				priorityQueue.add(v);
    			}
    			
    		}
    	}
    	return distTo;
    }

}