/**
 * 
 * @author Bilal Faheem
 *	BSTNode Class which is used for representing the individual node for a BST. Contains the getters and setters for the instance variables
 */
public class BSTNode {
	// instance variables
	private Record record;
	private BSTNode leftC, rightC, parent;
	
	/**
	 * Constructor
	 * @param item
	 */
	public BSTNode (Record item) {
		record = item; // sets the record to the inputed Record item variable
		// set the left child, right child and parent to be null
		leftC = null;
		rightC = null;
		parent = null;
	}
	
	/**
	 * getRecord - getter for the record
	 * @return
	 */
	public Record getRecord() {
		return record; // return record
	}
	
	/**
	 * setRecord - setter for the record
	 * @param d
	 */
	public void setRecord(Record d) {
		record = d; // set record to the inputed Record d
	}
	
	/**
	 * getLeftChild - getter for the left child of the node
	 * @return
	 */
	public BSTNode getLeftChild() {
		return leftC; // return leftc (left child)
	}
	
	/**
	 * getRightChild - getter for the right child of the node
	 * @return
	 */
	public BSTNode getRightChild() {
		return rightC; // return rightc (right child)
	}
	
	/**
	 * getParent - getter for the parent
	 * @return
	 */
	public BSTNode getParent() {
		return parent; // returns parent
	}
	
	/**
	 * setLeftChild - setter for the left child
	 * @param u
	 */
	public void setLeftChild(BSTNode u) {
		leftC = u; // set left child to the BSTNode u
	}
	
	/**
	 * setRightChild - setter for the right child
	 * @param u
	 */
	public void setRightChild(BSTNode u) {
		rightC = u; // set right child to the BSTNode u
	}
	
	/**
	 * setParent - setter for the parent
	 * @param u
	 */
	public void setParent (BSTNode u) {
		parent = u; // set parent to BSTNode u
	}
	
	/**
	 * isLeaf - checks if node is a leaf (returns true, otherwise if not a leaf, then returns false)
	 * @return
	 */
	public boolean isLeaf() {
		if (leftC == null && rightC == null) { // if the left child and right child are both null
			return true; // then this is a leaf node, return true
		}
		return false; // otherwise, this is not a leaf node, return false
	}
	
}
