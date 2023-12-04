/**
 * 
 * @author Bilal Faheem
 * Key Class: defines how to key objects work and methods used to manipulate keys
 *
 */
public class Key {
	
	// instance variables
	private String label; // stores the label
	private int type; // stores the type
	
	/**
	 * Constructor
	 * @param theLabel
	 * @param theType
	 */
	public Key(String theLabel, int theType) {
		label = theLabel.toLowerCase(); // set the label to the inputed label (in lower case)
		type = theType; // set the type to the inputed type
	}
	
	/**
	 * getLabel method used to get the label of a key
	 * @return
	 */
	public String getLabel() {
		return label; // return the label
	}
	
	/**
	 * getType method used to get the type of a key
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * compareTo method used to compare keys and see which is smaller or larger or equal
	 * @param k
	 * @return
	 */
	public int compareTo (Key k) {
		if (label.equals(k.getLabel().toLowerCase()) && type == k.getType()) { // check if the labels are the same and the types are the same
			return 0; // return 0 (meaning they are equal keys)
		}
		else if (label.compareTo(k.getLabel()) < 0 || (label.equals(k.getLabel()) && type < k.getType())) { // if this.label is smaller than the inputed key label OR this.type is less than inputed key type
			return -1; // then "this" key is smaller, so return -1
		}
		else { // "this" key is greater than Key k
			return 1; // return 1
		}
	}
}
