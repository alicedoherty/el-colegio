import static org.junit.Assert.*;

import org.junit.Test;

public class CompetitionTests {
	
	@Test
	public void testDijkstra() {
		
		// Test with null filename
		CompetitionDijkstra testDijkstra = new CompetitionDijkstra(null, 60, 70, 90);
		assertEquals("Testing Dijkstra with null filename", -1, testDijkstra.timeRequiredforCompetition());
				
		// Test with invalid filename
		testDijkstra = new CompetitionDijkstra("test", 60, 70, 90);
		assertEquals("Testing Dijkstra with invalid filename", -1, testDijkstra.timeRequiredforCompetition());
				
		// Test with speeds < 50 or > 100
		testDijkstra = new CompetitionDijkstra("input-A.txt", 45, 70, 90);
		assertEquals("Testing Dijkstra with speed < 50", -1, testDijkstra.timeRequiredforCompetition());
		
		testDijkstra = new CompetitionDijkstra("input-A.txt", 50, 70, 140);
		assertEquals("Testing Dijkstra with speed > 100", -1, testDijkstra.timeRequiredforCompetition());
				
		// Test with tinyEWD.txt
		testDijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 70, 75);
		assertEquals("Testing Dijkstra with tinyEWD.txt", 38, testDijkstra.timeRequiredforCompetition());
		
//		// Test with tinyEWD.txt
//		testDijkstra = new CompetitionDijkstra("1000EWD.txt", 50, 70, 75);
//		assertEquals("Testing Dijkstra with 1000EWD.txt", 28, testDijkstra.timeRequiredforCompetition());
		
		// Test with map that doesn't have path between two certain locations
		testDijkstra = new CompetitionDijkstra("input-A.txt", 60, 50, 75);
		assertEquals("Testing Dijkstra with map that doesn't have path between two locations", -1, testDijkstra.timeRequiredforCompetition());
		
		// Test with file that doesn't have any vertices/edges
		testDijkstra = new CompetitionDijkstra("input-J.txt", 60, 50, 75);
		assertEquals("Testing Dijkstra with file that doesn't have any vertices/edges", -1, testDijkstra.timeRequiredforCompetition());
		
		
	}
	
	@Test
	public void testFloydWarshall() {
		
		// Test with null filename
		CompetitionFloydWarshall testFloydWarshall = new CompetitionFloydWarshall(null, 60, 70, 90);
		assertEquals("Testing FloydWarshall with null filename", -1, testFloydWarshall.timeRequiredforCompetition());
				
		// Test with invalid filename
		testFloydWarshall = new CompetitionFloydWarshall("test", 60, 70, 90);
		assertEquals("Testing FloydWarshall with invalid filename", -1, testFloydWarshall.timeRequiredforCompetition());
				
		// Test with speeds < 50 or > 100
		testFloydWarshall = new CompetitionFloydWarshall("input-A.txt", 45, 70, 90);
		assertEquals("Testing FloydWarshall with speed < 50", -1, testFloydWarshall.timeRequiredforCompetition());
		
		testFloydWarshall = new CompetitionFloydWarshall("input-A.txt", 50, 70, 140);
		assertEquals("Testing FloydWarshall with speed > 100", -1, testFloydWarshall.timeRequiredforCompetition());
				
		// Test with tinyEWD.txt
		testFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 70, 75);
		assertEquals("Testing FloydWarshall with tinyEWD.txt", 38, testFloydWarshall.timeRequiredforCompetition());
		
		// Test with tinyEWD.txt
//		testFloydWarshall = new CompetitionFloydWarshall("1000EWD.txt", 50, 70, 75);
//		assertEquals("Testing FloydWarshall with 1000EWD.txt", 28, testFloydWarshall.timeRequiredforCompetition());
		
		// Test with map that doesn't have path between two certain locations
		testFloydWarshall = new CompetitionFloydWarshall("input-A.txt", 60, 50, 75);
		assertEquals("Testing FloydWarshall with map that doesn't have path between two locations", -1, testFloydWarshall.timeRequiredforCompetition());
		
		// Test with file that doesn't have any vertices/edges
		testFloydWarshall = new CompetitionFloydWarshall("input-J.txt", 60, 50, 75);
		assertEquals("Testing FloydWarshall with file that doesn't have any vertices/edges", -1, testFloydWarshall.timeRequiredforCompetition());
		
	}

}
