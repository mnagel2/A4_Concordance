import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Element object that contains the word and its associated line numbers
 * @author Montague Nagel
 *
 */
public class ConcordanceDataElement implements Comparable<ConcordanceDataElement>{

	// --- FIELDS
	
	private String word;
	private LinkedList<Integer> lineNumbers;
	
	// --- CONSTRUCTORS
	
	/**
	 * Creates a new word element
	 * @param word
	 */
	public ConcordanceDataElement(String word) {
		this.word = word;
		lineNumbers = new LinkedList<Integer>();
	}
	
	// --- METHODS
	
	/**
	 * Adds a line number to the linked list if the number doesn't exist in the list
	 * @param lineNumber
	 */
	public void addPage(int lineNumber) {
		for (Integer i: lineNumbers) {
			if (i == lineNumber) {
				return;
			}
		}
		lineNumbers.add(lineNumber);
	}
	
	/**
	 * Lexicographical comparison between two elements' words
	 * @param arg0
	 * @return
	 */
	public int compareTo(ConcordanceDataElement anotherDataElement) {
		return this.word.compareTo(anotherDataElement.word);
	}
	
	/**
	 * Returns the LinkedList of integers that represent the line numbers the word can be found on
	 * @return the LinkedList of integers that represent the line numbers
	 */
	public LinkedList<Integer> getList(){
		return lineNumbers;
	}
	
	/**
	 * Returns the word stored in this element
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Returns the hash code of the word stored in this element
	 * @return hashCode of the word
	 */
	public int hashCode() {
		return word.hashCode();
	}
	
	/**
	 * Returns a string in the following format: word: pageNum, pageNum - Example: after: 2,8,15
	 * @return the word followed by page numbers as a string
	 */
	public String toString() {
		String rep = word + ": ";
		ListIterator<Integer> iterator = lineNumbers.listIterator();
		for(int i=0; i < lineNumbers.size(); i++) {
			rep += iterator.next();
			if (iterator.hasNext()) {
				rep += ", ";
			}
		}
		return rep;
	}
}
