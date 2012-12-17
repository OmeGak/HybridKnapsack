package Heuristics;

import java.util.ArrayList;

import Problem.Element;
import Problem.Knapsack;

/**
 * Simulated Annealing Class, which represent the algorithm and the solutions.
 * 
 * @author Oxy
 *
 */

public class SimulatedAnnealing {
	/**
	 * Global variables.
	 * The integer type variables are initialized to 0.
	 */
	private int temperature, freezingT, nIter, MaxIter = 0;
	private Knapsack BestSolution, ActualSolution;
	private ArrayList<Knapsack> Solutions;
	private Knapsack knapsack;
	
	/**
	 * Constructor of SimulatedAnnealing class.
	 * @param t Temperature, chosen by the user
	 * @param fT Freezing Temperature 
	 * @param nI Number of iterations
	 * @param Max Max number of iterations
	 * @param k Instance of the problem which We are going to work
	 */
	public SimulatedAnnealing(int t, int fT, int nI, int Max, Knapsack k){
		this.temperature = t;
		this.freezingT = fT;
		this.nIter = nI;
		this.MaxIter = Max;
		this.knapsack = k;
	}
	
	/**
	 * Simulated Annealing Algorithm method
	 */
	private void SAAlgorithm (){
		Element minE = new Element();
		minE = knapsack.getNotInsertedElements().get(0); /*Random value*/
		
		
		/*Looking for the min element in the instance*/
		for(int i = 0; i < knapsack.getNotInsertedElements().size(); i++){
			for(int j = 0; j < knapsack.getNotInsertedElements().size(); j++){
				
				if(minE.getWeight() >  knapsack.getNotInsertedElements().get(j).getWeight()){
					minE = knapsack.getNotInsertedElements().get(j);
				
				/*
				 * If we can put into the knapsack
				 */
				if(knapsack.getTotalWeight() + minE.getWeight() <= knapsack.getCapacity()){
					knapsack.getInsertedElements().add(minE);
					Solutions.add(knapsack);
					knapsack.getNotInsertedElements().remove(minE);
				}else{
					break;
				}
				}
			}
		}
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
