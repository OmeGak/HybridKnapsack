package heuristics;

import java.util.LinkedList;
import java.util.Queue;

import problem.Evaluator;
import problem.Knapsack;

/**
 * Tabu search heuristic.
 * 
 * @author jorgebg90 
 */
public class TabuSearch extends Heuristic {

	/** Tabu list with the previous best elements of the KS */
	private Queue<Knapsack> tabuList;

	/** Knapsack that contains the best solution */
	private Knapsack bestSol;

	/** Knapsack that contains the best solution */
	private Knapsack sCandidate;

	/** It contains the size of the tabu list */
	private int tabuListMaxSize;

	/**
	 * Default constructor for Tabu Search
	 * 
	 * @param maxSize The maximum size of the tabu list.
	 */
	public TabuSearch(int maxSize) {
		tabuList = new LinkedList<Knapsack>();
		tabuListMaxSize = maxSize;
	}

	@Override
	public Knapsack executeOnce(Knapsack knapsack) {
		int neighbours = generateRandomNumberOfNeighbours();
		Knapsack best = null;
		
		/** In first instance, the best solution is added by the Agent */
		bestSol = knapsack;
		
		/** It creates a random neighborhood */
		for (int i = 0; i < neighbours; i++) {
			sCandidate = generateNeighbour(knapsack);
			
			if (!tabuList.contains(sCandidate)) {
				tabuList.add(sCandidate);
			}

			/**
			 * This part evaluates the different solutions If the candidate
			 * solution is better than the previous one it must be changed and
			 * added to the tabu list
			 * */
			if (sCandidate.evaluate() > knapsack.evaluate()) {

				tabuList.add(sCandidate);
				bestSol = new Knapsack(sCandidate);

				/** The tabuList just can be get twelve partial solutions */
				if (tabuList.size() > tabuListMaxSize)
					tabuList.remove();

			}
			if (bestSol.evaluate() == Evaluator.INVALID) {
				bestSol = knapsack;
			}
		}
		
		return bestSol;
	}
}
