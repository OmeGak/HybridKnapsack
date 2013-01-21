package problem;

/**
 * Evaluator for the knapsack problem.
 * 
 * @author omegak
 */
public class Evaluator {
	
	/** Value for invalid solutions. */
	public static final int INVALID = -1;
	
	/** The maximum number of evaluations before resetting. */
	private static final long MAX_EVALUATIONS = 100000;
	
	/** Number of evaluations performed since last reset. */
	private static long evaluations = 0; 
	
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
	 * Returns the number of evaluation performed since the last reset.
	 * 
	 * @return the number of evaluation performed since the last reset.
	 */
	public static long getEvaluations() {
		return evaluations;
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
