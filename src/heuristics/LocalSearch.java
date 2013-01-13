package heuristics;

import java.util.HashMap;

import problem.Element;
import problem.Knapsack;

/**
 * TODO add javadoc
 * @author omegak
 *
 */
public class LocalSearch extends Heuristic {

	@Override
	public Knapsack executeOnce(Knapsack knapsack) {
		HashMap<Integer, Element> insertedElements = knapsack.copyInsertedElements();
		
		for (Integer key : insertedElements.keySet()) {
			// TODO hacer y tal
		}
		
		return knapsack;
	}
}
