package heuristics;

import java.util.LinkedList;
import java.util.Queue;

import problem.Knapsack;

/**
 * Tabu search heuristic.
 * 
 * @author jorgebg90 
 */
public class TabuSearch extends Heuristic {

	/** Tabu list with the previous best elements of the KS */
	private Queue<Knapsack> tabuList;

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
		
		/** It creates a random neighborhood */
		for (int i = 0; i < neighbours; i++) {
			Knapsack candidate = generateNeighbour(knapsack);
			
			if (!isInTabuList(candidate)) {
				if (best == null) {
					best = candidate;
				} else if (candidate.compareWith(knapsack) >= 0) {
					best = candidate;
				}
			}
		}
			
		if (best != null) {
			addInTabuList(best);
			return best;
		} else {
			return knapsack;
		}
	}
	
	/**
	 * Adds in the tabu list a given knapsack. If the tabu list is full the oldest knapsack is removed.
	 * 
	 * @param k The knapsack to be stored.
	 */
	private void addInTabuList(Knapsack k) {
		tabuList.offer(k);
		
		if (tabuList.size() > tabuListMaxSize) {
			tabuList.poll();
		}
	}

	/**
	 * Determines whether a given knapsack already exists within the tabu list.
	 * 
	 * @param k The knapsack to be checked if it already exists.
	 * @return TRUE if present, FALSE otherwise.
	 */
	private boolean isInTabuList(Knapsack k) {
		for (Knapsack tabu : tabuList) {
			if (k.equals(tabu)) {
				return true;
			}
		}
		
		return false;
	}
}
