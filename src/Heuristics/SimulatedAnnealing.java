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
	
	private void SAAlgorithm (){
		Element e = new Element();
		e = knapsack.getElements().get(0); /*Random value*/
		
		
		/*Looking for the min element in the instance*/
		for(int i = 0; i < knapsack.getElements().size(); i++){
			for(int j = 0; j < knapsack.getElements().size(); j++){
				
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
