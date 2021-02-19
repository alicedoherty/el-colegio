import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Alice Doherty
 */

@RunWith(JUnit4.class)
public class BSTTest
{

	/** <p>Test {@link BST#prettyPrintKeys()}.</p> */


	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree", "-null\n", bst.prettyPrintKeys());

							//  -7
							//   |-3
							//   | |-1
							//   | | |-null
		bst.put(7, 7);      //   | |  -2
		bst.put(8, 8);      //   | |   |-null
		bst.put(3, 3);      //   | |    -null
		bst.put(1, 1);      //   |  -6
		bst.put(2, 2);      //   |   |-4
		bst.put(6, 6);      //   |   | |-null
		bst.put(4, 4);      //   |   |  -5
		bst.put(5, 5);      //   |   |   |-null
							//   |   |    -null
							//   |    -null
							//    -8
							//     |-null
							//      -null

		String result = 
				"-7\n" +
						" |-3\n" + 
						" | |-1\n" +
						" | | |-null\n" + 
						" | |  -2\n" +
						" | |   |-null\n" +
						" | |    -null\n" +
						" |  -6\n" +
						" |   |-4\n" +
						" |   | |-null\n" +
						" |   |  -5\n" +
						" |   |   |-null\n" +
						" |   |    -null\n" +
						" |    -null\n" +
						"  -8\n" +
						"   |-null\n" +
						"    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
	}


	/** <p>Test {@link BST#delete(Comparable)}.</p> */
	@Test
	public void testDelete() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7);  //        _7_
		bst.put(8, 8);  //      /     \
		bst.put(3, 3);  //    _3_      8
		bst.put(1, 1);  //  /     \
		bst.put(2, 2);  // 1       6
		bst.put(6, 6);  //  \     /
		bst.put(4, 4);  //   2   4
		bst.put(5, 5);  //        \
						//         5

		assertEquals("Checking order of constructed tree",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child",
				"(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children",
				"(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
	}


	// Test isEmpty()
	@Test
	public void testIsEmpty() {
		BST<String, String> bst = new BST<String, String>();
		assertTrue("Checking isEmpty() for empty BST", bst.isEmpty());

		bst.put("B", "B"); 
		bst.put("A", "A");
		assertFalse("Checking isEmpty() for non-empty BST", bst.isEmpty());
	}
	
	// Test size()
	@Test
	public void testSize() {
		BST<String, String> bst = new BST<String, String>();
		assertEquals("Checking size() for empty BST", 0, bst.size());
		
		bst.put("B", "B");
		assertEquals("Checking size() for single node BST", 1, bst.size());
		
    	bst.put("B", "B"); 
     	bst.put("A", "A");   
        bst.put("C", "C");       
        bst.put("D", "D");
        assertEquals("Check size() for multiple node BST", 4, bst.size());
	}
	
	// Test contains()
	@Test
	public void testContains() {
		BST<String, String> bst = new BST<String, String>();
		assertFalse("Checking contains() for empty BST", bst.contains("A"));
		
    	bst.put("B", "B"); 
     	bst.put("A", "A");   
        bst.put("C", "C");       
        bst.put("D", "D");
        assertTrue("Check contains() for multiple node BST where node is present", bst.contains("C"));
        assertFalse("Check contains() for multiple node BST where node is not present", bst.contains("E"));
	}
	
	// Test get()
	@Test
	public void testGet() {
		BST<String, String> bst = new BST<String, String>();
		assertEquals("Checking get() for empty BST", null, bst.get("A"));
		
    	bst.put("B", "B"); 
     	bst.put("A", "A");   
        bst.put("C", "C");       
        bst.put("D", "D");
        assertEquals("Check get() for multiple node BST where node is present", "A", bst.get("A"));
        assertEquals("Check get() for multiple node BST where node is not present", null, bst.get("E"));
	}
	
	// Test put()
	@Test
	public void testPut() {
		BST<String, String> bst = new BST<String, String>();
		bst.put("A", "A");
		bst.put("B", "B");
		bst.put("B", "F");
		assertEquals("Checking put() when key is inserted twice", "F", bst.get("B"));
		bst.put("A", null);
		assertEquals("Checking put() deletes node when val = null", null, bst.get("A"));
	}
	
	// Test height()
	@Test
	public void testHeight() {
		BST<String, String> bst = new BST<String, String>();
		assertEquals("Checking height() for empty BST", -1, bst.height());
		
		bst.put("A", "A");
		assertEquals("Checking height() for single node BST", 0, bst.height());
		
		bst = new BST<String, String>();
    	bst.put("B", "B"); 
     	bst.put("A", "A");   
        bst.put("C", "C");       
        bst.put("D", "D"); 
        assertEquals("Checking height() with example 1", 2, bst.height());
        
        bst = new BST<String, String>();
    	bst.put("S", "S");
    	bst.put("X", "X");
    	bst.put("E", "E");
    	bst.put("R", "R");
    	bst.put("H", "H");
    	bst.put("M", "M");
    	bst.put("A", "A");  
        bst.put("C", "C");
        assertEquals("Checking height() with example 2", 4, bst.height());
	}

	// Test median()
	@Test
	public void testMedian() {
		BST<String, String> bst = new BST<String, String>();
		assertEquals("Checking median() for empty BST", null, bst.median());
		
		bst.put("A", "A");
		assertEquals("Checking median() for single node BST", "A", bst.median());
		
		bst = new BST<String, String>();
    	bst.put("B", "B"); 
     	bst.put("A", "A");   
        bst.put("C", "C");       
        bst.put("D", "D"); 
        assertEquals("Checking median() with example 1", "B", bst.median());
        
        bst = new BST<String, String>();
    	bst.put("S", "S");
    	bst.put("X", "X");
    	bst.put("E", "E");
    	bst.put("R", "R");
    	bst.put("H", "H");
    	bst.put("M", "M");
    	bst.put("A", "A");  
        bst.put("C", "C");
        assertEquals("Checking median() with example 2", "H", bst.median());
	}
}
