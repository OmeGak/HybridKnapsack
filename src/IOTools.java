import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Set of useful IO tools to use consistently throughout the project.
 * 
 * @author omegak
 */
public class IOTools {
	
	/** Error message for a not existing directory. */
	private static final String ERROR_NO_SUCH_DIRECTORY = "Error: No such directory.";
	
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
