package heuristics;

import java.util.ArrayList;
import java.util.Queue;

import problem.Knapsack;

/**
 * TODO add javadoc
 * @author jorgebg90
 *
 */
public class TabuSearch extends Heuristic {

	/**Tabu list with the previous best elements of the KS*/
	private Queue <Knapsack> tabuList;
	
	/** Knapsack that contains the best solution*/
	private Knapsack bestSol;

	/** Knapsack that contains the best solution*/
	private Knapsack sCandidate;
	
	/** It contains the closed neighborhood*/
	private ArrayList <Knapsack> neighborhood;
	
	/** It contains the size of the tabu list*/
	private int tabuListSize;
	
	
		/**
		 * 
		 * Default constructor for Tabu Search
		 * 
		 * */
		
		
		public TabuSearch(int tlist){
					
			this.tabuListSize=tlist;
		}

		@Override
		public Knapsack executeOnce(Knapsack knapsack) {
			
		
				/**Variables of control*/
				int i = 0;
				int n = 0;
								
				/**It obtains a r index to give back a key to preserve the neighborhood*/
				n =generateRandomNumberOfNeighbours();
				
				/**It creates a random neighborhood*/
				for(i=0;i<n;i++){
					sCandidate=generateNeighbour(bestSol);
							
				/**This part compares if the candidate is included in the tabuList*/
				
					if(tabuList.isEmpty()){
						tabuList.add(sCandidate);			
					}
					/**If the tabu list is not empty but not contains
					 * the next candidate, include the candidate in the 
					 * candidate list*/
					else{
						if(!tabuList.contains(sCandidate)){
							tabuList.add(sCandidate);
						}						
					}
				
				
				/**This part evaluates the different solutions
				 * If the candidate solution is better than the previous one
				 * it must be changed and added to the tabu list
				 * */
				if(sCandidate.evaluate()>bestSol.evaluate()){
					
					tabuList.add(sCandidate);
					bestSol = new Knapsack(sCandidate);
					
					/**The tabuList just can be get twelve partial solutions*/
					if(tabuList.size()>tabuListSize)
						tabuList.remove();
					
			}
					
			}
			return bestSol;
		}
				
	}
	

