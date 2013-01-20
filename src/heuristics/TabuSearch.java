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

	/**Tabu list with the worst elements of the KS*/
	private Queue <Knapsack> tabuList;
	
	/** List of the possible candidates in the neighborhood*/
	private ArrayList <Knapsack> candidateList;
	
	/** Knapsack that contains the best solution*/
	private Knapsack bestSol;

	/** Knapsack that contains the best solution*/
	private Knapsack sCandidate;

	/**Index to iterates the knapsack*/
	private int sTabulist=0;
	
	/** It contains the closed neighborhood*/
	private ArrayList <Knapsack> neighborhood;
	
	
		/**
		 * 
		 * Default constructor for Tabu Search
		 * 
		 * */
		
		
		public TabuSearch(){
					
			/**Nothing to do*/
		}

		@Override
		public Knapsack executeOnce(Knapsack knapsack) {
			
			while(!bestSol.isFull())
			{
				/**Variables of control*/
				int i = 0;
				int j = 0;
				int n = 0;
				
				/**The candidate list is empty at first instance*/
				candidateList = null;		
				
				/**It obtains a r index to give back a key to preserve the neighborhood*/
				n =generateRandomNumberOfNeighbours();
				
				/**It creates a random neighborhood*/
				
				
				
					neighborhood.add(generateNeighbour(bestSol));
				
				
				
				/**This part compares if the candidate is included in the tabuList*/
				for(i=0;i<n;i++)
				{
									
					if(tabuList.isEmpty()){
						candidateList.add(sCandidate);			
					}
					/**If the tabu list is not empty but not contains
					 * the next candidate, include the candidate in the 
					 * candidate list*/
					else{
						if(!tabuList.contains(sCandidate)){
							candidateList.add(sCandidate);
							
						}						
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
					if(tabuList.size()>rounds)
					{
						tabuList.remove();
						
					}
					
				}
					
			}
					
		
			return bestSol;
		}
				
	}
	

