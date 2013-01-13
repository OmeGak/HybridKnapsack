/**
 * HybridKnapsack main class.
 * 
 * @author omegak
 */
public class HybridKnapsack {
	
	/** Usage of HybridKnapsack. */
	private static final String USAGE = "usage: HybridKnapsack [path]";
	
	/** Path where the instances of the knapsack problem are located. */
	private static String path = "instances";
	
	/**
	 * Main method of the program. It launches the UI.
	 * 
	 * @param args Arguments passed to be processed.
	 */
	public static void main(String[] args) {
		
		// Arguments handling
		if (args.length > 0) {
			path = args[0];			
		} else if (args.length > 1) {
			System.out.println(USAGE);
		}
		
		// User Interface execution
		UI.run(path);
	}

}
