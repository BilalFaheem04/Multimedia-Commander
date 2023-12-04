/**
 * 
 * @author Bilal Faheem
 * Record Class used to specify the Record objects that will be used for BST. Defines the getters for the instance variables.
 *
 */
public class Record {
	
	// instance variables
	private Key theKey; // Key variable
	private String data; // data variable
	
	/**
	 * Constructor
	 * @param k
	 * @param theData
	 */
	public Record(Key k, String theData) {
		theKey = k; // set theKey to inputed Key variable k
		data = theData; // set data to the inputed data String
	}
	
	/**
	 * getKey method - getter for theKey, returns theKey
	 * @return
	 */
	public Key getKey() {
		return theKey; // return theKey
	}
	
	/**
	 * getDataItem method - getter for the data, returns data
	 * @return
	 */
	public String getDataItem() {
		return data; // return data
	}
}
