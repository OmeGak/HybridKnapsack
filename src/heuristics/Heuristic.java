package heuristics;

import java.util.Random;

import problem.Element;
import problem.Knapsack;

/**
 * Abstract class to extends specific heuristics from it.
 * 
 * @author omegak
 */
public abstract class Heuristic {
	
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
		
		if (Math.random() < WEIGHT) {
			// Remove
			Element element = neighbour.getRandomInsertedElement();
			neighbour.removeElement(element);
		} else {
			// Insert
			Element element = neighbour.getRandomNotInsertedElement();
			neighbour.insertElement(element);
		}
		
		return neighbour;
	}
	
	/**
	 * Generate a random number of Neighbour
	 * 
	 * @return number of Neighbours.
	 */
	protected int generateRandomNumberOfNeighbour(){
		Random r = new Random();
		return r.nextInt(10)+1;
	}
}
	
