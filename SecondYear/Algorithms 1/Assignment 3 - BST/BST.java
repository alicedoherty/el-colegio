/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Alice Doherty
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N), where N = number of nodes in the tree
     * 		
     * Explanation:
     * 		All operations within the method are basic and are assumed to have constant running time (Theta(1)).
     * 		The method works by repeatedly calling height(Node x) on smaller BSTs working its way back up to the 
     * 		root returning whichever is the larger between the left/right side height each time,
     * 		therefore it will have to traverse each Node in the tree, so WC running time is	N * Theta(1) = Theta(N)
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    
    public int height() {
    	return height(root);
    }
    
    private int height(Node x) {
    	if(x == null)
    		return -1;
    	
    	int leftHeight = height(x.left);
    	int rightHeight = height(x.right);
    	
    	return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < k(N-1), then their median key 
     * is the element at position (N-1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    
    public Key median() {
      if (isEmpty()) 
    	  return null;
      else {
    	  int position = (size()-1)/2;
    	  return median(root, position);
      }
    }

    private Key median(Node x, int pos) {
    	int smallerCount = size(x.left);
    	if (smallerCount > pos)
    		return median(x.left, pos);
    	else if (smallerCount < pos)
    		return median(x.right, pos-smallerCount-1);
    	else 
    		return x.key;
    }

    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    
    public String printKeysInOrder() {
    	return printKeysInOrder(root);
    }
    
    private String printKeysInOrder(Node x) {
    	String keysInOrder = "";
    	
    	if(x == null) {
    		keysInOrder += "()";
    	}
    	else {
        	keysInOrder += "(" + printKeysInOrder(x.left);
        	keysInOrder += x.key;
        	keysInOrder += printKeysInOrder(x.right) + ")";
        	
    	}
    	return keysInOrder;
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    
    public String prettyPrintKeys() {
    	return prettyPrint(root, "");
    }
    
    private String prettyPrint(Node x, String prefix) {
    	String prettyPrint = "";
    	if(x == null) {
    		prettyPrint = prefix + "-null\n";
    	}
    	else {
        	prettyPrint += prefix + "-" + x.key + "\n";
        	prettyPrint += prettyPrint(x.left, prefix + " |");
        	prettyPrint += prettyPrint(x.right, prefix + "  ");
        	
    	}
    	return prettyPrint;
    }

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    
    public void delete(Key key) {
    	root = delete(root, key);
    }
    
    private Node delete(Node x, Key key) {
    	if(x == null) 
    		return null;
    	
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
        	x.left =  delete(x.left, key);
        else if (cmp > 0)
        	x.right = delete(x.right, key);
        else {
        	// If there's no right/left child
        	if (x.right == null) return x.left;
        	if (x.left == null) return x.right;
        	
        	// If node has 2 children - delete Node and replace with predecessor
        	Node toDelete = x;
        	x = max(x.left);
        	x.left = deleteMax(toDelete.left);
        	x.right = toDelete.right;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    // Find max node in relation to root entered
    private Node max(Node x) {
    	if(x.right == null) return x;
    	else return max(x.right);
    }
    
    // Delete the max node and update Node size
    private Node deleteMax(Node x) {
    	if(x.right == null) return x.left;
    	x.right = deleteMax(x.right);
    	x.N = 1 + size(x.right) + size(x.left);
    	return x;
    }
}