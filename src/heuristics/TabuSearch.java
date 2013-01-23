package heuristics;

import java.util.LinkedList;

import problem.Evaluator;
import problem.Knapsack;

/**
 * Tabu search heuristic.
 * 
 * @author jorgebg90
 */
public class TabuSearch extends Heuristic {

	/** Tabu list with the previous best elements of the KS */
	private LinkedList<Knapsack> tabuList;
	
	/**Candidate list to evaluate */
	private LinkedList<Knapsack> candidateList;

	/** Knapsack that contains the best solution */
	private Knapsack sCandidate;

	/** It contains the size of the tabu list */
	private int tabuListMaxSize;

	/**
	 * Default constructor for Tabu Search
	 * 
	 * @param maxSize
	 *            The maximum size of the tabu list.
	 */
	public TabuSearch(int maxSize) {
		tabuList = new LinkedList<Knapsack>();
		tabuListMaxSize = maxSize;
	}

	
	@Override
	public Knapsack executeOnce(Knapsack knapsack) {
		
		candidateList = null;
		int neighbours = generateRandomNumberOfNeighbours();
		
		Knapsack bestSol = null;

		/** In first instance, the best solution is added by the Agent */
		bestSol = knapsack;

		/** It creates a random neighborhood */
		for (int i = 0; i < neighbours; i++) {
			sCandidate = generateNeighbour(knapsack);
			
			//This checks if the candidate belongs to the tabuList
			for(int j=0;j<tabuList.size();j++){
			
				if(!sCandidate.equalKnapsack(tabuList.get(j))){
					
					candidateList.add(sCandidate);					
				}
			}
			
		}
		
		/**
		 * Now it takes the best candidate in the candidateList
		 * 
		 **/
		for(int i=0;i < candidateList.size();i++){
			
			sCandidate = candidateList.get(0);
			if(sCandidate.evaluate()<candidateList.get(i).evaluate()){
				sCandidate = candidateList.get(i);
				
			}
			
		}
							
		/**
		 * This part evaluates the different solutions If the candidate
		 * solution is better than the previous one it must be changed
		 * and added to the tabu list
		 * */
		if (sCandidate.evaluate() > bestSol.evaluate()) {
				tabuList.add(sCandidate);
				bestSol = new Knapsack(sCandidate);
				/** The tabuList just can be get twelve partial solutions */
				while(tabuList.size() > (tabuListMaxSize-1))
					tabuList.removeLast();
		}

		if (bestSol.evaluate() == Evaluator.INVALID) {
				bestSol = knapsack;
		}
		
		
		return bestSol;
	}
}


