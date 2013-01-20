package heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

import problem.Element;
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
		 * @param kInherited knapsack inherited from agent class
		 * @param rounds is the number of rounds that the agent executes
		 */
		
		
		public TabuSearch(Knapsack kInherited, int rounds){
			this.bestSol=kInherited;
			this.sTabulist=rounds;
			
			/**The Tabu list in the first instance must be empty*/
			tabuList=null;
		}

		@Override
		public Knapsack executeOnce(Knapsack knapsack) {
			
			while(!bestSol.isFull())
			{
				
				/**The candidate list is empty at first instance*/
				candidateList = null;		
				
				/**It obtains a r index to give back a key to preserve the neighborhood*/
				Random r = new Random();	
				int n = r.nextInt(10)+1;
				
				/**It creates a random neighborhood*/
				
				
				/**This part compares if the candidate is included in the tabuList*/
				for()
				{
									
					if(tabuList.isEmpty()){
						candidateList.add(sCandidate);			
					}
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
					if(tabuList.size()>12)
					{
						tabuList.remove();
						
					}
					
				}
					
			}
					
		
			return bestSol;
		}
				
	}
	

