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
	private Queue <Element> tabuList;
	
	/** List of the possible candidates in the neighborhood*/
	private ArrayList <Element> candidateList;
	
	/** Knapsack that contains the best solution*/
	private Knapsack bestSol;

	//*Index to iterates the knapsack*/
	private int i=0;
	
	//** It contains the closed neighborhood*/
	private ArrayList <Knapsack> neighborhood;
	
	
		/**
		 * 
		 * @param kInherited knapsack inherited from agent class
		 * 
		 */
		
		
		public TabuSearch(Knapsack kInherited){
			this.bestSol=kInherited;
		}

		@Override
		public Knapsack executeOnce(Knapsack knapsack) {
			
			//The Tabu list in the first instance must be empty
			tabuList=null;
			
			HashMap<Integer, Element> inserted = knapsack.copyInsertedElements();
			HashMap<Integer, Element> notInserted = knapsack.copyNotInsertedElements();
			
			
			
			while(!bestSol.isFull()){
				
				/**The candidate list is empty at first instance*/
				candidateList = null;		
				
				/**It obtains a r index to give back a key to preserve the neighborhood*/
				Random r = new Random();	
				int n = r.nextInt(10)+1;
				
				/**It creates a random neighborhood*/
				for(int i = 0; i < n; i++){
					neighborhood.add(new Knapsack(bestSol));
					
					
				}
				
				
				/**This part compares if the candidate is included in the tabuList*/
				for(){
									
					if(tabuList.isEmpty()){
						
						candidateList.add(sCandidate);			
					}
					else{
						if(!tabuList.contains(sCandidate)){
							candidateList.add(sCandidate);
							
						}						
					}
				}
				
				/***/
				
					
				}
					
				
				
				
			
			
			
			
					
	
				
			
			
			
			return bSol;
		}
				
	}
	

