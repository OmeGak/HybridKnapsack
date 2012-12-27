package Problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Class for reading files containing knapsack problems.
 * 
 * @author omegak
 */
public class Parser {
	
	private static int capacity;
	private static int optimalValue;
	private static ArrayList<Element> notInserted = new ArrayList<Element>();
	
	/**
	 * Parsing method that takes a file and returns a knapsack problem instance.
	 * 
	 * @param file The file to be parsed.
	 * @return a {@link Knapsack} instance.
	 * @throws Exception When the file's format doesn't match the parser.
	 */
	public static Knapsack parse(File file) throws Exception {
		
		
		// Starts reading the file
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferReader = new BufferedReader(fileReader);
		
		// TODO finish method
		
		return new Knapsack(capacity, optimalValue, notInserted);
	}
	
	/**
	 * TODO add doc
	 * @param line
	 */
	private static void headerReader(String line) throws Exception {
		// TODO complete
	}
	
	private static void handleLine(String s) {
		// TODO complete
	}
}
