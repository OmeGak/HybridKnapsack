import hybridation.CoordinatorMode;


/**
 * HybridKnapsack main class.
 * 
 * @author omegak
 */
public class HybridKnapsack {
	
	/** Usage of HybridKnapsack. */
	private static final String USAGE = "usage: HybridKnapsack [path [tries [mode]]\n";
	
	private static final String MODES = "modes:\t-c\tCooperative\n" +
										"\t-l\tLocal Searcn\n" +
										"\t-s\tSimulated Annealing\n" +
										"\t-t\tTabu Search";
	
	/** Mode for the coordinator. */
	private static CoordinatorMode mode = CoordinatorMode.COOPERATIVE;
	
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
		if (args.length == 0) {
			UI.run(path);
		} else if (args.length == 1) {
			path = args[0];
			UI.run(path);
		} else if (args.length == 2) {
			path = args[0];
			tries = Integer.parseInt(args[1]);
			Launcher.launchBatch(path, mode, tries);
		}else if (args.length == 3) {
			path = args[0];
			tries = Integer.parseInt(args[1]);
			mode = CoordinatorMode.ParseCoordinatorMode(args[2]);
			Launcher.launchBatch(path, mode, tries);
		} else if (args.length > 3) {
			System.out.print(USAGE);
			System.out.print(MODES);
		}
	}
}
