package problem;

/**
 * Evaluator for the knapsack problem.
 * 
 * @author omegak
 */
public class Evaluator {
	
	/** The maximum number of evaluations before resetting. */
	private static final long MAX_EVALUATIONS = 100000;
	
	/** Value for invalid solutions. */
	private static final int INVALID = -1;
	
	/** Number of evaluations performed since last reset. */
	private static int evaluations = 0; 
	
	/**
	 * Evaluation function that returns the profit of the solution. 
	 * 
	 * @return the profit of the solution. Negative for invalid solutions.
	 */
	public static int evaluate(Knapsack knapsack) {
		evaluations++;
		
		if (knapsack.calculateTotalWeight() <= knapsack.getCapacity()) {
			return knapsack.calculateTotalValue();
		} else {
			return INVALID;
		}
	}
	
	/**
	 * Determines whether the evaluator has spent all evaluations or not.
	 * 
	 * @return TRUE if the evaluator has spent all evaluations, FALSE otherwise.
	 */
	public static boolean isExhausted() {
		return evaluations > MAX_EVALUATIONS;
	}
	
	/**
	 * Reset the number of evaluations performed.
	 */
	public static void reset() {
		evaluations = 0;
	}
}
