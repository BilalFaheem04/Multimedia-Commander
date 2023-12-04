import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Bilal Faheem
 * Interface Class - the user interface
 *
 */
public class Interface {
	
	/*
	 * listOfPrefix - helper method for the list command
	 * returns the list of labels that start with the prefix inputed
	 */	
	private String listOfPrefix(BSTDictionary dictionary, String prefix) {
		Record tempRecord = new Record(new Key(prefix, 0), null); // create a record with the prefix
		String list = ""; // string to store all the records with that prefix
		boolean first = true; // boolean to check if we have had our first record with the prefix yet (used for commas)
		// add it to the tree
		try {
			dictionary.put(tempRecord); // add the record with the prefix to the tree
		} catch (DictionaryException e) {
		}
		Record current = dictionary.get(new Key(prefix, 0)); // get the record
		
		while(current != null) { // while the record is not null
			current = dictionary.successor(current.getKey()); // get the successor
			
			if(current == null) { // if the successor we found is null
				break; // exit the while loop
			}
			else if(current.getKey().getLabel().startsWith(prefix)) { // if the successor's label starts with the prefix
				if(!first) { // if it is not the first label being stored in the list
					list += ", "; // add comma
				}
				first = false; // set to false (meaning that the first label has been added to the list, allowing us to add comma the next time something is added)
				list += current.getKey().getLabel(); // add the current record's label to the list
			}
			
		}
		try {
			dictionary.remove(new Key(prefix, 0)); // remove the record containing the prefix
		} catch (DictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.equals("")) { // if the list is empty
			return ("No label attributes in the ordered dictionary start with prefix " + prefix); // return string telling user that there is no labels with that prefix
		}
		return list; // return the list
		
		
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		BSTDictionary dict = new BSTDictionary(); // create a BSTDictionary
		Interface inter = new Interface(); // create an interface object (used to call helper method later in main)
		try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))){ // set up the reader for the text file
			String label; // string for the label
			try {
				while((label = reader.readLine()) != null) { // while the label (read the first line) is not equal to null
					int type = 0; // int to store the type
					String dataOriginal = reader.readLine(); // String data (read the next line)
					String data = dataOriginal; // Copy of dataOriginal to manipulate and get the components of the data
					char typeIndication = data.charAt(0); // store the first character of data
					
					// check the different type indicators
					switch(typeIndication) { 
						case '-': // if the first char is '-'
							type = 3; // set type to 3
							dataOriginal = data.substring(1); // data is the rest of the line excluding the first char
							break; // break
						case '+': // if the first char is '+'
							type = 4; // set the type to 4
							dataOriginal = data.substring(1); // data is the rest of the line excluding the first char
							break;
						case '*': // if the first char is '*'
							type = 5; // set the type to 5
							dataOriginal = data.substring(1); // data is the rest of the line excluding the first char
							break;
						case '/': // if the first char is '/'
							type = 2; // set the type to 2
							dataOriginal = data.substring(1); // data is the rest of the line excluding the first char
							break;
						default: // default case if not any of the cases above
							String[] parts = data.split("\\."); // split up the string into parts using periods as the splitter
							if (parts.length == 2) { // if there are only 2 parts of the string
								String extension  = parts[1].toLowerCase(); // store the second part of the string
								switch (extension) { // check the different types of extensions
									case "gif": // if the extension is "gif"
										type = 7; // gif is type 7
										break; // break
									case "jpg": // if the extension is "jpg"
										type = 6; // jpg is type 6
										break;
									case "html": // if the extension is "html"
										type = 8; // html is type 8
										break;
									default: // if the extension is none of the above
										type = 1; // defaulting to type 1 if there is an unknown extension
										break;
								}
							}
							else { // if there is any other size of parts of the string
								type = 1; // default to type 1 for any other case
							}
							
					}
					
					Record rec = new Record(new Key(label.toLowerCase(), type), dataOriginal); // create the record with the key and data
					try {
						dict.put(rec); // put the record into the dictionary
					} catch (DictionaryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		boolean exit = false; // boolean to check if the user has requested to exit
		StringReader keyboard = new StringReader(); // string reader variable
		String line; // string to store the line
		
		while (!exit) { // while the user has not asked to exit
			line = keyboard.read("Enter next command: "); // prompt user to enter next command
			String[] commandParts = line.split(" ", 4); // split up the command by spaces
			String command = commandParts[0]; // store the first part into String command
			String label = "";
			if(commandParts.length > 1) {
				label = commandParts[1].toLowerCase(); // store the second part as the label
			}
			SoundPlayer playSound = new SoundPlayer(); // create the SoundPlayer object
			PictureViewer view = new PictureViewer(); // create the PictureViewer object
			ShowHTML page = new ShowHTML(); // create the ShowHTML object
			
			switch (command) { // check all the different commands
				case "define": // command "define"
					Record record = dict.get(new Key(label, 1)); // get the record with the key (label, 1)
					
					if (record != null) { // if the record exists
						System.out.println(record.getDataItem()); // print it
					}
					else { // otherwise tell the user the record with label is not in the dictionary
						System.out.println("The word " + label + " is not in the ordered dictionary");
					}
					break; // break
				case "translate": // command "translate"
					Record record1 = dict.get(new Key(label, 2)); // get the record with the key (label, 2)
					
					if (record1 != null) { // if the record exists
						System.out.println(record1.getDataItem()); // print it
					}
					else { // otherwise tell the user that there is no record with that label
						System.out.println("There is no defininton for the word " + label);
					}
					break;
				case "sound": // command "sound"
					Record record2 = dict.get(new Key(label, 3)); // get the record with the key(label, 3)
					
					if (record2 != null) { // if the record exists
						try {
							playSound.play(record2.getDataItem()); // play sound
						} catch (MultimediaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else { // otherwise, tell the user there is no sound file with that label
						System.out.println("There is no sound file for " + label);
					}
					break;
				case "play": // command "play"
					Record record3 = dict.get(new Key(label, 4)); // get the record with the key(label, 4)
					
					if (record3 != null) { // if the record exists
						try {
							playSound.play(record3.getDataItem()); // play sound
						} catch (MultimediaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else { // otherwise, tell the user there is no music with that label
						System.out.println("There is no music file for " + label);
					}
					break;
				case "say": // command "say"
					Record record4 = dict.get(new Key(label, 5)); // get the record with the key(label, 5)
					
					if (record4 != null) { // if the record exists
						try {
							playSound.play(record4.getDataItem()); // play sound
						} catch (MultimediaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else { // otherwise, tell the user there is no voice file with that label
						System.out.println("There is no voice file for " + label);
					}
					break;
				case "show": // command "show"
					Record record5 = dict.get(new Key(label, 6)); // get the record with the key(label, 6)
					
					if (record5 != null) { // if the record exists
						try {
							view.show(record5.getDataItem()); // show the picture
						} catch (MultimediaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else { // otherwise, tell the user there is no image file with that label
						System.out.println("There is no image file for " + label);
					}
					break;
				case "animate": // command "animate"
					Record record6 = dict.get(new Key(label, 7)); // get the record with the key(label, 7)
					
					if (record6 != null) { // if the record exists
						try {
							view.show(record6.getDataItem()); // show the animation
						} catch (MultimediaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else { // otherwise, tell the user there is no animated image file with that label
						System.out.println("There is no animated image file for " + label);
					}
					break;
				case "browse": // command "browse"
					Record record7 = dict.get(new Key(label, 8)); //get the record with the key(label, 8)

					if (record7 != null) { // if the record exists
						page.show(record7.getDataItem()); // show the web page
					}
					else { // otherwise, tell the user there is no web page with that label
						System.out.println("There is no webpage called " + label);
					}
					break;
				case "delete": // command "delete"
					try {
						dict.remove(new Key(label, Integer.parseInt(commandParts[2]))); // remove using the key inputed
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DictionaryException e) { // if exception thrown, then print out error message
						System.out.println("No record in the ordered dictionary has key ("+ label +", " + commandParts[2] +")");
					}
					break;
				case "add": // command "add"
					try {
						dict.put(new Record(new Key(commandParts[1], Integer.parseInt(commandParts[2])), commandParts[3])); // put the record using the record inputed
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DictionaryException e) { // if exception thrown, then print out an error message
						System.out.println("A record with the given key (" + commandParts[1] + ", " + commandParts[2] + ") is already in the ordered dictionary");
					}
					break;
				case "list": // command "list"
					System.out.println(inter.listOfPrefix(dict, commandParts[1])); // call the helper method (input the dictionary and the prefix) and print out the resulting list
					break;
				case "first": // command "first"
					Record smallest = dict.smallest(); // get the "smallest" record
					System.out.println(smallest.getKey().getLabel()+ "," + smallest.getKey().getType() + "," + smallest.getDataItem()); // print out the record
					break;
				case "last": // command "last"
					Record largest = dict.largest(); // get the "largest" record
					System.out.println(largest.getKey().getLabel()+ "," + largest.getKey().getType() + "," + largest.getDataItem()); // print out the record
					break;
				case "exit": // command "exit"
					exit = true; // set exit to true (the while loop will now end)
					break;
				default: // default command
					System.out.println("Invalid command"); // tell the user that they entered an invalid command
					break;
					
			}
			
		}
		
		
	}
}
