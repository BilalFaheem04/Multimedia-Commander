/**
 * 
 * @author Bilal Faheem
 *	BinarySearchTree class - this class represents a binary search tree
 *
 *
 */
public class BinarySearchTree {
	
	// instance variable
			private BSTNode root; // BST node for the root
			
			/**
			 * Constructor
			 */
			public BinarySearchTree() {
				root = new BSTNode(null); // set the root to null
			}
			
			/**
			 * getRoot - gets the root of the BST
			 * @return
			 */
			public BSTNode getRoot() {
				return root; // return root
			}
			
			/**
			 * get - gets a node with particular key inside the tree with root r. Returns either the node containing the key or the leaf where they key should be.
			 * @param r
			 * @param k
			 * @return
			 */
			public BSTNode get (BSTNode r, Key k) {
				//System.out.println(r.getRecord().getKey().compareTo(k));
				if (r == null || r.getRecord() == null) {
					return null;
				}
//				else if (r.isLeaf() && r.getRecord() != null) { // if the node is a leaf
//					//System.out.println(r.getRecord().getKey().getLabel());
//					return r; // return the leaf
//				}
				else { // if the root is  not a leaf
					
					if (k.compareTo(r.getRecord().getKey()) == 0) { // check if the key of r is equal to k
						return r; // return node
					}
					else if (k.compareTo(r.getRecord().getKey()) == -1) { // check if k is smaller than they key of this node
						return get(r.getLeftChild(), k); // continue the get searching on the left side of the node
					}
					else { // if k is larger than this node's key
						return get(r.getRightChild(), k); // continue the get searching on the right side of the node
					}
				}
			}
			
			/**
			 * insert - inserts a node into the tree. If the node is already in the tree, then throw a DictionaryException
			 * @param r
			 * @param d
			 * @throws DictionaryException
			 */
			public void insert (BSTNode r, Record d) throws DictionaryException {
				if (r.getRecord() == null || r == null) { // if the record of r is null, or the node is null itself
					r.setRecord(d); // set the record of this node to d
				}
				else { // we are at an internal node
					
					// CODE NOT WORKING :(
//					BSTNode p = get(r, d.getKey()); // THE GET IS NOT WORKING?? // its because I did not set leaf nodes to BSTNode(null), i just set node.leftChild and rightChild to null instead of node(null)
//					if (p == null) {
//						p = new BSTNode(d);
//					}
//					else { // if the node is a leaf
//						if (d.getKey().compareTo(r.getRecord().getKey()) == 1) {
//							//System.out.println("right");
//							r.setRightChild(new BSTNode(d));
//							r.getRightChild().setParent(r);
//						}
//						else if (d.getKey().compareTo(r.getRecord().getKey()) == -1) {
//							//System.out.println("left");
//							r.setLeftChild(new BSTNode(d));
//							r.getLeftChild().setParent(r);
//						}
//						else{
//							throw new DictionaryException("Record already in Tree");
//						}
//					}
					//Rewrite the code without get, get is acting weird
					
					// if record we wish to insert has a key smaller than the node's key
					if (d.getKey().compareTo(r.getRecord().getKey()) == -1) {
						if (r.getLeftChild() == null) { // if the left child is null
							r.setLeftChild(new BSTNode(d)); // set the left child of this node to be the new record (as a node)
							r.getLeftChild().setParent(r); // set the left child's parent to be this node
						}
						// if we are in an internal node
						else insert(r.getLeftChild(), d); // traverse to the left side of the node (since the record's key we wish to insert is smaller than node's key)
					}
					// if the record we wish to insert has a key larger than the node's key
					else if (d.getKey().compareTo(r.getRecord().getKey()) == 1) {
						if (r.getRightChild() == null) { // if the right child of the node is null
							r.setRightChild(new BSTNode(d)); // set the right child to be the new record (as a node)
							r.getRightChild().setParent(r); // set the right child's parent to be this node
						}
						// if we are in an internal node
						else insert(r.getRightChild(), d); // traverse to the right side of the node (since the record's key we wish to insert is larger than node's key)
					}
					// if the record we wish to insert has the same key as the current node
					else {
						throw new DictionaryException("Key is already in the tree"); // throw an exception
					}
				}
				
			}
			
