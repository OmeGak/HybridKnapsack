package heuristics;

import java.util.HashMap;

import problem.Element;
import problem.Knapsack;

/**
 * TODO add javadoc
 * @author jorgebg90
 *
 */
public class TabuSearch extends Heuristic {

	/**Tabu list with the worst elements of the KS*/
	HashMap<Integer, Element> tabuList;
	
	/** Knapsack that contains the best solution*/
	private Knapsack bSol;
	/** Knapsack that contains the actual solution*/
	private Knapsack aSol;
	/** Knapsack that contains the temporal solution*/
	private Knapsack btSol;
	
	/**Temporal element that maintains the current best solution*/
	private Element tElement;
	/**Number of iterations that is given by the Agent class*/
	private int iT;
	
	public TabuSearch() {
		// TODO delete
	}
	
	/**
	 * 
	 * @param kInherited knapsack inherited from agent class
	 * @param nIter max number of iterations for the execution
	 * 
	 */
	
	
	public TabuSearch(int nIter, Knapsack kInherited)
	{
	
		this.bSol=kInherited;
		this.iT = nIter;
		
	}

	@Override
	public Knapsack executeOnce(Knapsack knapsack) {
		
		
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public Knapsack TabuSearch ()
	{
		
		//In the first iteration, the best solution is given by the agent
		btSol = bSol;
		
		// tElement contains a random element value from Agent propose
		tElement = btSol.copyNotInsertedElements().get(0); /*Random value*/
		
		//It iterates a defined number of steps given by the Agent
		
			for (int j=0; j<= btSol.copyNotInsertedElements().size();j++)
			{
				if(tElement.getWeight() >  btSol.copyNotInsertedElements().get(j).getWeight())
				{
					
					
				}
				
				if(btSol.getTotalWeight() + tElement.getWeight() <= bSol.getCapacity())
				{
				
					
				}
				
				else{
					break;
				}
				
				
			}
			
		
		
		
		return bSol;
	}
}
