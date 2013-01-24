import hybridation.Coordinator;
import hybridation.CoordinatorMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import problem.Knapsack;
import problem.Parser;

/**
 * Launcher class for launching single instances or batches of instances.
 * 
 * @author omegak
 */
public class Launcher {
	
	/**
	 * Parses a file and executes the coordinator that solves the knapsack problem instance hybridly.
	 * 
	 * @param mode The mode of execution.
	 * @param instance The instance to be solved.
	 */
	public static Knapsack launchOneInstance(CoordinatorMode mode, File instance) {
		Coordinator coordinator;
		Knapsack solution = null;

		try {
			// Parses file
			System.out.println("Parsing file " + instance.getName() + "...");
			Knapsack knapsack = Parser.parse(instance);

			// Solves problem
			System.out.println("Solving...");
			coordinator = new Coordinator(mode, knapsack);
			solution = coordinator.solve();

			// Stores results
			System.out.println(coordinator.printBestSolution());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return solution;
	}

	/**
	 * Launches a whole directory of instances executing each one of them a given number of instances. 
	 * 
	 * @param path The folder where the instances are stored.
	 * @param mode The mode of execution.
	 * @param triesPerInstance The given number of tries.
	 */
	public static void launchBatch(String path, CoordinatorMode mode, int triesPerInstance) {
		ArrayList<File> instances = new ArrayList<File>();
		ArrayList<Integer> evaluations;
		Knapsack solution;
		
		File csv = IOTools.createCsvFile(mode.toString());
		
		try {
			instances = IOTools.loadDirectory(path);
			
			for (File instance : instances) {
				evaluations = new ArrayList<Integer>();
				
				for (int i = 0; i < triesPerInstance; i++) {
					solution = launchOneInstance(mode, instance);
					if (evaluations.size() == 0) {
						evaluations.add(solution.getOptimalValue());
					}
					evaluations.add(solution.evaluate());
				}
				
				IOTools.exportCsvRow(csv, evaluations);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
