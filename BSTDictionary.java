/**
 * 
 * @author Bilal Faheem
 * BSTDictionary class - implements an ordered dictionary ADT using binary search tree
 *
 */
public class BSTDictionary implements BSTDictionaryADT{
	
	// instance variable
	private BinarySearchTree tree; // BinarySearchTree object
	
	/**
	 * constructor
	 */
	public BSTDictionary() {
		tree = new BinarySearchTree(); // initialize the BST
	}
	
	@Override
	/**
	 * get - gets the record in the BST with that key
	 */
	public Record get(Key k) {
		BSTNode node = tree.get(tree.getRoot(), k); // use the get function to get the node where the key is
		if(node == null) return null; // if the key is not in tree, return null
		return node.getRecord(); // return the record
	}

	@Override
	/**
	 * put - puts the record into the BST
	 */
	public void put(Record d) throws DictionaryException {
		try {
			tree.insert(tree.getRoot(), d); // insert the record into the tree
			//System.out.println(tree.get(tree.getRoot(), d.getKey()));
		}
		catch (DictionaryException e){ // if exception is thrown
			throw new DictionaryException("Record already in Tree"); // throw an exception
		}
	}

	@Override
	/**
	 * remove - remove the node with the inputed key
	 */
	public void remove(Key k) throws DictionaryException {
		try {
			tree.remove(tree.getRoot(), k); // remove the key k from the tree
		}
		catch (DictionaryException e) { // if an exception is thrown
			throw new DictionaryException("Record is not in Tree"); // throw an exception
		}
	}

	@Override
	/**
	 * successor - finds the successor of the node with given key k
	 */
	public Record successor(Key k) {
		BSTNode testNode = tree.successor(tree.getRoot(), k); // find the successor and store it in a node
		if(testNode == null) return null;  // if the node is null, return null
		return testNode.getRecord(); // return the record
	}

	@Override
	/**
	 * predecessor - finds the predecessor of the node with given key k
	 */
	public Record predecessor(Key k) {
		BSTNode testNode = tree.predecessor(tree.getRoot(), k); // find the predecessor and store it in a node
		if(testNode == null) return null; // if the node is null, return null
		return testNode.getRecord(); // return the record
	}

	@Override
	/**
	 * smallest - find the smallest node
	 */
	public Record smallest() {
		BSTNode testNode = tree.smallest(tree.getRoot()); // gets the smallest node and store it in a node
		if(testNode == null) return null; // if the node is null, return null
		return testNode.getRecord(); // return the record of smallest
	}

	@Override
	/**
	 * largest - find the largest node
	 */
	public Record largest() {
		BSTNode testNode = tree.largest(tree.getRoot()); // gets the largest node and store it in a node
		if(testNode == null) return null; // if the node is null, return null
		return testNode.getRecord(); // return the record of the largest
	}

}
