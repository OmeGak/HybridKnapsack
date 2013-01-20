package heuristics;

import java.util.HashMap;

import problem.Element;
import problem.Knapsack;

/**
 * Greedy heuristics that fills a knapsack. It does not need to work concurrently and therefore 
 * {@link #executeOnce(Knapsack)} runs the whole algorithm.
 * 
 * @author omegak
 */
public class Greedy extends Heuristic {

	/** Error message when there is no fitting element. */
	private static final String ERROR_NO_FITTING = "Error: there is not fitting element.";
	
	/**
	 * Executes the whole algorithm and produces a new solution.
	 * 
	 * @return A new {@link Knapsack} object encoding the new solution.
	 */
	@Override
	public Knapsack executeOnce(Knapsack knapsack) {

		// Fill the knapsack with the best element at a time
		while ((!knapsack.isFull()) && (knapsack.hasElementsLeft())) {
			try {
				knapsack.insertElement(getBestFitting(knapsack));
			} catch (Exception e) {
				System.out.println(ERROR_NO_FITTING);
				break;
			}
		}
		
		return knapsack;
	}
	
	/**
	 * Returns the best fitting element.
	 * 
	 * @param knapsack The knapsack to get the not inserted elements from.
	 * @return The best fitting element.
	 * @throws Exception When there is no fitting element.
	 */
	private Element getBestFitting(Knapsack knapsack) throws Exception {
		HashMap<Integer, Element> notInsertedElements = knapsack.copyNotInsertedElements();
		Element best = null;
		
		// Searches the best fitting
		for (Integer key : notInsertedElements.keySet()) {
			Element element = notInsertedElements.get(key);
			
			// Initialization
			if (best == null) {
				best = element;
			} 
			
			// Comparison
			else if (element.getRatio() > best.getRatio()) {
				if (knapsack.fits(element)) {
					best = element;
				}
			}
		}
		
		// Exception check
		if (best == null) {
			throw new Exception();
		}
		
		return best;
	}

}
