import hybridation.Coordinator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import problem.Knapsack;
import problem.Parser;

/**
 * Launcher class for launching single instances or batches of instances.
 * 
 * @author omegak
 */
public class Launcher {

	/** Extension for csv files. */
	private static final String CSV_EXTENSION = ".csv";
	
	/** Label for csv files. */
	private static final String CSV_FILENAME = "csv";
	
	/** Formater for the date. */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
	
	/** Error message for handling CSV files. */
	private static final String ERROR_HANDLING_CSV = "Error: CSV cannot be handled as expected.";
	
	/**
	 * Parses a file and executes the coordinator that solves the knapsack problem instance hybridly.
	 * 
	 * @param instance The instance to be solved.
	 */
	public static Knapsack launchOneInstance(File instance) {
		Coordinator coordinator;
		Knapsack solution = null;

		try {
			// Parses file
			System.out.println("Parsing file " + instance.getName() + "...");
			Knapsack knapsack = Parser.parse(instance);

			// Solves problem
			System.out.println("Solving...");
			coordinator = new Coordinator(knapsack);
			solution = coordinator.solve();

			// Stores results
			System.out.println("Storing solution...");
			String s = coordinator.printBestSolution();
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return solution;
	}

	/**
	 * Launches a whole directory of instances executing each one of them a given number of instances. 
	 * 
	 * @param triesPerInstance The given number of tries.
	 */
	public static void launchBatch(String path, int triesPerInstance) {
		ArrayList<File> instances = new ArrayList<File>();
		ArrayList<Integer> evaluations;
		Knapsack solution;
		
		File csv = createCSV(path);
		
		try {
			instances = Tools.loadDirectory(path);
			
			for (File instance : instances) {
				evaluations = new ArrayList<Integer>();
				
				for (int i = 0; i < triesPerInstance; i++) {
					solution = launchOneInstance(instance);
					evaluations.add(solution.evaluate());
				}
				
				exportCSV(csv, evaluations);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a CSV file in the given path.
	 * 
	 * @param path The given path.
	 * @return The {@link File} reference to the created file.
	 */
	private static File createCSV(String path) {
		String date = DATE_FORMATTER.format(new Date());
		
		File csv = new File(path + CSV_FILENAME + date + CSV_EXTENSION);
		try {
			csv.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return csv; 
	}
	
	/**
	 * Inserts a new row into a given CSV file.
	 * 
	 * @param file The given CSV file.
	 * @param values The row of integers to be inserted into the CSV file.
	 */
	private static void exportCSV(File file, ArrayList<Integer> values) {				
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String row = "";
			
			for (int value : values) {
				String s = Integer.toString(value);
				row = s + ",";
			}
			
			writer.write(row);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println(ERROR_HANDLING_CSV);
			e.printStackTrace();
		}
	}
}
