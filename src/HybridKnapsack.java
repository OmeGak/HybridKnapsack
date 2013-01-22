
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
	
	/** Number of tries for running each instance. */
	private static int tries = 30;
	
	/**
	 * Main method of the program. It launches the UI.
	 * 
	 * @param args Arguments passed to be processed.
	 */
	public static void main(String[] args) {
		
		// Arguments handling
		if (args.length == 0) {
			UI.run(path);
		} else if (args.length == 1) {
			path = args[0];
			UI.run(path);
		} else if (args.length == 2) {
			path = args[0];
			tries = Integer.parseInt(args[1]);
			Launcher.launchBatch(path, tries);
		} else if (args.length > 2) {
			System.out.println(USAGE);
		}
	}
}
