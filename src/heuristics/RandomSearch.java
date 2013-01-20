package heuristics;

import problem.Knapsack;

/**
 * Random search that fills the knapsack. It does not need to work concurrently and therefore 
 * {@link #executeOnce(Knapsack)} runs the whole algorithm.
 * 
 * @author omegak
 */
public class RandomSearch extends Heuristic {

	@Override
	public Knapsack executeOnce(Knapsack knapsack) {
		while ((!knapsack.isFull()) && (knapsack.hasElementsLeft())) {
				knapsack.insertElement(knapsack.getRandomNotInsertedElement());
		}
		
		return knapsack;
	}
}
