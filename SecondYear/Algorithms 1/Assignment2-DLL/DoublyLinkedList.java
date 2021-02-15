import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Alice Doherty
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int length;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      length = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  Method only contains a single basic comparison which is assumed
     *  to take Theta(1) time to execute.
     */
    public boolean isEmpty()
    {
    	return head == null;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  Worst-case input is if element is getting inserted before the last element.
     * 	The last else statement will be run and the program will iterate through the 
     * 	all the elements in the for-loop. The operations inside the loop are assumed to 
     * 	take Theta(1) time so the running time of the for-loop is N*Theta(1) = Theta(N). 
     * 	All other operations are basic and assumed to take Theta(1) time therefore total
     * 	running time is Theta(N).
     */
    public void insertBefore(int pos, T data) 
    {
    	if(isEmpty()) {
    		DLLNode newNode = new DLLNode(data, null, null);
    		head = newNode;
    		tail = newNode;
    	}

    	else if(pos <= 0) {
    		insertFirst(data);
    	}

    	else if(pos >= length) {
    		insertLast(data);
    	}

    	else {
    		DLLNode tmp = head;
    		for(int i = 0; i < pos; i++) {
    			tmp = tmp.next;
    		}
    		DLLNode newNode = new DLLNode(data, null, null);
    		newNode.prev = tmp.prev;
    		newNode.next = tmp;
    		newNode.prev.next = newNode;
    		newNode.next.prev = newNode;
    	}
    	length++;
    	return;
    }
    
    /**
     * insertFirst() and insertLast() are implemented by insertBefore() if the node
     * is being inserted to a list at the first or last position (and the list is not empty)
     */
    
    public void insertFirst(T data) {
    	DLLNode newNode = new DLLNode(data, null, null);
    	DLLNode oldHead = head;
    	oldHead.prev = newNode;
    	newNode.next = oldHead;
    	head = newNode;    	
    }
    
    public void insertLast(T data) {
    	DLLNode newNode = new DLLNode(data, null, null);
    	DLLNode oldTail = tail;
    	oldTail.next = newNode;
    	newNode.prev = oldTail;
    	tail = newNode;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  Worst case input is if pos is for second last element. In this case the final else
     *  statement will be run. The program will iterate through the elements of the list through 
     *  the for-loop roughly N-1 times. The operation inside the for-loop is assumed to take Theta(1) 
     *  time so cost of the for-loop is (N-1)*Theta(1) = Theta(N-1). We can simplify Theta(N-1) to 
     *  Theta(N) by only keeping the highest order term. The other operations are basic and assumed 
     *  to take Theta(1) time, therefore total worst-case asymptotic running time is Theta(N).
     */
    public T get(int pos) 
    {
    	// handles empty lists (length = 0) and out of bounds pos
    	if(pos < 0 || pos >= length)
    		return null;
    	
    	// returns head data
    	else if(pos == 0) {
    		return head.data;
    	}
    	
    	// returns tail data
    	else if(pos == length - 1) {
    		return tail.data;
    	}
    	
    	else {
    		DLLNode tmp = head;
    		for(int i = 0; i < pos; i++) {
    			tmp = tmp.next;
    		}
    		return tmp.data;
    	}
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     * 	Worst case input is if pos is for second last element. In this case the final else
     *  statement will be run. The program will iterate through the elements of the list through 
     *  the for-loop roughly N-1 times. The operation inside the for-loop is assumed to take Theta(1) 
     *  time so cost of the for-loop is (N-1)*Theta(1) = Theta(N-1). We can simplify Theta(N-1) to 
     *  Theta(N) by only keeping the highest order term. The other operations are basic and assumed 
     *  to take Theta(1) time, therefore total worst-case asymptotic running time is Theta(N).
     */
    public boolean deleteAt(int pos) 
    {
    	if(pos < 0 || pos >= length)
    		return false;
    	
    	// removing element at pos 0 when list has 1 element
    	else if(head == tail) {
    		head = null;
    		tail = null;
    	}
    	
    	// removing current head for list where length > 1
    	else if(pos == 0) {
    		head = head.next;
    		head.prev.next = null;
    		head.prev = null;
    	}
    	
    	// removing current tail for list where length > 1
    	else if(pos == length - 1) {
    		tail = tail.prev;
    		tail.next.prev = null;
    		tail.next = null;
    	}
    	
    	else {
    		DLLNode tmp = head;
    		for(int i = 0; i < pos; i++) {
    			tmp = tmp.next;
    		}
    		tmp.prev.next = tmp.next;
    		tmp.next.prev = tmp.prev;
    		tmp.prev = null;
    		tmp.next = null;
    	}
    	length--;
    	return true;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  The program will iterate through the while loop N times (iterates over each element). 
     *  We assume each swap (the contents of the while loop) take Theta(1) time, therefore 
     *  the total cost of the while loop is N*Theta(1) = Theta(N). The other operations outside 
     *  the loop are basic and assumed to be Theta(1), therefore the total cost of the function is
     *  Theta(N).
     */
	public void reverse() 
	{
		if (length > 1) {
			DLLNode node = head;
			DLLNode tmp = null;

			while (node != null) {
				tmp = node.prev;
				node.prev = node.next;
				node.next = tmp;
				node = node.prev;
			}
			tmp = head;
			head = tail;
			tail = tmp;
		}
	}

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements unique.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(N^2)
     *
     * Justification:
     *  In the worst case scenario, none of the elements are duplicates and the program will have to 
     *  compare every element in the list to each other (if some were duplicates, they would be deleted,
     *  making the list length shorter and decreasing the iterations needed). In this case, the outer for-
     *  loop is iterated through N times, and the contents of the for-loop have a cost of 2N (get() has a 
     *  running cost of N and the while loop is iterated through N times (deleteAt() isn't called in 
     *  the worst-case so the while loop contents run at Theta(1)), therefore N + N = 2N). All other operations in 
     *  the for-loop are basic with a cost of Theta(1). Therefore, the running time of the for-loop is
     *  N*(2N), but 2N can be simplified to N, so the total cost is Theta(N^2).
     */
	public void makeUnique() 
	{
		for (int i = 0; i < length; i++) {
			DLLNode currentNode = head;
			T testData = get(i);
			int nodePos = 0;
			while (currentNode != null) {
				DLLNode savedNext = currentNode.next;
				if (currentNode.data == testData && nodePos != i) {
					deleteAt(nodePos);
					nodePos--;
				}
				currentNode = savedNext;
				nodePos++;
			}
		}
	}


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  The method doesn't have to iterate through any loops (as the position of the pushed element 
     *  is always the same) and the operations performed are all assumed to take constant time, 
     *  therefore the total cost is Theta(1).
     */
	public void push(T item) 
	{
		if (head == null) {
			head = new DLLNode(item, null, null);
		}

		else {
			DLLNode oldHead = head;
			head = new DLLNode(item, null, oldHead);
			oldHead.prev = head;
		}
		length++;
	}

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  This method only contains operations that are assumed to take constant time. There is no
     *  need to iterate through the elements as the position of the element being popped is always 
     *  the same, therefore the total cost is Theta(1).
     */
    public T pop() 
    {
    	DLLNode oldHead = head;
    	
    	if(head == null)
    		return null;

    	else if(length == 1) {
    		head = null;
    		tail = null;
    	}
    	else {
    		head = oldHead.next;
    		oldHead.next = null;
    		head.prev = null;
    	}
		length--;
		return oldHead.data;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  This method doesn't need to iterate through the list as the position of the
     *  enqueued item is always the same. All the operations in the method are assumed
     *  to take constant time, therefore the cost is Theta(1).
     */
    public void enqueue(T item) 
    {
    	if (head == null) {
    		head = new DLLNode(item, null, null);
    		tail = head;
    	}
    	else {
    		DLLNode newNode = new DLLNode(item, tail, null);
    		tail.next = newNode;
    		tail = newNode;
    	}
    	length++;
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an enqueue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  This method doesn't iterate through the items of the list as the position of the
     *  dequeued item will always be the same. All the operations in the method are assumed to 
     *  take constant time, therefore the cost is Theta(1).
     */
    public T dequeue() 
    {
    	DLLNode oldHead = head;
    	
    	if (head == null)
    		return null;
    	
    	else if(head == tail) {
    		head = null;
    		tail = null;
    	}
    	else {
    		head = oldHead.next;
    		oldHead.next = null;
    		head.prev = null;
    	}
    	length--;
    	return oldHead.data;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost: Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }
}
