import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author Alice Doherty
 *  @version 13/10/16 18:15
 */

@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // Check that isEmpty() returns true if DLL is empty, false otherwise
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue("Check that isEmpty() returns true for empty DLL", testDLL.isEmpty());
    	testDLL.insertBefore(0, 23);
    	assertFalse("Check that isEmpty() returns false when DLL is not empty", testDLL.isEmpty());
    }
    
    // Test insertFirst() works (only with non-empty lists - as if list is empty will go into if(isEmpty()) in insertBefore() and not call insertFirst())
    @Test
    public void testInsertFirst() 
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 15); 
    	testDLL.insertBefore(1, 9);
        testDLL.insertFirst(7);
        assertEquals("Checking insertFirst to a non-empty list", "7,15,9", testDLL.toString());
    }
    
    // Test insertLast() works (only with non-empty lists - as if list is empty will go into if(isEmpty()) in insertBefore() and not call insertLast())
    @Test
    public void testInsertLast()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 12); 
    	testDLL.insertBefore(1, 10);
        testDLL.insertLast(2);
        assertEquals("Checking insertFirst to a non-empty list", "12,10,2", testDLL.toString());
    }
    
    // Test get() works
    @Test
    public void testGet()
    {
	    // test with non-empty list containing elements 1,2,3
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	    testDLL.insertBefore(0,1);
	    testDLL.insertBefore(1,2);
	    testDLL.insertBefore(2,3);
	    assertEquals("Checking get() returns element at position 0 (head)", "1", testDLL.get(0).toString());
	    assertEquals("Checking get() returns element at position 1", "2", testDLL.get(1).toString());
	    assertEquals("Checking get() returns element at position 2 (tail)", "3", testDLL.get(2).toString());
	    
	    // test with position that is out of bounds
	    assertEquals("Checking get() returns null when negative position entered", null, testDLL.get(-1));
	    assertEquals("Checking get() returns null when position is out of bounds (pos == length)", null, testDLL.get(3));
	    assertEquals("Checking get() returns null when position is out of bounds (pos > length)", null, testDLL.get(23));
	    
	    // test with empty list
	    testDLL = new DoublyLinkedList<Integer>();
	    assertEquals("Checking get() returns null when list is empty", null, testDLL.get(0));
    }
    
    // Test deleteAt() works
    @Test
    public void testDeleteAt()
    {
    	// test deleteAt at pos 0 (head) with non-empty list containing elements 1,2,3
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	boolean deleteAtReturn;
    	
	    testDLL.insertBefore(0,1);
	    testDLL.insertBefore(1,2);
	    testDLL.insertBefore(2,3);
	    
	    deleteAtReturn = testDLL.deleteAt(0);
	    assertEquals("Checking deleteAt() deletes element at position 0 (head)", "2,3", testDLL.toString());
	    assertTrue("Checking deleteAt() returns true when element is deleted", deleteAtReturn);
	    
	    // test deleteAt at pos 2 (tail) with non-empty list containing elements 1,2,3
	    testDLL = new DoublyLinkedList<Integer>();
	    testDLL.insertBefore(0,1);
	    testDLL.insertBefore(1,2);
	    testDLL.insertBefore(2,3);
	    
	    deleteAtReturn = testDLL.deleteAt(2);
	    assertEquals("Checking deleteAt() deletes element at position 2 (tail)", "1,2", testDLL.toString());
	    assertTrue("Checking deleteAt() returns true when element is deleted", deleteAtReturn);
	    
	    // test deleteAt at pos 1 with non-empty list containing elements 1,2,3
	    testDLL = new DoublyLinkedList<Integer>();
	    testDLL.insertBefore(0,1);
	    testDLL.insertBefore(1,2);
	    testDLL.insertBefore(2,3);
	    
	    deleteAtReturn = testDLL.deleteAt(1);
	    assertEquals("Checking deleteAt() deletes element at position 1", "1,3", testDLL.toString());
	    assertTrue("Checking deleteAt() returns true when element is deleted", deleteAtReturn);
	    
	    // test with list of single node
	    testDLL = new DoublyLinkedList<Integer>();
	    testDLL.insertBefore(0,1);
	    
	    deleteAtReturn = testDLL.deleteAt(0);
	    assertTrue("Checking deleteAt() returns true when element is deleted", deleteAtReturn);
	    
	    // test with positions that are out of bounds
	    testDLL = new DoublyLinkedList<Integer>();
	    testDLL.insertBefore(0,1);
	    testDLL.insertBefore(1,2);
	    testDLL.insertBefore(2,3);
	    
	    // test with negative position
	    deleteAtReturn = testDLL.deleteAt(-1);
	    assertEquals("Checking deleteAt() doesn't delete any element when negative position is given", "1,2,3", testDLL.toString());
	    assertFalse("Checking deleteAt() returns false when element is not deleted (pos < 0)", deleteAtReturn);
	    
	    // test with position equal to list length
	    deleteAtReturn = testDLL.deleteAt(3);
	    assertEquals("Checking deleteAt() doesn't delete any element when pos == length", "1,2,3", testDLL.toString());
	    assertFalse("Checking deleteAt() returns false when element is not deleted (pos == length)", deleteAtReturn);
	    
	    // test with position greater than list length
	    deleteAtReturn = testDLL.deleteAt(7);
	    assertEquals("Checking deleteAt() doesn't delete any element when pos > length", "1,2,3", testDLL.toString());
	    assertFalse("Checking deleteAt() returns false when element is not deleted (pos > 0)", deleteAtReturn);
    }
    
    // Test testReverse() works
    @Test
    public void testReverse()
    {
    	// test reverse() reverses elements correctly
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
        testDLL.insertBefore(0,"A");
        testDLL.insertBefore(1,"B");
        testDLL.insertBefore(2,"C");
        testDLL.insertBefore(3,"D");
        testDLL.reverse();
        assertEquals("Checking reverse() reverses order of elements correctly", "D,C,B,A", testDLL.toString());
    	
    	// test reverse() when list contains one node
        testDLL = new DoublyLinkedList<String>();
        testDLL.insertBefore(0,"A");
        testDLL.reverse();
        assertEquals("Checking reverse() doesn't change anything if list only has one node", "A", testDLL.toString());
    }
    
    // Test makeUnique() works
    @Test
    public void testMakeUnique() 
    {
    	// test makeUnique() removes any duplicate elements and maintains relative order
    	DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
        testDLL.insertBefore(0,"A");
        testDLL.insertBefore(1,"B");
        testDLL.insertBefore(2,"C");
        testDLL.insertBefore(3,"B");
        testDLL.insertBefore(4,"D");
        testDLL.insertBefore(5,"A");
        
        testDLL.makeUnique();
        assertEquals("Checking makeUnique() removes duplicate elements and maintains relative order", "A,B,C,D", testDLL.toString());
        
        // test makeUnique() when there are no duplicate elements
        testDLL = new DoublyLinkedList<String>();
        testDLL.insertBefore(0,"A");
        testDLL.insertBefore(1,"B");
        testDLL.insertBefore(2,"C");
        testDLL.insertBefore(3,"D");
        
        testDLL.makeUnique();
        assertEquals("Checking makeUnique() when there are no duplicate elements in lsit", "A,B,C,D", testDLL.toString());
    }
    
    // Test push() works
    @Test
    public void testPush() 
    {
    	// test pushing 3 elements onto list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(1);
        testDLL.push(2);
        testDLL.push(3);
        assertEquals("Checking push() inserted 3 elements to list correctly", "3,2,1", testDLL.toString());	
    }
    
    // Test pop() works
    @Test
    public void testPop() 
    {
    	// test pop() from list with elements 3,2,1
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        int poppedItem;
        
        testDLL.push(1);
        testDLL.push(2);
        testDLL.push(3);
        poppedItem = testDLL.pop();
        assertEquals("Checking pop() deletes element last in", "2,1", testDLL.toString());
        assertEquals("Checking pop() returns element last in", 3, poppedItem);
        
        // test pop() from single element list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(1);
        poppedItem = testDLL.pop();
        assertEquals("Checking pop() returns element popped (from single element list)", 1, poppedItem);
        
        // test pop() from empty list
        testDLL = new DoublyLinkedList<Integer>();
        assertEquals("Checking pop() returns null when list is empty", null, testDLL.pop());	
    }
    
    // Test enqueue() works
    @Test
    public void testEnqueue() 
    {
    	// test enqueuing 3 elements onto list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(1);
        testDLL.enqueue(2);
        testDLL.enqueue(3);
        assertEquals("Checking enqueue() inserted 3 elements to list correctly", "1,2,3", testDLL.toString());	
    }
    
    // Test dequeue() works
    @Test
    public void testDequeue() 
    {
    	// test dequeue() from list with elements 1,2,3
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        int dequeuedItem;
        
        testDLL.enqueue(1);
        testDLL.enqueue(2);
        testDLL.enqueue(3);
        dequeuedItem = testDLL.dequeue();
        assertEquals("Checking dequeue() deletes element first in", "2,3", testDLL.toString());
        assertEquals("Checking dequeue() returns element first in", 1, dequeuedItem);	
        
        // test dequeue() from single element list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(1);
        dequeuedItem = testDLL.dequeue();
        assertEquals("Checking dequeue() returns element dequeued (from single element list)", 1, dequeuedItem);
        
        // test dequeue() from empty list
        testDLL = new DoublyLinkedList<Integer>();
        assertEquals("Checking dequeue() returns null when list is empty", null, testDLL.dequeue());	
    }
}

