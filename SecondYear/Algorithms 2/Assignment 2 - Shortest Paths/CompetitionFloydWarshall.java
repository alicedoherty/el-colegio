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
 * This class implements the competition using Floyd-Warshall algorithm
 * 
 * @author Alice Doherty
 *  
 */

public class CompetitionFloydWarshall {
	
	private int N; // number of intersections
	private int S; // number of streets
	private EdgeWeightedDigraph floydGraph;
	private int speedA;
	private int speedB;
	private int speedC;
	private double[][] distances;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){

    	this.speedA = sA;
    	this.speedB = sB;
    	this.speedC = sC;
    	
    	String line;
    	String[] street = {};
    	
    	if(filename == null)
    		this.floydGraph = null;
    	
    	else {
    		try {
        		File file = new File(filename);
            	Scanner scanner = new Scanner(file);
            	
            	N = Integer.parseInt(scanner.nextLine());
            	S = Integer.parseInt(scanner.nextLine());
            	
            	if(N == 0)
            		this.floydGraph = null;
            	
            	else {
                	this.floydGraph = new EdgeWeightedDigraph(N, S);
                	distances = new double[floydGraph.V][floydGraph.V];
                	
                	for(int i = 0; i < floydGraph.V; i++) {
                		for(int j = 0; j < floydGraph.V; j++) {
                			distances[i][j] = Double.POSITIVE_INFINITY;
                		}
                	}
                		
            		while(scanner.hasNextLine()) {
            			line = scanner.nextLine();
            			street = line.trim().split("\\s+");
            			int from = Integer.parseInt(street[0]);
            			int to = Integer.parseInt(street[1]);
            			double weight = Double.parseDouble(street[2]);
            			
            			distances[from][to] = weight;
            			distances[from][from] = 0;
            		}	
            	}
            		
            	scanner.close();
            	
        	} catch (FileNotFoundException e) {
        		this.floydGraph = null;
        	}
    	}	
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	
       	if(floydGraph == null) {
    		return -1;
    	}

    	int min = Math.min(speedA, Math.min(speedB, speedC));
    	int max = Math.max(speedA, Math.max(speedB, speedC));
    	
    	if(min < 50 || max > 100)
    		return -1;
    	
    	double maxDistance = 0.0;
    	double[][] shortestPaths = floydWarshall();
    	
    	for(int i = 0; i < floydGraph.V; i++) {
    		for(int j = 0; j < floydGraph.V; j++) {
    			double tmpDist = shortestPaths[i][j];
    			if(tmpDist == Double.POSITIVE_INFINITY) return -1;
    			maxDistance = Math.max(maxDistance, tmpDist);
    		}
    	}
    	
    	double maxTimeNeeded = maxDistance * 1000 / min;
    	
    	return (int)Math.ceil(maxTimeNeeded);
    }
    
    public double[][] floydWarshall() {
    	for(int k = 0; k < floydGraph.V; k++) {
    		for(int i = 0; i < floydGraph.V; i++) {
    			for(int j = 0; j < floydGraph.V; j++) {
    				if(distances[i][k] + distances[k][j] < distances[i][j]) {
    					distances[i][j] = distances[i][k] + distances[k][j];
    				}
    			}
    		}
    	}
    	return distances;
    }
}