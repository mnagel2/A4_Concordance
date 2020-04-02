import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The manager prepares String inputs and File inputs for the concordance data structure to take and process into String or File concordances.
 * @author Montague Nagel
 *
 */
public class ConcordanceDataManager implements ConcordanceDataManagerInterface{

	ConcordanceDataStructure structure;
	Scanner scan;
	int lineNum;
	
	public ConcordanceDataManager(){}
	
	/**
	 * 
	 * TODO Change it from word=line to line being everything before a \n (use a scanner)
	 * @return an ArrayList of Strings, each of which represents a concordance entry
	 */
	public ArrayList<String> createConcordanceArray(String input) {
		input = input.toLowerCase();
		input = removePunctuation(input);
		input = removeSmallWords(input);
		
		String[] words = input.split("\\s+");
		structure = new ConcordanceDataStructure(words.length);
		
		scan = new Scanner(input);
		lineNum = 0;
		
		
		if (scan.hasNext()) {
			do {
				lineNum++;
				String[] temp = scan.nextLine().split("\\s+");;
				for (String s : temp) {
					structure.add(s, lineNum);
				}
			} while (scan.hasNextLine());
		}
		
		/*
		
		for (int i = 0; i < words.length; i++) {
			structure.add(words[i],i);
		}*/
		// TODO Auto-generated method stub
		
		scan.close();
		return structure.showAll();
	}

	@Override
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		scan = new Scanner(input);
		String inputString = "";
		
		if (scan.hasNext()) {
			do {
				String[] temp = scan.nextLine().split("\\s+");;
				for (String s : temp) {
					inputString +=  " " + s;
				}
				inputString += "\n";
			} while (scan.hasNextLine());
		}
		U.p(inputString);
		
		scan.close();
		
		
		ArrayList<String> outputArray = createConcordanceArray(inputString);
		U.p("\noutputArray length: " + outputArray.size());
		try {
			FileWriter writer = new FileWriter(output);
			for (String s : outputArray) {
				writer.write(s);
				U.p("Writing '" + s + "' to file");
			}
			writer.close();
		} catch (IOException e) {
			U.p(e.getMessage());
		} catch (Exception e) {
			U.p("Something when wrong with writing the file!: " + e.getMessage());
		}
		return true;
	}

	/**
	 * 
	 */
	public String formatInput(String s) {
		s.toLowerCase();
		s = removePunctuation(s);
		s = removeSmallWords(s);
		return s;
	}
	
	/**
	 * Removes punctuation marks from the input String
	 * @param s Input String
	 * @return the input string without punctuation marks, excluding apostrophes in the middle of words
	 */
	public String removePunctuation(String s) {
		s = s.replace("."," ");
		s = s.replace(","," ");
		s = s.replace("!"," ");
		s = s.replace(" ' "," ");
		s = s.replace("' "," ");
		s = s.replace(" '"," ");
		s = s.replace("\""," ");
		s = s.replace("?"," ");
		s = s.replace("("," ");
		s = s.replace(")"," ");
		s = s.replace(":"," ");
		s = s.replace(";"," ");
		s = s.replace("~"," ");
		s = s.replace("_"," ");
		return s;
	}
	
	/**
	 * Removes the words "the", "and", as well as any one or two letter words from the concordance.
	 * @param s Input String
	 * @return the input string without any short words, which includes "the", "and", and words shorter than three characters
	 */
	public String removeSmallWords(String s) {
		String output = "";
		Scanner lineScanner = new Scanner(s);
		if(lineScanner.hasNext()) {
			do {
				String [] lineString = lineScanner.nextLine().split("\\s+");
				ArrayList<String> lineArray = new ArrayList<String>();
				for(String word : lineString) {
					if (word.length() >= 3 && !word.equals("the") && !word.equals("and")) {
						lineArray.add(word);
					}
				}
				for(String word : lineArray) {
					output += word + " ";
				}
				output += "\n";
			} while (lineScanner.hasNextLine());
		}
		lineScanner.close();
		return output;
	}
}
