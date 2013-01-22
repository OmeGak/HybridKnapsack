import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Set of useful IO tools to use consistently throughout the project.
 * 
 * @author omegak
 */
public class IOTools {
	
	/** Extension for csv files. */
	private static final String CSV_EXTENSION = ".csv";
	
	/** Formater for the date. */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
	
	/** Error message for handling CSV files. */
	private static final String ERROR_HANDLING_CSV = "Error: CSV cannot be handled as expected.";
	
	/** Error message for a not existing directory. */
	private static final String ERROR_NO_SUCH_DIRECTORY = "Error: No such directory.";
	
	/**
	 * Creates a CSV file in the given path.
	 * 
	 * @param path The given path.
	 * @return The {@link File} reference to the created file.
	 */
	public static File createCSV(String path) {
		String date = DATE_FORMATTER.format(new Date());
		
		File csv = new File(path + "_" + date + CSV_EXTENSION);
		try {
			csv.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return csv; 
	}
	
	/**
	 * Inserts a new row of integers into a given CSV file.
	 * 
	 * @param file The given CSV file.
	 * @param values The row of integers to be inserted into the CSV file.
	 */
	public static void exportCSV(File file, ArrayList<Integer> values) {				
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			String row = "";
			
			for (int value : values) {
				row += Integer.toString(value) + ",";
			}
			
			writer.write(row);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println(ERROR_HANDLING_CSV);
			e.printStackTrace();
		}
	}
	
	/**
	 * Tries to load the instances of the knapsack problem from a given path.  
	 * 
	 * @param path The given path.
	 * @return A list of of {@link File} objects.
	 * @throws IOException When there is no such directory.
	 */
	public static ArrayList<File> loadDirectory(String path) throws IOException {
		ArrayList<File> instances = new ArrayList<File>();
		File workspace = new File(path);
		
		// Checks if it is a directory
		if (!workspace.isDirectory()) {
			throw new IOException(ERROR_NO_SUCH_DIRECTORY);
		}
		
		// Indexes the instances
		File[] files = workspace.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			String filename = files[i].getName();
			if (filename.endsWith(".txt") || filename.endsWith(".TXT")) {
				instances.add(files[i]);
			}
		}
		
		return instances;
	}
}
