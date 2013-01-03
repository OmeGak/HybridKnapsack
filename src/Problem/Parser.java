package Problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for reading files containing knapsack problems.
 * 
 * @author omegak
 */
public class Parser {
	
	/** Error message for invalid files. */
	private static final String ERROR_INVALID_FILE = "ERROR: The file is invalid.";
	
	/** The pattern for valid body chunks. */
	private static final String PATTERN_BODY = "(\\d+\\s+)*\\s*";
	
	/** The pattern for valid empty lines. */
	private static final String PATTER_EMPTY = "\\s*";
	
	/** The pattern for valid headers. */
	private static final String PATTERN_HEADER = "\\d+ \\d+ \\d+\\s*";
	
	/** The pattern for splitting string */
	private static final String PATTER_SPLIT = " ";
	
	/** Capacity of the last parsed instance. */
	private static int capacity = 0;
	
	/** Optimal value of the last parsed instance. */
	private static int optimalValue = 0;
	
	/** List of elements of the last parsed instance. */
	private static ArrayList<Element> notInserted = new ArrayList<Element>();
	
	/**
	 * Parsing method that takes a file and returns a knapsack problem instance.
	 * 
	 * @param file The file to be parsed.
	 * @return a {@link Knapsack} instance.
	 * @throws Exception When the file's format doesn't match the parser.
	 */
	public static Knapsack parse(File file) throws Exception {
		
		// Prepares to read the file
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		// Parses the file
		parseHeader(reader.readLine());
		parseBody(reader);
		reader.close();
		
		// Wraps the read values in a Knapsack object
		return new Knapsack(capacity, optimalValue, notInserted);
	}
	
	/**
	 * Parses the header of the file given a line with this format: <em>\d+ \d+ \d+\s*</em>. Being the numbers:
	 * <ul>
	 * 	<li>Number of elements.</li>
	 * 	<li>Capacity.</li>
	 * 	<li>Optimal value.</li>
	 * </ul>
	 * 
	 * @param line The header line to be parsed.
	 * @throws IOException When the line's format doesn't match the expected format.
	 */
	private static void parseHeader(String line) throws IOException {
		
		// Checks if the line matches the pattern
		if (!line.matches(PATTERN_HEADER)) {
			throw new IOException(ERROR_INVALID_FILE);
		}
		
		// Stores values
		String[] values = line.split(PATTER_SPLIT);
		capacity = Integer.parseInt(values[1]);
		optimalValue = Integer.parseInt(values[2]);
	}
	
	/**
	 * Parses the body of a file, containing values and weights of elements, and stores them in the {@link #notInserted}
	 * list.
	 * 
	 * @param reader A {@link BufferedReader} ready to read the body of a file.
	 * @throws IOException When a line's format doesn't match the expected format.
	 */
	private static void parseBody(BufferedReader reader) throws IOException {
		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		String line;
		
		// Creates the list for the elements
		notInserted = new ArrayList<Element>();
		
		// Reads values
		do {
			line = reader.readLine();
			
			if (!line.matches(PATTER_EMPTY)) {
				parseBodyLines(line, values);
			}
		} while (!line.matches(PATTER_EMPTY));
		
		// Reads weights
		do {
			line = reader.readLine();
			
			if (line != null) {
				parseBodyLines(line, weights);				
			}
		} while (line != null);
		
		// Creates the elements and adds them to the list
		for (int i = 0; i < values.size(); i++) {
			Element e = new Element(values.get(i), weights.get(i));
			notInserted.add(e);
		}
	}
	
	/**
	 * Parses a given line containing values or weights of elements storing them into a given {@link ArrayList}.
	 * 
	 * @param bodyLine A line containing values or weights of elements in plain text.
	 * @param list A list containing values or weights of elements.
	 * @throws IOException When the line's format doesn't match the expected format.
	 */
	private static void parseBodyLines(String bodyLine, ArrayList<Integer> list) throws IOException {
		
		// Checks if the lines match the pattern 
		if (!bodyLine.matches(PATTERN_BODY)) {
			throw new IOException(ERROR_INVALID_FILE);
		}
		
		// Parses the line
		String[] items = bodyLine.split(PATTER_SPLIT);
		
		// Adds the items to the list
		for (String str : items) {
			list.add(Integer.parseInt(str));
		}
	}
}