			/**
			 * remove - deletes the node with the given key from the tree with root r
			 * @param r
			 * @param k
			 * @return
			 * @throws DictionaryException
			 */
			public void remove (BSTNode r, Key k) throws DictionaryException {
				BSTNode p = get(r, k); // use get method to find the node or the place where it should be
				//System.out.println(p);
				if (p == null) { // if there is no node with that key
					throw new DictionaryException("Node with that key does not exist"); // throw exception
				}
				else { // if there is a node
					if (p.isLeaf()) { //  if the node is a leaf
						BSTNode parent = p.getParent(); // store the parent of the node
						if(parent != null) { // if the parent is not null
							if (parent.getLeftChild() == p) { // if the left child of the parent is the node p
								parent.setLeftChild(null); // set parents left child to be null
							}
							else { // otherwise, set the right child of the parent to be null
								parent.setRightChild(null);
							}
						}
						else { // if the node is a leaf and the parent is null
							root = new BSTNode(null); // set the root to be null
						}
					}
					else if(p.getLeftChild() == null) { // if the left child is null
						BSTNode otherNode = p.getRightChild(); // get the other child
						BSTNode parent = p.getParent(); // get the parent
						
						if (parent != null) { // if the parent is not null
							if(parent.getRightChild() == p) { // if the parent's right child is the node we are trying to delete
								parent.setRightChild(otherNode); // set the right child of the parent to be the other node
								otherNode.setParent(parent); // set the other node's parent to be parent
							}
							else { // if the parent's left child is the node we are trying to delete
								parent.setLeftChild(otherNode); // set the left child of the parent to be the other node
								otherNode.setParent(parent); // set the other node's parent to be parent
							}
						}
						else { // if the parent is null (meaning the current node p is the root)
							root = otherNode; // then set the root to be the other node
						}
					}
					else if (p.getRightChild() == null) { // if the right child of p is null
						BSTNode otherNode = p.getLeftChild(); // get the other child
						BSTNode parent = p.getParent(); // get the parent
						
						if (parent != null) { // if the parent is not null
							if (parent.getLeftChild() == p) { // if the parent's left child is the node we are trying to delete
								parent.setLeftChild(otherNode); // set the left child of the parent to be the other node
								otherNode.setParent(parent); // set the other node's parent to be parent
							}
							else { // if the parent's right child is the node we are trying to delete
								parent.setRightChild(otherNode); // set the right child of the parent to be the other node
								otherNode.setParent(parent); // set the other node's parent to be parent
							}
						}
						else { // if the parent is null (meaning the current node p is the root)
							root = otherNode; // set the root to be the other node
						}
					}
					else { // if the left and right child have non-null nodes (the node p is an internal node)
						BSTNode smallest = smallest(p.getRightChild()); // get the smallest node on the right of the current node
						p.setRecord(smallest.getRecord()); // set the current node's record to be the smallest node on the right's record
						remove(smallest, smallest.getRecord().getKey()); // remove the smallest on the right after we have copied it to p (using recursive call)
					}
				}
				
			}
			/**
			 * successor - finds the node storing the successor of the given key in the tree with root r
			 * @param r
			 * @param k
			 * @return
			 */
			public BSTNode successor(BSTNode r, Key k) {
				BSTNode p = get(r, k); // use the get method to find a node with key k and store it in node p
				
				if (p == null) { // if the get returns a null, that means we did not find a node with that key
					return null; // return null
				}
				
				if(p.getRightChild() != null) { // if the right child of p is not null
					return smallest(p.getRightChild()); // return the smallest value on the right subtree of p
				} 
				else { // if the right child is a leaf
					p = p.getParent(); // set p to the parent of p
					while(p != null && k.compareTo(p.getRecord().getKey()) == 1) { // while p is not null and the key k is greater than the key in the current node p
						p = p.getParent(); // set p to p's parent
					}
					return p; // return p
				}
			}
			
			/**
			 * predecessor - finds the node storing the predecessor of the given key in the tree with root r
			 * @param r
			 * @param k
			 * @return
			 */
			public BSTNode predecessor(BSTNode r, Key k) {
				BSTNode p = get(r,  k); // use the get method to find a node with the key k and store it in the node p
				
				if (p == null) { // if p is null, then that means the get did not find a node with key k
					return null; // return null
				}
				
				if(p.getLeftChild() != null) { // if the left child of p is not null
					return largest(p.getLeftChild()); // return the largest value on the left of p
				}
				else { // if the left child is a leaf
					p = p.getParent(); // set p to the parent of p
					while (p != null && k.compareTo(p.getRecord().getKey()) == -1) { // while p is not null and k is less than the key in the current node p
						p = p.getParent();	// set p to p's parent	
					}
					return p; // return p
				}
			}
			
			/**
			 * smallest - returns the node with the smallest key
			 * @param r
			 * @return
			 */
			public BSTNode smallest(BSTNode r) {
				if(r == null) { // if the node is null
					return null; // return null
				}
				else {
					BSTNode p = r; // make a new node that points to r
					while(p != null) { // while p is not null
						if(p.getLeftChild() == null) return p; // if the left child is null, return the current node as the smallest
						p = p.getLeftChild(); // point to the left child of p
					}
					return p.getParent(); // return p's parent (we have reached the left most internal node, not leaf)
				}
			}
			
			/**
			 * largest - returns the node with the largest key
			 * @param r
			 * @return
			 */
			public BSTNode largest(BSTNode r) {
				if(r == null) { // if the node is null
					return null; // return null
				}
				else {
					BSTNode p = r; // make a new node that points to r
					while(p != null) { // while p is not null
						if(p.getRightChild() == null) return p; // if the right child is null, return the current node as the largest
						p = p.getRightChild(); // set p to the right child of p
					}
					return p.getParent(); // return p's parent (we have reached the rightmost internal node, not leaf)
				}
			}
	
	
}
