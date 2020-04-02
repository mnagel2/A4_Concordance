import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * The concordance structure is based on a bucket hash table, 
 * where each bucket is a list of data elements that represent words found in the text.
 * @author Montague Nagel
 *
 */
public class ConcordanceDataStructure implements ConcordanceDataStructureInterface{

	// --- FIELDS
	
	ArrayList<LinkedList<ConcordanceDataElement>> hashTable;
	int size;
	double loadingFactor = 1.5;
	
	// --- CONSTRUCTORS
	
	/**
	 * Number represents the estimated number of words in the text
	 * @param num
	 */
	public ConcordanceDataStructure(int estimatedEntries) {
		size = calcSize(estimatedEntries);
		initialize();
	}
	
	/**
	 * Constructor for testing purposes. 
	 * @param test
	 * @param size
	 */
	public ConcordanceDataStructure(String test, int size) {
		this.size = size;
		initialize();
	}

	// --- METHODS
	
	/**
	 * Initialized the hash table data structure after the size of the structure is established.
	 * @return the hash table data structure 
	 */
	public ConcordanceDataStructure initialize() {
		hashTable = new ArrayList<LinkedList<ConcordanceDataElement>>();
		int i = 0;
		while (i < size) {
			hashTable.add(new LinkedList<ConcordanceDataElement>());
			i++;
		}
		U.p("Size of hashTable: " + hashTable.size());
		return this;
	}
	
	/**
	 * Gives the size of the array portion of the hash data structure
	 * @return size of array
	 */
	public int getTableSize() {
		return size;
	}

	/**
	 * Returns all the words in the "bucket" at the specified index
	 * @return all words in a "bucket" as an ArrayList of Strings
	 */
	public ArrayList<String> getWords(int index) {
		ArrayList<String> bucketWords = new ArrayList<String>();
		for (ConcordanceDataElement e : hashTable.get(index)) {
			bucketWords.add(e.getWord());
		}
		/*
		ListIterator<ConcordanceDataElement> iterator = hashTable.get(index).listIterator();
		for (int i = 0; i < hashTable.get(index).size(); i++) {
			bucketWords.add(iterator.next().getWord());
		}
		*/
		return bucketWords;
	}

	/**
	 * Returns all the elements in the concordance, as a list.
	 * @return all the DataConcordanceElements in an ArrayList.
	 */
	public ArrayList<ConcordanceDataElement> getAllElements(){
		ArrayList<ConcordanceDataElement> elements = new ArrayList<ConcordanceDataElement>();
		for (LinkedList<ConcordanceDataElement> bucket : hashTable) {
			for (ConcordanceDataElement e : bucket) {
				elements.add(e);
				U.p("Found: " + e);
			}
		}
		return elements;
	}
	
	
	public ArrayList<ConcordanceDataElement> getAlphaSortedWords(ArrayList<ConcordanceDataElement> unsorted){
		ArrayList<ConcordanceDataElement> sorted = new ArrayList<ConcordanceDataElement>();
		while (unsorted.size() > 0) {
			ConcordanceDataElement min = new ConcordanceDataElement("~ ~ZZZZZZZZZZZZZZZZZ ~ZZ ~ZZ ~Z"); //Likely to be the last element in a concordance
			for (ConcordanceDataElement e : unsorted) {
				if (e.compareTo(min) < 0) {
					min = e;
				}
			}
			sorted.add(min);
			unsorted.remove(min);
		}
		
		U.p("Alpha Order:");
		for (ConcordanceDataElement e : sorted) {
			U.p(" "+e);
		}
		
		return sorted;
	}
	
	
	/**
	 * Returns all page numbers of the words in the "bucket" at the specified index
	 * @return all page numbers in the form of a ArrayList of LinkedLists, 
	 * where each item in the LinkedList is a line number, 
	 * and each item in the ArrayList is the set of all line numbers for the corresponding word in the "bucket"
	 */
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		ArrayList<LinkedList<Integer>> bucketWordLineNumbers = new ArrayList<LinkedList<Integer>>();
		for (ConcordanceDataElement e : hashTable.get(index)) {
			bucketWordLineNumbers.add(e.getList());
		}
		return bucketWordLineNumbers;
	}

	/**
	 * Adds the word to the correct location in the hash table, if it is not already there.
	 * If the word is already there, the line number will be added to the list of lines in the word data element, if it is not already there.
	 * @param word the word to be added
	 * @param lineNum the line number the word was found on
	 */
	public void add(String word, int lineNum) {
		U.p("Word: "+ word + " | " + word.hashCode() );
		if (word.hashCode() == 0) {
			return;
		}
		LinkedList<ConcordanceDataElement> bucket = hashTable.get(Math.abs(word.hashCode()) % size);
		for (ConcordanceDataElement e : bucket) {
			if (e.getWord().hashCode() == word.hashCode()) {
				e.addPage(lineNum);
				return;
			}
		}
		ConcordanceDataElement entry = new ConcordanceDataElement(word);
		entry.addPage(lineNum);
		bucket.add(entry);
	}
	
	
	public ArrayList<String> showAll() {
		
		ArrayList<String> showString = new ArrayList<String>();
		
		// Get a list of data elements
		ArrayList<ConcordanceDataElement> showElement = getAllElements();
		
		// Get the elements in alphabetical order
		showElement = getAlphaSortedWords(showElement);
		
		// add the element's toString + "\n" as a String for each element (as a separate String in the list)
		
		for (ConcordanceDataElement e : showElement) {
			showString.add(e.toString() + "\n");
			
		}
		
		//return list
		
		
		
		
		return showString;
	}
	
	/**
	 * Calculates the number of buckets the data structure should use                     --- MOVE TO METHODS SECTION WHEN DONE!!!
	 * @param estimatedEntrees
	 * @return
	 */
	public int calcSize(int estimatedEntries) {
		int loaded = (int) Math.floor(estimatedEntries / loadingFactor);
		U.p(loaded);
		int k = (int) Math.floor((loaded - 3)/4)+1;
		U.p(k);
		loaded = 4*k+3;
		
		while (true){
			if (isPrime(loaded)) {
				return loaded;
			} else {
				k++;
				loaded = 4*k+3;
			}
		}
	}
	
	/**
	 * Sees if number is prime
	 * @return true if number is prime
	 */
	public boolean isPrime(int num){
		for (int i = 2; i <= (num/2); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}
