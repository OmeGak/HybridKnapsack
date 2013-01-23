package heuristics;

import java.util.Random;

import problem.Knapsack;

/**
 * Abstract class to extends specific heuristics from it.
 * 
 * @author omegak
 */
public abstract class Heuristic {
	
	/** Warning message for unchanged neighbours. */
	private static final String WARNING_NEIGHBOUR = "Warning: Generated neighbour didn't change.";
	
	/** Weight for randomly remove or insert an element from a knapsack. */
	private static final double WEIGHT = 0.5;
	
	/**
	 * Executes one step of the heuristic producing a new solution.
	 * 
	 * @param knapsack The knapsack to be solved.
	 */
	public abstract Knapsack executeOnce(Knapsack knapsack);
	
	/**
	 * Generates a new knapsack that is a neighbour of a given one.
	 * 
	 * @param knapsack The given knapsack to create a neighbour from it.
	 * @return A new knapsack.
	 */
	protected Knapsack generateNeighbour(Knapsack knapsack) {
		Knapsack neighbour = new Knapsack(knapsack);
		double random = Math.random();
		
		try {
			if (random > WEIGHT) {
				if (neighbour.hasNotInsertedElements()) {
					neighbour.insertElement(neighbour.getRandomNotInsertedElement());										
				} else {
					neighbour.removeElement(neighbour.getRandomInsertedElement());					
				}
			} else {
				if (neighbour.hasInsertedElements()) {
					neighbour.removeElement(neighbour.getRandomInsertedElement());					
				} else {					
					neighbour.insertElement(neighbour.getRandomNotInsertedElement());										
				}
			} 
		} catch (Exception e) {
			System.out.println(WARNING_NEIGHBOUR);
		}
		
		return neighbour;
	}
	
	/**
	 * Generate a random number of Neighbour
	 * 
	 * @return number of Neighbours.
	 */
	protected int generateRandomNumberOfNeighbours(){
		Random r = new Random();
		return r.nextInt(10)+1;
	}
}
